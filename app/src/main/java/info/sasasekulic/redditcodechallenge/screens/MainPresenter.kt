package info.sasasekulic.redditcodechallenge.screens

import info.sasasekulic.redditcodechallenge.api.models.Child
import info.sasasekulic.redditcodechallenge.api.models.LinkChild
import info.sasasekulic.redditcodechallenge.api.models.ListingChild
import info.sasasekulic.redditcodechallenge.repositories.IPreferencesRepository
import info.sasasekulic.redditcodechallenge.repositories.IRedditDataRepository
import info.sasasekulic.redditcodechallenge.util.ISchedulerProvider
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import javax.inject.Inject

open class MainPresenter @Inject constructor(prefsRepository: IPreferencesRepository, redditRepository: IRedditDataRepository, schedulerProvider: ISchedulerProvider) {

    private val preferencesRepository = prefsRepository

    private val redditDataRepository = redditRepository

    private val appSchedulerProvider = schedulerProvider

    lateinit var view: MainView

    private lateinit var article: List<Child>

    private var loadArticleSubscription: Disposable = Disposables.disposed()

    fun setMainView(view: MainView) {
        this.view = view
        val articleId = preferencesRepository.getSavedArticleId()

        loadArticle(articleId)
    }

    fun loadArticle(articleId: String) {
        if (articleId.isEmpty()) {
            view.showInputDialog()
        } else {
            view.showLoading(true)
            loadArticleSubscription = redditDataRepository.getArticleComments(articleId)
                    .observeOn(appSchedulerProvider.ui())
                    .subscribe(
                            { art ->
                                article = art
                                preferencesRepository.setSavedArticleId(articleId)
                                view.showLoading(false)
                                view.showLink((article.get(0) as ListingChild).data.children.get(0) as LinkChild)
                                view.showComments((article.get(1) as ListingChild).data.children)
                            },
                            { exception ->
                                view.showError(exception)
                                view.showLoading(false)
                            })
        }
    }

    fun dispose() {
        loadArticleSubscription.dispose()
    }

    interface MainView {
        fun showInputDialog()

        fun showError(error: Throwable)

        fun showLoading(loading: Boolean)

        fun showLink(link: LinkChild)

        fun showComments(commentsList: List<Child>)
    }

}