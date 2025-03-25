package xmc.translations

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sun.javafx.logging.Logger
import java.io.InputStreamReader
import java.util.LinkedList

object I18n {

    private val languages: LinkedHashMap<String, ClientLanguage> = LinkedHashMap()
    private val dictionary: LinkedHashMap<ClientLanguage, JsonObject> = LinkedHashMap()

    private val translatables: LinkedList<Translatable> = LinkedList()

    fun loadLanguagesRegistry() {
        //Fallback & default languages
        languages["ru_ru"] = ClientLanguage.RUSSIAN
        languages["en_en"] = ClientLanguage.ENGLISH

        val source = this.javaClass.classLoader.getResourceAsStream("embedded/translations/languages.json")
            ?: throw RuntimeException("Language files not found, please reinstall the program!")
        val reader = InputStreamReader(source)
        val content = reader.readText()
        source.close()
        reader.close()

        val json = JsonParser.parseString(content) as JsonObject
        json.remove("ru_ru")
        json.remove("en_en")

        json.asMap().forEach {
            languages[it.key] = ClientLanguage.forName(it.key)
        }
        java.util.logging.Logger.getLogger("I18n").info("""
            Just loaded ${languages.size} languages!
            Map: $languages
        """.trimIndent())
    }

    fun loadTranslationsDictionary() {
        languages.forEach { loadTranslation(it.value) }
    }

    fun addLanguageRegistry(language: ClientLanguage, reg: JsonObject) {
        dictionary[language] = reg
        languages[language.code] = language
    }

    fun onLanguageChange() { translatables.forEach(Translatable::translate) }

    fun translate(translationKey: String, language: ClientLanguage): String {
        return dictionary[language]!!.get(translationKey).asString ?: translationKey
    }

    private fun loadTranslation(language: ClientLanguage) {
        val file = language.loadTranslationFile()
        dictionary[language] = file
    }
}