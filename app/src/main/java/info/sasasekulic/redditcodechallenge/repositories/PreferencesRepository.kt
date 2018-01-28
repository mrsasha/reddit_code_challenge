package info.sasasekulic.redditcodechallenge.repositories

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepository @Inject constructor(context: Context) : IPreferencesRepository {

    companion object {
        val PREFS_NAME = "REDDIT_PREFS"

        val TAG_ARTICLE_ID = "ARTICLE_ID"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun setSavedArticleId(articleId: String) {
        sharedPreferences.edit().putString(TAG_ARTICLE_ID, articleId).apply()
    }

    override fun getSavedArticleId(): String = sharedPreferences.getString(TAG_ARTICLE_ID, "")

}