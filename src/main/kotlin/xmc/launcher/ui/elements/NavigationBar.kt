package xmc.launcher.ui.elements

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import javafx.stage.Stage
import javafx.util.Duration
import xmc.LauncherApplication
import xmc.launcher.ui.elements.SideControlsBar.Companion.BUTTON_HEIGHT_PREF_SIZE
import xmc.launcher.ui.elements.SideControlsBar.Companion.BUTTON_SIDE_PREF_SIZE
import xmc.launcher.ui.elements.SideControlsBar.Companion.FIT_ICON_SIZE
import xmc.launcher.ui.elements.SideControlsBar.Companion.SIDEBAR_BUTTON_SCALE

/**
 * Represents a top of default controls frame.
 * ```
 *  ╭───────────────────────────────────────────────────────────╮
 *  │  (←)  (→)     ( No instances running )        [ -  x ]    │
 *  │                                                           │
 * ```
 * This bar includes some basic navigation like windows default window buttons,
 *
 * active instance control and back/forward buttons.
 */
class NavigationBar(parent: Scene) {

    val header = HBox()

    var rootPageName = TranslatableLabel("xmc.launcher.ui-page.home").apply {
        label().apply {
            maxWidth = 275.0
            font = Font.font("Unbounded Medium", FontWeight.EXTRA_BOLD, 16.0)
            textFill = LauncherApplication.DEFAULT_TEXT_COLOR
        }
    }

    constructor(parent: Scene, title: String) : this(parent) {
        this.title.text = title
    }

    constructor(parent: Scene, title: String, icon: Image): this(parent) {
        this.title.text = title
        this.icon.image = icon
    }

    private val icon = ImageView()
    private var title = TranslatableLabel("xmc.product.name").label()

    private val languageSelectionButton = Button().apply {
        prefHeight = (BUTTON_SIDE_PREF_SIZE + BUTTON_HEIGHT_PREF_SIZE) * SIDEBAR_BUTTON_SCALE
        prefWidth = (BUTTON_SIDE_PREF_SIZE) * SIDEBAR_BUTTON_SCALE
        textFill = LauncherApplication.DEFAULT_TEXT_COLOR
        font = Font.font(LauncherApplication.DEFAULT_FONT_NAME, FontWeight.BOLD, 16.0)
        graphic = ImageView(Image("embedded/icons/Global.png")).apply {
            fitWidth = FIT_ICON_SIZE
            fitHeight = FIT_ICON_SIZE
            pickOnBoundsProperty().set(true)
            preserveRatioProperty().set(true)
        }
        tooltip = TranslatableTooltip("xmc.launcher.button.global.tooltip").tooltip().apply {
            showDelay = Duration.millis(250.0)
        }
        setOnMouseClicked {

        }
    }

    private var offsetX: Double = 0.0
    private var offsetY: Double = 0.0

    init {
        this.header.apply {
            padding = Insets(10.0)
            spacing = 5.0

            prefHeight = 65.0
            parent.widthProperty().addListener {_, _, value ->
                prefWidth = value.toDouble()
            }

            //val color = Color.rgb(242, 29, 5, 0.9)
            //background = Background(BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY))

            children.addAll(
                icon,
                title,
                //languageSelectionButton,
                rootPageName.label()
            )

            setOnMouseDragged {
                val stage = scene.window as Stage
                if (stage.isMaximized) {
                    stage.isMaximized = false
                }
                stage.x = it.screenX - offsetX
                stage.y = it.screenY - offsetY
            }

            setOnMouseClicked {
                if ((it.button == MouseButton.PRIMARY) && (it.clickCount == 2)) {
                    val stage = scene.window as Stage
                    val maximized = stage.isMaximized
                    stage.isMaximized = !maximized
                }
            }

            setOnMousePressed {
                offsetX = it.sceneX
                offsetY = it.sceneY
            }
        }

        this.title.apply {
            prefHeight = 60.0
            prefWidth = 120.0
            font = Font.font("Unbounded Medium", FontWeight.BOLD, 18.0)
        }

        this.rootPageName.apply {
            label().prefHeight = 60.0
            header.widthProperty().addListener {_, _, value ->
                label().layoutX = value.toDouble() / 2
            }
        }

        this.icon.apply {
            fitWidth = 100.0
            fitHeight = 40.0
            pickOnBoundsProperty().set(true)
            preserveRatioProperty().set(true)
        }
    }

}