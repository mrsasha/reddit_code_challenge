package info.sasasekulic.redditcodechallenge.screens

import android.util.Log
import info.sasasekulic.redditcodechallenge.repositories.IPreferencesRepository
import info.sasasekulic.redditcodechallenge.repositories.RedditDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainPresenter @Inject constructor(prefsRepository: IPreferencesRepository, redditRepository: RedditDataRepository) {

    private val preferencesRepository = prefsRepository

    private val redditDataRepository = redditRepository

    private lateinit var view: MainView

    private lateinit var article: List<Object>

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
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { art ->
                                article = art
                                preferencesRepository.setSavedArticleId(articleId)
                                view.showLoading(false)
                                Log.d("MAINPRESENTER", "loaded article!")
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
    }

}