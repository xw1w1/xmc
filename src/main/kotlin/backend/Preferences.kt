package backend

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.apache.commons.lang3.SystemUtils
import translations.ClientLanguage
import java.io.File

object Preferences {

    private var platform = Platform.WINDOWS

    /* hey but they are not really writable wtf? */
    private var writableSettings: JsonObject = JsonObject()
    private var currentlyUsedLanguage: ClientLanguage = ClientLanguage.forName("en_en")

    fun saveSettingsConfiguration() {}
    fun loadSettingsFromConfiguration() {}

    fun loadPreferences() {
        if (SystemUtils.IS_OS_WINDOWS_10 || SystemUtils.IS_OS_WINDOWS_11) {
            platform = Platform.WINDOWS
            System.loadLibrary("FluentLib")
            System.setProperty("prism.order", "sw")
            System.setProperty("prism.lcdtext", "false")
            System.setProperty("prism.forceUploadingPainter", "true")
            System.setProperty("javafx.animation.fullspeed", "true")
        } else if (SystemUtils.IS_OS_LINUX) {
            platform = Platform.LINUX
        } else if (SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_MAC) {
            platform = Platform.MACOS
        }

        loadConfiguration()
    }

    fun platform() = platform

    fun wasLoadedBefore(): Boolean {
        return File(ResourceManager.getConfigurationsFolder(), "preferences.json").exists()
    }

    fun getCurrentLanguage(): ClientLanguage {
        return currentlyUsedLanguage
    }

    fun setCurrentLanguage(language: ClientLanguage) {
        currentlyUsedLanguage = language
    }

    private fun loadConfiguration() {
        if (!wasLoadedBefore()) {
            loadDefaultConfiguration()
        } else {
            val configsFolder = ResourceManager.getConfigurationsFolder()
            val content = File(configsFolder, "preferences.json").inputStream()
            writableSettings = JsonParser.parseString(content.bufferedReader().readText()).asJsonObject
        }
    }

    private fun loadDefaultConfiguration() {
        val content = this.javaClass.classLoader.getResourceAsStream("defaultconfiguration.json")
            ?: throw RuntimeException("Default configuration is missing, please reinstall the program.")
        writableSettings = JsonParser.parseString(content.bufferedReader().readText()).asJsonObject
    }

    enum class Platform {
        WINDOWS,
        LINUX,
        MACOS
    }
}