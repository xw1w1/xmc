package xmc

import javafx.application.Application
import org.apache.commons.lang3.SystemUtils
import x64.WindowsLauncherApplication
import xmc.launcher.backend.Preferences
import xmc.translations.I18n

fun main(args: Array<String>) {
    println("Hello world!")
    XMC.launch(args)
}

object XMC {

    private var startupArgs = emptyArray<String>()
    private lateinit var instance: LauncherApplication

    fun launch(args: Array<String>) {
        this.startupArgs = args

        I18n.loadTranslationFiles()
        I18n.loadTranslations()

        Preferences.loadPreferences()

        if (SystemUtils.IS_OS_WINDOWS_10 || SystemUtils.IS_OS_WINDOWS_11) {
            Application.launch(WindowsLauncherApplication::class.java)
        } else if (SystemUtils.IS_OS_LINUX) {
            throw UnsupportedOperationException("Not yet implemented")
        } else if (SystemUtils.IS_OS_MAC) {
            throw UnsupportedOperationException("Not yet implemented")
        }
    }

    fun startupArgs(): Array<String> {
        return this.startupArgs
    }

    fun setInstance(launcherApplication: LauncherApplication) {
        this.instance = launcherApplication
    }

    fun getInstance(): LauncherApplication {
        return this.instance
    }
}