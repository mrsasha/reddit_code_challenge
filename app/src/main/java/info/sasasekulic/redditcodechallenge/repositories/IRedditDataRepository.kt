package info.sasasekulic.redditcodechallenge.repositories

import info.sasasekulic.redditcodechallenge.api.models.Child
import io.reactivex.Observable

interface IRedditDataRepository {

    fun getArticleComments(articleId: String): Observable<List<Child>>

}