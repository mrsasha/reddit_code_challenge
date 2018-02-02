package info.sasasekulic.redditcodechallenge.screens

import info.sasasekulic.redditcodechallenge.TestSchedulerProvider
import info.sasasekulic.redditcodechallenge.api.models.Child
import info.sasasekulic.redditcodechallenge.api.models.CommentChild
import info.sasasekulic.redditcodechallenge.api.models.LinkChild
import info.sasasekulic.redditcodechallenge.api.models.ListingChild
import info.sasasekulic.redditcodechallenge.repositories.IPreferencesRepository
import info.sasasekulic.redditcodechallenge.repositories.IRedditDataRepository
import info.sasasekulic.redditcodechallenge.util.ISchedulerProvider
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*

class MainPresenterTest {

    private val prefsRepository: IPreferencesRepository = mock(IPreferencesRepository::class.java)

    private val redditRepository: IRedditDataRepository = mock(IRedditDataRepository::class.java)

    private val schedulerProvider: ISchedulerProvider = TestSchedulerProvider()

    private val mainView: MainPresenter.MainView = mock(MainPresenter.MainView::class.java)

    private lateinit var mainPresenter: MainPresenter

    private val linkChild: LinkChild = LinkChild("t3", LinkChild.LinkData("url", "title", "thumb"))

    private val commentChild: CommentChild = CommentChild("t1", CommentChild.CommentData("id", "author", 0, 0, "body"))

    private val commentChildList: ArrayList<Child> = arrayListOf(commentChild)

    private val listingChild1: ListingChild = ListingChild("Listing", ListingChild.ListingData("", "", "", 0, arrayListOf(linkChild)))

    private val listingChild2: ListingChild = ListingChild("Listing", ListingChild.ListingData("", "", "", 0, commentChildList))

    private val comments: ArrayList<Child> = arrayListOf(listingChild1, listingChild2)

    @Before
    fun setup() {
        mainPresenter = spy(MainPresenter(prefsRepository, redditRepository, schedulerProvider))
        mainPresenter.view = spy(mainView)
    }

    @Test
    fun `setMainView_loads_saved_articleId_and_loads_article`() {
        given(prefsRepository.getSavedArticleId()).willReturn("ID")
        given(redditRepository.getArticleComments("ID")).willReturn(Observable.just(comments))

        mainPresenter.setMainView(mainView)

        verify(mainPresenter, times(1)).loadArticle("ID")
    }

    @Test
    fun `loadArticle_with_empty_articleId_shows_input_dialog`() {
        mainPresenter.loadArticle("")

        verify(mainPresenter.view, times(1)).showInputDialog()
    }

    @Test
    fun `loadArticle_saves_articleId_if_loading_is_successful`() {
        given(redditRepository.getArticleComments("ID")).willReturn(Observable.just(comments))

        mainPresenter.loadArticle("ID")

        verify(prefsRepository, times(1)).setSavedArticleId("ID")
    }

    @Test
    fun `loadArticle_shows_data_if_loading_is_successful`() {
        given(redditRepository.getArticleComments("ID")).willReturn(Observable.just(comments))

        mainPresenter.loadArticle("ID")

        verify(mainPresenter.view, times(1)).showLink(linkChild)
        verify(mainPresenter.view, times(1)).showComments(commentChildList)
    }

    @Test
    fun `loadArticle_removes_progress_when_loaded`() {
        given(redditRepository.getArticleComments("ID")).willReturn(Observable.just(comments))

        mainPresenter.loadArticle("ID")

        verify(mainPresenter.view, times(1)).showLoading(true)
        verify(mainPresenter.view, times(1)).showLoading(false)
    }

}