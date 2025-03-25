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
        private const val SIDEBAR_BUTTON_SCALE = 0.7
        private const val BUTTON_HEIGHT_PREF_SIZE = 20.0
        private const val BUTTON_SIDE_PREF_SIZE = 35.0
        private const val FIT_ICON_SIZE = 20.0
    }

    init {
        this.bar.apply {
            alignment = Pos.BOTTOM_LEFT
            padding = Insets(15.0)
            spacing = 15.0

            scene.heightProperty().addListener {_, _, value ->
                prefHeight = value.toDouble() - 60
                layoutY = 60.0
            }

            prefWidth = 75.0

            background = Background(BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))

            children.addAll(
                Button().apply {
                    prefHeight = (xmc.launcher.ui.elements.SideControlsBar.Companion.BUTTON_SIDE_PREF_SIZE + xmc.launcher.ui.elements.SideControlsBar.Companion.BUTTON_HEIGHT_PREF_SIZE) * xmc.launcher.ui.elements.SideControlsBar.Companion.SIDEBAR_BUTTON_SCALE
                    prefWidth = (xmc.launcher.ui.elements.SideControlsBar.Companion.BUTTON_SIDE_PREF_SIZE) * xmc.launcher.ui.elements.SideControlsBar.Companion.SIDEBAR_BUTTON_SCALE
                    textFill = LauncherApplication.DEFAULT_TEXT_COLOR
                    font = Font.font(LauncherApplication.DEFAULT_FONT_NAME, FontWeight.BOLD, 16.0)
                    graphic = ImageView(Image("embedded/icons/Home.png")).apply {
                        fitWidth = xmc.launcher.ui.elements.SideControlsBar.Companion.FIT_ICON_SIZE
                        fitHeight = xmc.launcher.ui.elements.SideControlsBar.Companion.FIT_ICON_SIZE
                        pickOnBoundsProperty().set(true)
                        preserveRatioProperty().set(true)
                    }
                    tooltip = xmc.launcher.ui.elements.TranslatableTooltip("xmc.launcher.button.home.tooltip").tooltip().apply {
                        showDelay = Duration.millis(250.0)
                    }
                    setOnMouseClicked {
                        LauncherApplication.activeNavBar.rootPageName.key = "xmc.launcher.ui-page.home"
                        LauncherApplication.activeNavBar.rootPageName.translate()
                    }
                },

                Button().apply {
                    prefHeight = (xmc.launcher.ui.elements.SideControlsBar.Companion.BUTTON_SIDE_PREF_SIZE + xmc.launcher.ui.elements.SideControlsBar.Companion.BUTTON_HEIGHT_PREF_SIZE) * xmc.launcher.ui.elements.SideControlsBar.Companion.SIDEBAR_BUTTON_SCALE
                    prefWidth = (xmc.launcher.ui.elements.SideControlsBar.Companion.BUTTON_SIDE_PREF_SIZE) * xmc.launcher.ui.elements.SideControlsBar.Companion.SIDEBAR_BUTTON_SCALE
                    textFill = LauncherApplication.DEFAULT_TEXT_COLOR
                    font = Font.font(LauncherApplication.DEFAULT_FONT_NAME, FontWeight.BOLD, 16.0)
                    graphic = ImageView(Image("embedded/icons/Settings.png")).apply {
                        fitWidth = xmc.launcher.ui.elements.SideControlsBar.Companion.FIT_ICON_SIZE
                        fitHeight = xmc.launcher.ui.elements.SideControlsBar.Companion.FIT_ICON_SIZE
                        pickOnBoundsProperty().set(true)
                        preserveRatioProperty().set(true)
                    }
                    tooltip = xmc.launcher.ui.elements.TranslatableTooltip("xmc.launcher.button.settings.tooltip").tooltip().apply {
                        showDelay = Duration.millis(250.0)
                    }

                    setOnMouseClicked {
                        LauncherApplication.activeNavBar.rootPageName.key = "xmc.launcher.ui-page.settings"
                        LauncherApplication.activeNavBar.rootPageName.translate()
                    }
                },

                Button().apply {
                    prefHeight = (xmc.launcher.ui.elements.SideControlsBar.Companion.BUTTON_SIDE_PREF_SIZE + xmc.launcher.ui.elements.SideControlsBar.Companion.BUTTON_HEIGHT_PREF_SIZE) * xmc.launcher.ui.elements.SideControlsBar.Companion.SIDEBAR_BUTTON_SCALE
                    prefWidth = (xmc.launcher.ui.elements.SideControlsBar.Companion.BUTTON_SIDE_PREF_SIZE) * xmc.launcher.ui.elements.SideControlsBar.Companion.SIDEBAR_BUTTON_SCALE
                    textFill = LauncherApplication.DEFAULT_TEXT_COLOR
                    font = Font.font(LauncherApplication.DEFAULT_FONT_NAME, FontWeight.BOLD, 16.0)
                    graphic = ImageView(Image("embedded/icons/Plus.png")).apply {
                        fitWidth = xmc.launcher.ui.elements.SideControlsBar.Companion.FIT_ICON_SIZE
                        fitHeight = xmc.launcher.ui.elements.SideControlsBar.Companion.FIT_ICON_SIZE
                        pickOnBoundsProperty().set(true)
                        preserveRatioProperty().set(true)
                    }
                    tooltip = xmc.launcher.ui.elements.TranslatableTooltip("xmc.launcher.button.new-instance.tooltip").tooltip().apply {
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