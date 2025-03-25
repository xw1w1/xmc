import x64.WindowsLauncherInitializer
import xmc.launcher.backend.Preferences
import xmc.translations.I18n

fun main(args: Array<String>) {
    println("Hello World!")

    val arguments = args.toList()
    // throw this for now ^^

    Preferences.loadPreferences()

    I18n.loadLanguagesRegistry()
    I18n.loadTranslationsDictionary()

    if (Preferences.platform() == Preferences.Platform.WINDOWS)
        WindowsLauncherInitializer().loadPlatformedInitializer(arguments)
    else if (Preferences.platform() == Preferences.Platform.LINUX) return
}