package x64

import earth.groundctrl.fluent.lib.Windows
import javafx.stage.Stage
import javafx.stage.StageStyle
import xmc.LauncherApplication

class WindowsLauncherApplication : LauncherApplication() {

    override fun applyPlatformedWindowDecorations(stage: Stage) {
        Windows.setMicaFor(stage, true)
        Windows.setHeaderBarFor (TITLE, true)
    }

    override fun start(stage: Stage) {
        activeNavBar = this.navigationBar
        activeSideBar = this.controlsBar

        stage.scene = this.rootScene
        stage.initStyle(StageStyle.UNIFIED)
        stage.title = TITLE

        stage.minWidth = 800.0
        stage.minHeight = 500.0

        stage.show()

        applyPlatformedWindowDecorations(stage)
    }
}