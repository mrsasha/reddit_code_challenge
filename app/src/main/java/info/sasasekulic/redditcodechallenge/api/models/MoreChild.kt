package info.sasasekulic.redditcodechallenge.api.models

data class MoreChild(
        val kind: String,
        val data: Any
) : Child {
    companion object {
        val NAME = "more"
    }

}