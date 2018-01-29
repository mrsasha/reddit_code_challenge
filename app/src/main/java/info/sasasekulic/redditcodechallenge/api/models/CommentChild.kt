package info.sasasekulic.redditcodechallenge.api.models

data class CommentChild(
        val kind: String,
        val data: CommentData
) : Child {
    companion object {
        val NAME = "t1"
    }

    data class CommentData(
            val id: String,
            val author: String,
            val score: Int,
            val created_utc: Int,
            val body_html: String
    )
}