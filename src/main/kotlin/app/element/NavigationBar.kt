package app.element

import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.stage.Stage
import xmc.launcher.ui.elements.TranslatableLabel
import java.awt.Toolkit

/**
 * A custom top navigation bar implementation that extends the standard Windows window header.
 *
 * This [HBox]-based control combines native window controls with custom application-specific
 * elements in a horizontally expandable container.
 *
 * The bar dynamically adjusts its content and width to accommodate child components while
 * maintaining window decoration functionality.
 * ```
 * ╭───────────────────────────────────────────────────────────╮
 * (  xmc                   Home   ( o fabric 1.21 )  ─  □  ✖  )
 * │                                                           │
 * ```
 * The implementation handles proper spacing and alignment between system controls and
 * custom elements while preserving window manager functionality.
 */
@Suppress("MemberVisibilityCanBePrivate")
class NavigationBar(scene: Scene) : HBox() {

    var draggable: Boolean = true
    val appIcon: ImageView = ImageView("favicon.png")
    val appTitle: Label = TranslatableLabel("").label()

    private var offsetX = 0.0
    private var offsetY = 0.0

    private var preferredHeight: Double = 65.0

    constructor(scene: Scene, icon: ImageView) : this(scene) {
        this.appIcon.image = icon.image
    }

    constructor(scene: Scene, prefHeight: Double) : this(scene) {
        this.preferredHeight = prefHeight
    }

    constructor(scene: Scene, prefHeight: Double, icon: ImageView) : this(scene) {
        this.appIcon.image = icon.image
        this.preferredHeight = prefHeight
    }

    init {
        this.padding = Insets(10.0)
        this.spacing = 5.0

        this.prefHeight = this.preferredHeight

        scene.widthProperty().addListener { _, _, value ->
            this.prefWidth = value.toDouble() - this.computeWindowButtonsSize()
        }

        this.initTitle(); this.initIcon()
        this.children.addAll(appIcon, appTitle)

        this.setOnMousePressed {
            this.offsetX = it.sceneX
            this.offsetY = it.sceneY
        }

        this.setOnMouseClicked { this.onMouseClicked(it) }
        this.setOnMouseDragged { this.onMouseDragged(it) }
    }

    private fun initTitle() {
        this.appTitle.apply {
            prefHeight = 60.0
            prefWidth = 120.0
            font = Font.font("Unbounded Medium", FontWeight.BOLD, 18.0)
        }
    }

    private fun initIcon() {
        this.appIcon.apply {
            fitWidth = 100.0
            fitHeight = 40.0
            pickOnBoundsProperty().set(true)
            preserveRatioProperty().set(true)
        }
    }

    private fun onMouseClicked(event: MouseEvent) {
        if ((event.button == MouseButton.PRIMARY) && (event.clickCount == 2)) {
            val stage = scene.window as Stage
            val maximized = stage.isMaximized
            stage.isMaximized = !maximized
        }
    }

    private fun onMouseDragged(event: MouseEvent) {
        if (this.draggable) {
            val stage = scene.window as Stage
            if (stage.isMaximized) stage.isMaximized = false
            stage.x = event.screenX - offsetX
            stage.y = event.screenY - offsetY
        }
    }

    private fun computeWindowButtonsSize(): Double {
        val dpiScaling = Toolkit.getDefaultToolkit().screenResolution / 96.0
        val scaledButtonWidth = 45 * dpiScaling
        return (scaledButtonWidth * 3)
    }

}