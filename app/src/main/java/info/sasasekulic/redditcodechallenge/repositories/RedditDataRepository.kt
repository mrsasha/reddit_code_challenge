package info.sasasekulic.redditcodechallenge.repositories

import info.sasasekulic.redditcodechallenge.api.RedditApiService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditDataRepository @Inject constructor(apiService: RedditApiService) : IRedditDataRepository {

    private val redditApiService = apiService

    override fun getArticleComments(articleId: String): Observable<List<Object>> = redditApiService.getArticleComments(articleId)

}