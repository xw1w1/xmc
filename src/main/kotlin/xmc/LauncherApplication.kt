package xmc

import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.effect.InnerShadow
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.stage.Stage
import backend.Preferences
import old.ui.elements.NavigationBar
import old.ui.elements.SideControlsBar
import frontend.element.TranslatableText

abstract class LauncherApplication : Application() {

    val root: AnchorPane = AnchorPane().apply {
        background = Background(BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))
    }

    val rootScene: Scene = Scene(this.root, 800.0, 600.0).apply {
        val platform = Preferences.platform().name.lowercase()
        stylesheets.add("embedded/styles/$platform/fluent-dark.css")
        fill = Color.BLACK
    }

    var contentPane: Parent = HBox().apply {
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

    val navigationBar: NavigationBar = NavigationBar(rootScene, TITLE_TEXT, Image("favicon.png"))
    val controlsBar: SideControlsBar = SideControlsBar(rootScene)

    init {
        root.children.addAll(navigationBar.header, controlsBar.bar)
        AnchorPane.setLeftAnchor(controlsBar.bar, 0.0)
    }

    fun setPage(parent: Parent) {
        root.children.clear()
        root.children.addAll(navigationBar.header, controlsBar.bar, parent)
        AnchorPane.setTopAnchor(parent, 65.0)
        AnchorPane.setLeftAnchor(parent, 75.0)
        AnchorPane.setBottomAnchor(parent, 0.0)
        AnchorPane.setRightAnchor(parent, 0.0)
    }

    abstract fun applyPlatformedWindowDecorations(stage: Stage)

    companion object {
        lateinit var activeNavBar: NavigationBar
        lateinit var activeSideBar: SideControlsBar

        const val DEFAULT_FONT_NAME = "Segoe UI"

        val TITLE_TEXT: String = TranslatableText("xmc.product.name").text().text
        val DEFAULT_TEXT_COLOR: Color = Color.rgb(199, 198, 198, 1.0)
    }
}