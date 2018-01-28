package info.sasasekulic.redditcodechallenge.repositories

interface IPreferencesRepository {

    fun getSavedArticleId(): String

    fun setSavedArticleId(articleId: String)
}