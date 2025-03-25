package x64

import javafx.application.Application
import xmc.LauncherInitializer

class WindowsLauncherInitializer : LauncherInitializer() {
    override fun loadPlatformedInitializer(args: List<String>) {
        Application.launch(WindowsLauncherApplication::class.java)
    }
}