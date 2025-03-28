package app.element

import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle

/**
 * A modal pop-up window that displays custom content and can be closed by pressing the Escape key.
 * ```
 *  ╭───────────────────────────────────────────────────────────╮
 *  (/////////////////////////////////////////////[ ─  □  ✖ ]//)
 *  │///////////////////////////////////////////////////////////│
 *  │///////////////////////////////////////////////////////////│
 *  │///////////////╭───────────────────────────╮///////////////│
 *  │///////////////│ 1000G - tem pay 4 colleg  │///////////////│
 *  │///////////////│                           │///////////////│
 *  │///////////////│      [Exit] [Buy]         │///////////////│
 *  │///////////////╰───────────────────────────╯///////////////│
 *  │///////////////////////////////////////////////////////////│
 *  │///////////////////////////////////////////////////////////│
 *  │///////////////////////////////////////////////////////////│
 *  ╰───────────────────────────────────────────────────────────╯
 * ```
 * The pop-up dimensions are automatically sized to approximately one-third of the
 * owner window's width and two-thirds of its height.
 *
 * Pop-up have its own semi-transparent overlay that dims the background content.
 */
@Suppress("MemberVisibilityCanBePrivate", "unchecked_cast")
class PopUp(private val contentNode: Node) {

    private lateinit var popUpStage: Stage
    private lateinit var overlay: Pane
    private lateinit var contentWrapper: Pane

    fun open(primaryStage: Stage) {
        this.initOverlay(primaryStage)
        this.prepareContents(primaryStage)
        this.initPopUpStage(primaryStage)
        popUpStage.show()
    }

    fun close() {
        if (::popUpStage.isInitialized) {
            this.popUpStage.close()
        }
    }

    fun <T : Node> content(): T {
        return contentNode as T
    }

    private fun initOverlay(primaryStage: Stage) {
        overlay = Pane().apply {
            style = "-fx-background-color: rgba(0, 0, 0, 0.5);"
            isPickOnBounds = true
            prefWidth = primaryStage.scene.width
            prefHeight = primaryStage.scene.height
        }
    }

    private fun prepareContents(primaryStage: Stage) {
        this.contentWrapper = Pane().apply {
            val width = primaryStage.scene.width / 3
            val height = primaryStage.height * 2 / 3

            prefWidth = width
            prefHeight = height

            contentNode.layoutX = (width - contentNode.layoutBounds.width) / 2
            contentWrapper.layoutY = (height - contentNode.layoutBounds.height) / 2
            children.add(contentNode)
        }
    }

    private fun initPopUpStage(ownerStage: Stage) {
        val root = StackPane().apply {
            children.addAll(overlay, contentWrapper)
        }

        this.popUpStage = Stage(StageStyle.TRANSPARENT).apply {
            initOwner(ownerStage)
            scene = Scene(root).apply {
                fill = Color.TRANSPARENT
                setOnKeyPressed { event ->
                    if (event.code == KeyCode.ESCAPE) close()
                }
            }

            val x = ownerStage.x + (ownerStage.width - contentWrapper.prefWidth) / 2
            val y = ownerStage.y + (ownerStage.height - contentWrapper.prefHeight) / 2

            this.x = x
            this.y = y
        }

        overlay.setOnMouseClicked { close() }
    }
}