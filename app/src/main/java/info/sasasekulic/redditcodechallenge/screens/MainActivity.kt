package info.sasasekulic.redditcodechallenge.screens

import android.app.DialogFragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import dagger.android.AndroidInjection
import info.sasasekulic.redditcodechallenge.R
import info.sasasekulic.redditcodechallenge.api.models.LinkChild
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainPresenter.MainView, ArticleIdInputDialog.ArticleIdInputDialogListener {

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var dialog: ArticleIdInputDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setMainView(this)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            showInputDialog()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun showInputDialog() {
        if (supportFragmentManager.findFragmentByTag(ArticleIdInputDialog.TAG) != null) {
            val fragment = supportFragmentManager.findFragmentByTag(ArticleIdInputDialog.TAG) as DialogFragment
            fragment.dismiss()
        }

        dialog = ArticleIdInputDialog()
        dialog.show(supportFragmentManager, ArticleIdInputDialog.TAG)
    }

    override fun showError(error: Throwable) {
        Log.e("MainActivity", error.toString())

        Snackbar.make(fab!!, R.string.error, Snackbar.LENGTH_LONG)
                .setAction(resources.getString(R.string.action_retry), { _ -> showInputDialog() })
                .show()
    }

    override fun showLoading(loading: Boolean) {
        progress_bar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun showLink(link: LinkChild) {
        Log.d("MainActivity", "loaded link = " + link)

        link_title.text = link.data.title
        link_url.text = link.data.url
    }

    override fun onLoadArticleClick(articleId: String) {
        presenter.loadArticle(articleId)
    }
}
