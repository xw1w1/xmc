package xmc.launcher.ui.elements

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.util.Duration
import xmc.LauncherApplication

/**
 * Represents a left part of the default controls frame.
 * ```
 *  ╭─────────────────...
 *  │
 *  │        ╭────────...
 *  │
 *  │
 *  │
 *  │
 *  │
 *  │
 *  │  [〨]
 *  │  [〨]
 *  │  [〨]
 *  │  [〨]
 *  │
 *  ╰────────┻──────────...
 * ```
 * This bar includes some basic navigation like windows default window buttons,
 *
 * active instance control and back/forward buttons.
 */
class SideControlsBar(scene: Scene) {

    val bar = VBox()

    companion object {
        const val SIDEBAR_BUTTON_SCALE = 0.7
        const val BUTTON_HEIGHT_PREF_SIZE = 20.0
        const val BUTTON_SIDE_PREF_SIZE = 35.0
        const val FIT_ICON_SIZE = 20.0
    }

    init {
        this.bar.apply {
            alignment = Pos.BOTTOM_LEFT
            padding = Insets(15.0)
            spacing = 15.0

            scene.heightProperty().addListener {_, _, value ->
                prefHeight = value.toDouble() - 65
                layoutY = 65.0
            }

            prefWidth = 75.0

            //val color = Color.rgb(0, 191, 255, 0.9)
            //background = Background(BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY))

            children.addAll(
                Button().apply {
                    prefHeight = (BUTTON_SIDE_PREF_SIZE + BUTTON_HEIGHT_PREF_SIZE) * SIDEBAR_BUTTON_SCALE
                    prefWidth = (BUTTON_SIDE_PREF_SIZE) * SIDEBAR_BUTTON_SCALE
                    textFill = LauncherApplication.DEFAULT_TEXT_COLOR
                    font = Font.font(LauncherApplication.DEFAULT_FONT_NAME, FontWeight.BOLD, 16.0)
                    graphic = ImageView(Image("embedded/icons/Home.png")).apply {
                        fitWidth = FIT_ICON_SIZE
                        fitHeight = FIT_ICON_SIZE
                        pickOnBoundsProperty().set(true)
                        preserveRatioProperty().set(true)
                    }
                    tooltip = TranslatableTooltip("xmc.launcher.button.home.tooltip").tooltip().apply {
                        showDelay = Duration.millis(250.0)
                    }
                    setOnMouseClicked {
                        LauncherApplication.activeNavBar.rootPageName.key = "xmc.launcher.ui-page.home"
                        LauncherApplication.activeNavBar.rootPageName.translate()
                    }
                },

                Button().apply {
                    prefHeight = (BUTTON_SIDE_PREF_SIZE + BUTTON_HEIGHT_PREF_SIZE) * SIDEBAR_BUTTON_SCALE
                    prefWidth = (BUTTON_SIDE_PREF_SIZE) * SIDEBAR_BUTTON_SCALE
                    textFill = LauncherApplication.DEFAULT_TEXT_COLOR
                    font = Font.font(LauncherApplication.DEFAULT_FONT_NAME, FontWeight.BOLD, 16.0)
                    graphic = ImageView(Image("embedded/icons/Settings.png")).apply {
                        fitWidth = FIT_ICON_SIZE
                        fitHeight = FIT_ICON_SIZE
                        pickOnBoundsProperty().set(true)
                        preserveRatioProperty().set(true)
                    }
                    tooltip = TranslatableTooltip("xmc.launcher.button.settings.tooltip").tooltip().apply {
                        showDelay = Duration.millis(250.0)
                    }

                    setOnMouseClicked {
                        LauncherApplication.activeNavBar.rootPageName.key = "xmc.launcher.ui-page.settings"
                        LauncherApplication.activeNavBar.rootPageName.translate()
                    }
                },

                Button().apply {
                    prefHeight = (BUTTON_SIDE_PREF_SIZE + BUTTON_HEIGHT_PREF_SIZE) * SIDEBAR_BUTTON_SCALE
                    prefWidth = (BUTTON_SIDE_PREF_SIZE) * SIDEBAR_BUTTON_SCALE
                    textFill = LauncherApplication.DEFAULT_TEXT_COLOR
                    font = Font.font(LauncherApplication.DEFAULT_FONT_NAME, FontWeight.BOLD, 16.0)
                    graphic = ImageView(Image("embedded/icons/Plus.png")).apply {
                        fitWidth = FIT_ICON_SIZE
                        fitHeight = FIT_ICON_SIZE
                        pickOnBoundsProperty().set(true)
                        preserveRatioProperty().set(true)
                    }
                    tooltip = TranslatableTooltip("xmc.launcher.button.new-instance.tooltip").tooltip().apply {
                        showDelay = Duration.millis(250.0)
                    }

                    setOnMouseClicked {
                        LauncherApplication.activeNavBar.rootPageName.key = "xmc.launcher.ui-page.new-instance"
                        LauncherApplication.activeNavBar.rootPageName.translate()
                    }
                },

                )
        }

    }
}