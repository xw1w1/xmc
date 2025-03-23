package xmc

import xmc.launcher.backend.Preferences
import xmc.translations.I18n
import xmc.launcher.unix.LinuxLauncherInitializer
import xmc.launcher.windows.WindowsLauncherInitializer

fun main(args: Array<String>) {
    println("Hello World!")

    val arguments = args.toList()

    Preferences.loadPreferences()

    I18n.loadLanguagesRegistry()
    I18n.loadTranslationsDictionary()

    if (Preferences.platform() == Preferences.Platform.WINDOWS)
        WindowsLauncherInitializer().loadPlatformedInitializer(arguments)
    else if (Preferences.platform() == Preferences.Platform.LINUX)
        LinuxLauncherInitializer().loadPlatformedInitializer(arguments)
}