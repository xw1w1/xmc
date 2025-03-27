package xmc.launcher.ui.pages

import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.effect.InnerShadow
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import org.w3c.dom.css.Rect
import xmc.XMC
import xmc.launcher.backend.ResourceManager
import xmc.launcher.backend.instance.InstanceManager
import xmc.launcher.backend.instance.InstanceObject
import xmc.launcher.ui.elements.TranslatableText
import java.awt.GraphicsDevice
import java.awt.Toolkit
import java.util.LinkedList
import java.util.Objects
import kotlin.random.Random

class Home : UIPage() {

    private val tiles = mutableListOf<Region>()
    private val instances = InstanceManager.randomInstancesList(10)

    private val root = VBox().apply {
        spacing = 20.0
        padding = Insets(PADDING)
        val fillColorSub = Color.rgb(33, 32, 32, 0.5)
        effect = InnerShadow(10.0, Color.rgb(33, 33, 33, 0.8))
        background = Background(BackgroundFill(fillColorSub, CornerRadii(15.0, 0.0, 0.0, 0.0, false), Insets.EMPTY))
        widthProperty().addListener {_, _, _ ->
            this.children.clear()
            contentPane()
        }
    }

    override fun open() {
        XMC.getInstance().setPage(this.contentPane())
    }

    override fun contentPane(): Parent {
        val recentlyAdded = TranslatableText("xmc.launcher.ui-page.home.title").text().apply {
            font = Font.font("Unbound Medium", FontWeight.BOLD, 18.0)
        }

        val tileContainersHolder = VBox().apply {
            val rows = breakInstancesToRows(instances)
            children.addAll(rows)
            tiles.addAll(rows)
        }

        root.children.addAll(
            recentlyAdded,
            tileContainersHolder,
        )

        return root
    }

    private fun breakInstancesToRows(instances: List<InstanceObject>): List<HBox> {
        val rows = LinkedList<HBox>()
        var elementsPerRow = 3
        val screenWidth = Toolkit.getDefaultToolkit().screenSize.width
        val paneWidth = root.widthProperty().get()
        if (paneWidth > (screenWidth * 0.9)) {
            elementsPerRow = 5
        } else if (paneWidth > (screenWidth * 0.8)) {
            elementsPerRow = 4
        }

        instances.forEach { instance ->
            val row = rows.firstOrNull { it.children.size < elementsPerRow } ?: HBox().also { rows.add(it) }
            row.children.add(this.createCard(instance))
        }
        return rows
    }

    private fun createCard(obj: InstanceObject): Button {
        return Button().apply {
            val card = obj.card
            var buttonWidth = 0.0
            widthProperty().addListener { _, _, value ->
                buttonWidth = value.toDouble()
            }
            graphic = card.apply {
                fitWidth = PLACEHOLDER_MAX_WIDTH
                fitHeight = PLACEHOLDER_HEIGHT
                clip = Rectangle(buttonWidth, PLACEHOLDER_HEIGHT).apply {
                    arcWidth = 25.0
                    arcHeight = 25.0
                }
            }

            maxWidth = PLACEHOLDER_MIN_WIDTH
            minWidth = PLACEHOLDER_MIN_WIDTH
            maxHeight = PLACEHOLDER_HEIGHT

            prefWidth = PLACEHOLDER_MAX_WIDTH
        }
    }

    private companion object {
        const val PLACEHOLDER_HEIGHT = 100.0
        const val PLACEHOLDER_MIN_WIDTH = 250.0
        const val PLACEHOLDER_MAX_WIDTH = 350.0
        const val SPACING = 15.0
        const val PADDING = 20.0
    }
}