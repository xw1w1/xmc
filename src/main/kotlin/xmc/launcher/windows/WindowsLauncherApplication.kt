package xmc.launcher.windows

import javafx.stage.Stage
import xmc.LauncherApplication

class WindowsLauncherApplication : LauncherApplication() {

    override fun start(stage: Stage) {
        stage.title = "xmc-test | Running args: Not Specified"
    }
}