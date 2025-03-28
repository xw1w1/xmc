package labrinth

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.io.File
import java.net.HttpURLConnection
import java.net.URI
import java.security.MessageDigest
import kotlin.collections.Map.Entry

import labrinth.v2.LabrinthAPI.DEBUG_LOGGER

object Utils {

    fun connect(path: String, method: String): HttpURLConnection {
        return (URI.create(path).toURL().openConnection() as HttpURLConnection).apply {
            setRequestProperty("Accept", "application/json")
            setRequestProperty("User-Agent", "xw1w1/xmc under construction (ienji.speaker@gmail.com) ")
            requestMethod = method
            connectTimeout = 6000
            readTimeout = 6000
        }.also { it.connect() }
    }

    fun connect(path: String): HttpURLConnection {
        return connect(path, "GET")
    }

    fun sha512(file: File): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-512")
            val fileBytes = file.readBytes()
            val hashBytes = digest.digest(fileBytes)
            hashBytes.joinToString("") { String.format("%02x", it) }
        } catch (ignored: Exception) {
            DEBUG_LOGGER.severe("Error while hashing file: ${file.absolutePath}")
            throw RuntimeException(ignored)
        }
    }

    fun parseStringList(jsonObject: JsonObject): List<String> {
        return forEachEntry(jsonObject) { jsonObject.asString }.toList()
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun <T> forEachEntry(jsonObject: JsonObject, action: (Entry<String, JsonElement>) -> T): Collection<T> {
        return jsonObject.entrySet().map { action.invoke(it) }
    }

}