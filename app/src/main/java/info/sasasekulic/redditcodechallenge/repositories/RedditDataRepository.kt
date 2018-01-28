package info.sasasekulic.redditcodechallenge.repositories

import info.sasasekulic.redditcodechallenge.api.RedditApiService
import info.sasasekulic.redditcodechallenge.api.models.Child
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditDataRepository @Inject constructor(apiService: RedditApiService) : IRedditDataRepository {

    private val redditApiService = apiService

    override fun getArticleComments(articleId: String): Observable<List<Child>> = redditApiService.getArticleComments(articleId)

}