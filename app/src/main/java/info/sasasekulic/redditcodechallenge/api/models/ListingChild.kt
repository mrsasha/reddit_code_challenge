package info.sasasekulic.redditcodechallenge.api.models

data class ListingChild(
        val kind: String,
        val data: ListingData
) : Child {
    companion object {
        val NAME = "Listing"
    }

    data class ListingData(
            val after: Any,
            val whitelist_status: String,
            val modhash: String,
            val dist: Int,
            val children: List<Child>
    )
}