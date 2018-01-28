package info.sasasekulic.redditcodechallenge.repositories

import io.reactivex.Observable

interface IRedditDataRepository {

    fun getArticleComments(articleId: String): Observable<List<Object>>
    
}