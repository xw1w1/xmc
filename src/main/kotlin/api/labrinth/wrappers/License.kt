package api.labrinth.wrappers

import com.google.gson.JsonObject
import java.net.URI
import java.net.URL

/**
 * Wrapped API Response from [api.modrinth.com/v2/](docs.modrinth.com/api/operations/getversion/)
 * @see launcher.backend.labrinth.v2.LabrinthAPIProvider
 */
data class License(     // api response types:
    val id: String,     // string
    val name: String,   // string
    val url: String?    // string
) {
    constructor(jsonObject: JsonObject) : this(
        jsonObject["id"].asString,
        jsonObject["name"].asString,
        jsonObject["url"].asString
    )

    @Suppress("unused", "MemberVisibilityCanBePrivate")
    fun url(): URL? {
        return URI.create(url ?: return null).toURL()
    }
}