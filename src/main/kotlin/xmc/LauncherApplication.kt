package xmc

import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.effect.InnerShadow
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.stage.Stage
import xmc.launcher.backend.Preferences
import xmc.launcher.ui.elements.NavigationBar
import xmc.launcher.ui.elements.SideControlsBar
import xmc.launcher.ui.elements.TranslatableText

abstract class LauncherApplication : Application() {

    val root: AnchorPane = AnchorPane().apply {
        background = Background(BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))
    }

    val rootScene: Scene = Scene(this.root, 800.0, 600.0).apply {
        val platform = Preferences.platform().name.lowercase()
        stylesheets.add("embedded/styles/$platform/fluent-dark.css")
        fill = Color.BLACK
    }

    val navigationBar: NavigationBar = NavigationBar(rootScene, TITLE, Image("favicon.png"))
    val controlsBar: SideControlsBar = SideControlsBar(rootScene)

    private val contentPane = HBox().apply {
        alignment = Pos.BOTTOM_CENTER

        val fillColorSub = Color.rgb(33, 32, 32, 0.5)

        effect = InnerShadow(10.0, Color.rgb(33, 33, 33, 0.8))
        background = Background(BackgroundFill(fillColorSub, CornerRadii(15.0, 0.0, 0.0, 0.0, false), Insets.EMPTY))
        padding = Insets(20.0)

        children.addAll(
            Label("under construction | xmc@bf4d8b1").apply {
                textFill = DEFAULT_TEXT_COLOR
                font = Font.font("Segoe UI Semibold", FontWeight.SEMI_BOLD, 15.0)
            }
        )
    }

    init {
        root.children.addAll(navigationBar.header, controlsBar.bar,  contentPane)

        AnchorPane.setTopAnchor(contentPane, 65.0)
        AnchorPane.setLeftAnchor(contentPane, 75.0)
        AnchorPane.setBottomAnchor(contentPane, 0.0)
        AnchorPane.setRightAnchor(contentPane, 0.0)

        AnchorPane.setLeftAnchor(controlsBar.bar, 0.0)
    }

    abstract fun applyPlatformedWindowDecorations(stage: Stage)

    companion object {
        lateinit var activeNavBar: NavigationBar
        lateinit var activeSideBar: SideControlsBar

        const val DEFAULT_FONT_NAME = "Segoe UI"

        val TITLE = TranslatableText("xmc.product.name").text().text
        val DEFAULT_TEXT_COLOR: Color = Color.rgb(199, 198, 198, 1.0)
    }
}