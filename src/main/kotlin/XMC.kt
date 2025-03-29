import javafx.application.Application
import org.apache.commons.lang3.SystemUtils
import backend.Preferences
import translations.I18n

fun main(args: Array<String>) {
    println("Hello world!")
    XMC.launch(args)
}

object XMC {

    private var startupArgs = emptyArray<String>()
    private lateinit var instance: Launcher

    fun launch(args: Array<String>) {
        startupArgs = args

        I18n.loadTranslationFiles()
        I18n.loadTranslations()

        Preferences.loadPreferences()

        if (SystemUtils.IS_OS_WINDOWS_10 || SystemUtils.IS_OS_WINDOWS_11) {
            Application.launch(Launcher::class.java)
        } else if (SystemUtils.IS_OS_LINUX) {
            throw UnsupportedOperationException("Not yet implemented")
        } else if (SystemUtils.IS_OS_MAC) {
            throw UnsupportedOperationException("Not yet implemented")
        }
    }

    fun startupArgs(): Array<String> {
        return startupArgs
    }

    fun setInstance(launcherApplication: Launcher) {
        instance = launcherApplication
    }

    fun getInstance(): Launcher {
        return instance
    }
}