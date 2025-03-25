package xmc

import com.google.gson.JsonParser

// maybe i should merge this one with Bootstrap?
@Suppress("warnings")
object Version {

    fun getVersion(): VersionInfo {
        val input = Version.javaClass.classLoader.getResourceAsStream("version_info.json")
        val json = JsonParser.parseString(input.bufferedReader().readText()).asJsonObject
        return VersionInfo(json.get("version").asString, json.get("commit").asString)
    }

    data class VersionInfo(
        val versionNum: String,
        val gitCommitHash: String
    )
}