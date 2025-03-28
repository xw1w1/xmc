package labrinth.wrappers

import com.google.gson.JsonObject
import labrinth.Utils

/**
 * Wrapped API Response from [api.modrinth.com/v2/project/](docs.modrinth.com/api/operations/getproject/)
 * @see launcher.backend.labrinth.v2.LabrinthAPI
 */
data class Project(                                // api response types:
    val slug: String,                              // string
    val id: String,                                // base62 string
    val title: String,                             // string
    val description: String,                       // string

    val team: String,                              // base62 string
    val license: License,                          // object

    val rawVersions: List<String>,                 // Array<string>
    val gameVersions: List<String>,                // Array<string>
    val rawLoaders: List<String>,                  // Array<string>

    val publicationDate: String,                   // string
    val updatedDate: String,                       // string

    val categories: List<String>,                  // Array<string>
    val environment: List<SupportedEnvironment>,   // server-side & client-side string

    val projectType: Type,                         // string
    val downloads: Int,                            // integer
    val followers: Int,                            // integer
    val color: Int,                                // integer

    val body: String,                              // string
    val status: Status,                            // string
    val requestedStatus: Status,                   // string
    val additionalCategories: List<String>,        // Array<string>

    val iconURL: String,                           // string

    val issuesURL: String,                         // string
    val sourceURL: String,                         // string
    val wikiURL: String,                           // string
    val discordURL: String,                        // string
    val donationsURL: List<String>                 // string
) {

    constructor(jsonObject: JsonObject) : this(
        jsonObject["slug"].asString,
        jsonObject["id"].asString,
        jsonObject["title"].asString,
        jsonObject["description"].asString,

        jsonObject["team"].asString,
        License(jsonObject["license"].asJsonObject),

        Utils.parseStringList(jsonObject.getAsJsonObject("versions")),
        Utils.parseStringList(jsonObject.getAsJsonObject("game_versions")),
        Utils.parseStringList(jsonObject.getAsJsonObject("loaders")),

        jsonObject["published"].asString,
        jsonObject["updated"].asString,

        Utils.parseStringList(jsonObject.getAsJsonObject("categories")),
        SupportedEnvironment.find(jsonObject["client_side"].asString, jsonObject["server_side"].asString),

        Type.valueOf(jsonObject["project_type"].asString.uppercase()),
        jsonObject["downloads"].asInt,
        jsonObject["followers"].asInt,
        jsonObject["color"].asInt,

        jsonObject["body"].asString,

        Status.valueOf(jsonObject["status"].asString.uppercase()),
        Status.valueOf(jsonObject["requested_status"].asString.uppercase()),
        Utils.parseStringList(jsonObject.getAsJsonObject("additional_categories")),

        jsonObject["icon_url"].asString,
        jsonObject["issues_url"].asString,
        jsonObject["source_url"].asString,
        jsonObject["wiki_url"].asString,
        jsonObject["discord_url"].asString,
        Utils.parseStringList(jsonObject.getAsJsonObject("donation_urls"))
    )

    enum class Type(val key: String) {
        MOD("mod"),
        MODPACK("modpack"),
        RESOURCEPACK("resourcepack"),
        SHADER("shader")
    }

    enum class Status(val key: String) {
        APPROVED("approved"),
        ARCHIVED("archived"),
        REJECTED("rejected"),
        DRAFT("draft"),
        UNLISTED("unlisted"),
        PROCESSING("processing"),
        WITHHELD("withheld"),
        SCHEDULED("scheduled"),
        PRIVATE("private"),
        UNKNOWN("unknown")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Project

        if (color != other.color) return false
        if (slug != other.slug) return false
        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (team != other.team) return false
        if (publicationDate != other.publicationDate) return false
        if (updatedDate != other.updatedDate) return false
        if (projectType != other.projectType) return false
        if (body != other.body) return false
        if (iconURL != other.iconURL) return false
        if (donationsURL != other.donationsURL) return false

        return true
    }
    override fun hashCode(): Int {
        var result = slug.hashCode()
        result = 31 * result + slug.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + team.hashCode()
        result = 31 * result + projectType.hashCode()
        return result
    }
}