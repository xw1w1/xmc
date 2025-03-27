package xmc.launcher.backend

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.apache.commons.lang3.SystemUtils
import xmc.translations.ClientLanguage
import java.io.File

object Preferences {

    private var platform = Platform.WINDOWS

    private var writableSettings: JsonObject = JsonObject()
    private var currentlyUsedLanguage: ClientLanguage = ClientLanguage.forName("en_en")

    fun saveSettingsConfiguration() {}
    fun loadSettingsFromConfiguration() {}

    fun loadPreferences() {
        if (SystemUtils.IS_OS_WINDOWS_10 || SystemUtils.IS_OS_WINDOWS_11) {
            this.platform = Platform.WINDOWS

            System.loadLibrary("FluentLib")
            System.setProperty("prism.lcdtext", "false")
            System.setProperty("javafx.animation.fullspeed", "true")
        } else if (SystemUtils.IS_OS_LINUX) {
            this.platform = Platform.LINUX
        } else if (SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_MAC) {
            this.platform = Platform.MACOS
        }

        this.loadConfiguration()
    }

    fun platform() = this.platform

    fun wasLoadedBefore(): Boolean {
        return File(ResourceManager.getConfigurationsFolder(), "preferences.json").exists()
    }

    fun getCurrentLanguage(): ClientLanguage {
        return this.currentlyUsedLanguage
    }

    fun setCurrentLanguage(language: ClientLanguage) {
        this.currentlyUsedLanguage = language
    }

    private fun loadConfiguration() {
        if (!wasLoadedBefore()) {
            this.loadDefaultConfiguration()
        } else {
            val configsFolder = ResourceManager.getConfigurationsFolder()
            val content = File(configsFolder, "preferences.json").inputStream()
            this.writableSettings = JsonParser.parseString(content.bufferedReader().readText()).asJsonObject
        }
    }

    private fun loadDefaultConfiguration() {
        val content = this.javaClass.classLoader.getResourceAsStream("defaultconfiguration.json")
            ?: throw RuntimeException("Default configuration is missing, please reinstall the program.")
        this.writableSettings = JsonParser.parseString(content.bufferedReader().readText()).asJsonObject
    }

    enum class Platform {
        WINDOWS,
        LINUX,
        MACOS
    }
}