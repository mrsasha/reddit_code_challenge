package info.sasasekulic.redditcodechallenge.api

import info.sasasekulic.redditcodechallenge.api.models.Child
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditApiService {

    @GET("/comments/{articleId}")
    fun getArticleComments(@Path("articleId") articleId: String): Observable<List<Child>>

}