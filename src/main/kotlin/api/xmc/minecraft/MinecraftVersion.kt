package api.xmc.minecraft

import com.google.gson.JsonObject
import java.net.URI
import java.net.URL

data class MinecraftVersion(
    val id: String,
    val releaseType: VersionType,
    val downloadURL: URL
) {
    constructor(jsonObject: JsonObject) : this(
        id = jsonObject["id"].asString,
        releaseType = VersionType.valueOf(jsonObject["type"].asString.uppercase()),
        downloadURL = URI.create(jsonObject["url"].asString).toURL()
    )

}