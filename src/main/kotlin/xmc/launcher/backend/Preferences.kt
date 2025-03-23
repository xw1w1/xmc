package xmc.launcher.backend

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.apache.commons.lang3.SystemUtils
import xmc.translations.ClientLanguage
import java.io.File

object Preferences {

    private var platform = Platform.WINDOWS

    private var writableSettings: JsonObject = JsonObject()
    private var currentlyUsedLanguage: ClientLanguage = ClientLanguage.ENGLISH

    fun saveSettingsConfiguration() {}
    fun loadSettingsFromConfiguration() {}

    fun loadPreferences() {
        if (SystemUtils.IS_OS_WINDOWS) {
            this.platform = Platform.WINDOWS
        } else if (SystemUtils.IS_OS_LINUX) {
            this.platform = Platform.LINUX
        } else if (SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_MAC) {
            this.platform = Platform.MACOS
        }

        this.loadConfiguration()
    }

    fun platform() = this.platform

    fun wasLoadedBefore(): Boolean {
        return File(this.getConfigurationsFolder(), "preferences.ini").exists()
    }

    // Should be set to Program Files by installer or other in linux
    // Expose for future work with configuration
    fun getDataFolder(): File {
        return File(Preferences::class.java.protectionDomain.codeSource.location.toURI())
    }

    fun getCurrentLanguage(): ClientLanguage {
        return this.currentlyUsedLanguage
    }

    fun setCurrentLanguage(language: ClientLanguage) {
        this.currentlyUsedLanguage = language
    }

    // should be called first
    private fun loadConfiguration() {
        if (!wasLoadedBefore()) {
            this.loadDefaultConfiguration()
        } else {
            val configsFolder = this.getConfigurationsFolder()
            val content = File(configsFolder, "preferences.json").inputStream()
            this.writableSettings = JsonParser.parseString(content.bufferedReader().readText()).asJsonObject
        }
    }

    private fun loadDefaultConfiguration() {
        val content = this.javaClass.classLoader.getResourceAsStream("defaultconfiguration.json")
            ?: throw RuntimeException("Default configuration is missing, please reinstall the program.")
        this.writableSettings = JsonParser.parseString(content.bufferedReader().readText()).asJsonObject
    }

    private fun getConfigurationsFolder(): File {
        return File(this.getDataFolder(), "preferences")
    }

    enum class Platform {
        WINDOWS,
        LINUX,
        MACOS
    }
}