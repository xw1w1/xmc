package labrinth.v2

import com.google.gson.JsonParser
import labrinth.Utils.connect
import labrinth.Utils.sha512
import labrinth.wrappers.Project
import labrinth.wrappers.Version
import java.io.File
import java.util.logging.Logger

object LabrinthAPI {

    /* API Debug Logger */
    val DEBUG_LOGGER: Logger = Logger.getLogger("LabrinthAPI/v2-mini")

    fun getProjectFromHash(file: File): Project {
        val hash = sha512(file)
        val version = getVersionFromHash(hash)
        return getProjectExact(version.id)
    }

    fun getVersionFromHash(file: File): Version {
        return getVersionFromHash(sha512(file))
    }

    fun getVersionFromHash(hash: String): Version {
        val connection = connect("https://api.modrinth.com/v2/version/$hash/")
        if (connection.responseCode in 200..299) {
            DEBUG_LOGGER.info("Mod info found successfully: $hash")
            return Version(JsonParser.parseReader(
                   connection.inputStream.bufferedReader()
            ).asJsonObject)
        }
        throw LabrinthAPIException("Error version project info by hash: $hash")
    }

    fun getProjectExact(id: String): Project {
        val connection = connect("https://api.modrinth.com/v2/project/$id/")
        if (connection.responseCode in 200..299) {
            DEBUG_LOGGER.info("Mod info found successfully by id: $id")
            return Project(JsonParser.parseReader(
                connection.inputStream.bufferedReader()
            ).asJsonObject)
        }
        throw LabrinthAPIException("Error fetching project info by id: $id")
    }

}