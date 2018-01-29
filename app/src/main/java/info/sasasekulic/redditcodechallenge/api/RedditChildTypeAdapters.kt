package info.sasasekulic.redditcodechallenge.api

import android.util.Log
import com.github.salomonbrys.kotson.jsonDeserializer
import com.google.gson.JsonObject
import info.sasasekulic.redditcodechallenge.api.models.*

val childDeserializer = jsonDeserializer {
    var child: Child = BaseChild("", "")
    val childType = it.json.asJsonObject.get("kind").asString

    try {
        if (childType.isNotEmpty()) {
            when (childType) {
                CommentChild.NAME -> {
                    val dataObject = it.json.asJsonObject.get("data")
                    val replies = dataObject.asJsonObject.get("replies")

                    if (replies is JsonObject) {
                        child = it.context.deserialize<Child>(it.json, CommentChildHierarchical::class.java)
                    } else {
                        child = it.context.deserialize<Child>(it.json, CommentChild::class.java)
                    }
                }
                ListingChild.NAME -> {
                    child = it.context.deserialize<Child>(it.json, ListingChild::class.java)
                }
                LinkChild.NAME -> {
                    child = it.context.deserialize<Child>(it.json, LinkChild::class.java)
                }
                MoreChild.NAME -> {
                    child = it.context.deserialize<Child>(it.json, MoreChild::class.java)
                }
                else -> {
                    Log.w("ChildListSerializer", "childType $childType not found!")
                    child = it.context.deserialize<Child>(it.json, BaseChild::class.java)
                }
            }
        } else {
            child = it.context.deserialize<Child>(it.json, BaseChild::class.java)
        }
    } catch (e: Exception) {
        Log.e("ChildListSerializer", e.toString())
    }

    child
}