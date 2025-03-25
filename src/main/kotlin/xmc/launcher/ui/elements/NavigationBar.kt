package xmc.launcher.ui.elements

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.stage.Stage

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
            font = Font.font("Segoe UI Semibold", FontWeight.EXTRA_BOLD, 16.0)
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

    private var offsetX: Double = 0.0
    private var offsetY: Double = 0.0

    init {
        this.header.apply {
            alignment = Pos.TOP_LEFT
            padding = Insets(10.0)
            spacing = 8.0

            prefHeight = 60.0
            parent.widthProperty().addListener {_, _, value ->
                prefWidth = value.toDouble()
            }

            background = Background(BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))

            children.addAll(
                icon,
                title,
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
        }

        this.icon.apply {
            fitWidth = 100.0
            fitHeight = 40.0
            pickOnBoundsProperty().set(true)
            preserveRatioProperty().set(true)
        }
    }

}