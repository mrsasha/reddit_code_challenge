package info.sasasekulic.redditcodechallenge.api.models

data class LinkChild(
        val kind: String,
        val data: LinkData
) : Child {
    companion object {
        val NAME = "t3"
    }

    data class LinkData(
            val url: String,
            val title: String,
            val thumbnail: String
    )

}