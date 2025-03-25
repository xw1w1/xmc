package xmc.translations

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.InputStreamReader

/**
 * Simple wrapper for single registered language in languages.json
 */
class ClientLanguage private constructor(val code: String) {

    constructor(code: String, filePath: String) : this(code) {
        this.translationFilePath = filePath
    }

    private var translationFilePath = "$FILE_PATH$code.json"

    fun loadTranslationFile(): JsonObject {
        val content = this::class.java.classLoader.getResourceAsStream(translationFilePath())
            ?: throw RuntimeException("Language files not found, please reinstall the program!")
        val reader = InputStreamReader(content, "UTF-8")
        val rawJSON = reader.readText()
        reader.close()
        content.close()
        return JsonParser.parseString(rawJSON) as JsonObject
    }

    private fun translationFilePath() = translationFilePath

    companion object {
        const val FILE_PATH = "embedded/translations/"

        val RUSSIAN = ClientLanguage("ru_ru")
        val ENGLISH = ClientLanguage("en_en")

        fun forName(code: String): ClientLanguage {
            val language = ClientLanguage(code)
            val translations = language.loadTranslationFile()
            I18n.addLanguageRegistry(language, translations)
            return language
        }

        fun custom(code: String, filePath: String): ClientLanguage {
            return ClientLanguage(code, filePath)
        }
    }
}