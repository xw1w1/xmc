package x64

import earth.groundctrl.fluent.lib.Windows
import javafx.scene.image.Image
import javafx.stage.Stage
import javafx.stage.StageStyle
import xmc.LauncherApplication
import xmc.XMC
import xmc.translations.ClientLanguage
import xmc.translations.I18n

class WindowsLauncherApplication : LauncherApplication() {

    override fun applyPlatformedWindowDecorations(stage: Stage) {
        Windows.setMicaFor(stage, true)
        val title = I18n.translate("xmc.product.name.full", ClientLanguage.ENGLISH)
        Windows.setHeaderBarFor(title, true)
    }

    override fun start(stage: Stage) {
        XMC.setInstance(this)
        activeNavBar = this.navigationBar
        activeSideBar = this.controlsBar

        stage.scene = this.rootScene
        stage.initStyle(StageStyle.UNIFIED)
        stage.title = I18n.translate("xmc.product.name.full", ClientLanguage.ENGLISH)

        stage.icons.add(Image(LauncherApplication::class.java.classLoader.getResourceAsStream("appicon.png")))

        stage.minWidth = 800.0
        stage.minHeight = 500.0

        stage.show()

        applyPlatformedWindowDecorations(stage)
    }
}