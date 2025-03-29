package api.labrinth.wrappers

import com.google.gson.JsonObject
import api.labrinth.Utils
import java.util.LinkedList

/**
 * Wrapped API Response from [api.modrinth.com/v2/version/](docs.modrinth.com/api/operations/getversion/)
 * @see launcher.backend.labrinth.v2.LabrinthAPI
 */
data class Version(                        // api response types:
    val id: String,                        // string base62
    val name: String,                      // string

    val projectID: String,                 // string base62
    val authorID: String,                  // string base62

    val versionNumber: String,             // string
    val changelog: String,                 // string

    val dependencies: List<Dependency>,    // Array<object>
    val gameVersions: List<String>,        // Array<string>
) {
    constructor(jsonObject: JsonObject) : this(
        jsonObject["id"].asString,
        jsonObject["name"].asString,

        jsonObject["project_id"].asString,
        jsonObject["author_id"].asString,

        jsonObject["version_number"].asString,
        jsonObject["changelog"].asString,

        parseDependencies(jsonObject.getAsJsonObject("dependencies")),
        Utils.parseStringList(jsonObject.getAsJsonObject("game_versions"))
    )

    companion object {
        private fun parseDependencies(jsonObject: JsonObject): List<Dependency> {
            val list = LinkedList<Dependency>()
            jsonObject.entrySet().forEach {
                val element = it.value.asJsonObject
                val dependencyType = Dependency.DependencyType.valueOf(element["dependency_type"].asString.uppercase())
                list.add(
                    Dependency(
                        element["version_id"].asString,
                        element["project_id"].asString,
                        element["file_name"].asString,
                        dependencyType
                    )
                )
            }
            return list
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Version

        if (name != other.name) return false
        if (versionNumber != other.versionNumber) return false
        if (changelog != other.changelog) return false
        if (dependencies != other.dependencies) return false

        return true
    }
    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + versionNumber.hashCode()
        result = 31 * result + changelog.hashCode()
        result = 31 * result + dependencies.hashCode()
        return result
    }

    /**
     * Dependency object stored in [Version.dependencies]
     */
    data class Dependency(
        val versionID: String,             // SHA512 string
        val projectID: String,             // SHA512 string
        val jarFileName: String,           // string
        val dependencyType: DependencyType // string
    ) {
        enum class DependencyType {
            REQUIRED,                      // string
            OPTIONAL,                      // string
            INCOMPATIBLE,                  // string
            EMBEDDED                       // string
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Dependency

            if (versionID != other.versionID) return false
            if (projectID != other.projectID) return false
            if (jarFileName != other.jarFileName) return false
            if (dependencyType != other.dependencyType) return false

            return true
        }

        override fun hashCode(): Int {
            var result = versionID.hashCode()
            result = 31 * result + projectID.hashCode()
            result = 31 * result + jarFileName.hashCode()
            result = 31 * result + dependencyType.hashCode()
            return result
        }
    }
}