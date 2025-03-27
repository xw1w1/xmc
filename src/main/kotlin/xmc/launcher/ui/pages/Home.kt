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
import javafx.stage.Stage
import xmc.XMC
import xmc.launcher.backend.ResourceManager
import xmc.launcher.ui.elements.TranslatableText
import kotlin.random.Random

class Home : UIPage() {

    private val tiles = mutableListOf<Pane>()
    private val wideTiles = mutableListOf<Region>()

    val root = VBox().apply {
        spacing = 20.0
        padding = Insets(PADDING)
        val fillColorSub = Color.rgb(33, 32, 32, 0.5)
        effect = InnerShadow(10.0, Color.rgb(33, 33, 33, 0.8))
        background = Background(BackgroundFill(fillColorSub, CornerRadii(15.0, 0.0, 0.0, 0.0, false), Insets.EMPTY))
        widthProperty().addListener { _, _, value ->
            val buttonAdditional: Double
            if (value.toDouble() <= minWidth) {
                buttonAdditional = PLACEHOLDER_MIN_WIDTH
            } else if (value.toDouble() >= maxWidth) {
                buttonAdditional = PLACEHOLDER_MAX_WIDTH
            } else {
                val ratio = (value.toDouble() - minWidth) / (maxWidth - minWidth)
                buttonAdditional = width + ratio * (maxWidth - minWidth)
            }

            tiles.forEach {
                //(it.graphic as ImageView).prefWidth(buttonAdditional)
                it.prefWidth = buttonAdditional
            }
        }
    }

    override fun open() {
        XMC.getInstance().setPage(this.contentPane())
    }

    override fun contentPane(): Parent {
        val recentlyAdded = TranslatableText("xmc.launcher.ui-page.home.title").text().apply {
            font = Font.font("Unbound Medium", FontWeight.BOLD, 18.0)
        }

        val tileContainer = FlowPane().apply {
            hgap = SPACING
            vgap = SPACING
        }

        repeat(6) {
            val tile = createCard()
            tileContainer.children.add(tile)
            this.tiles.add(tile)
        }

        root.children.addAll(
            recentlyAdded,
            tileContainer,
        )

        return root
    }

    private fun createCard(): Pane {
        return Pane().also { button ->
            val cards = ResourceManager.getBackgroundCards()
            button.background = Background(
                BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)
            )
            val image = cards[Random.nextInt(cards.size)].apply {
                clip = Rectangle(PLACEHOLDER_MAX_WIDTH - 10, PLACEHOLDER_HEIGHT).apply {
                    layoutX += 5
                    arcWidth = 15.0
                    arcHeight = 15.0
                    fitHeight = PLACEHOLDER_HEIGHT
                    fitWidth = PLACEHOLDER_MAX_WIDTH
                }
            }
            button.maxHeight = PLACEHOLDER_HEIGHT
            button.minWidth = PLACEHOLDER_MIN_WIDTH
            button.maxWidth = PLACEHOLDER_MAX_WIDTH
        }
    }

    private companion object {
        const val PLACEHOLDER_HEIGHT = 100.0
        const val PLACEHOLDER_MIN_WIDTH = 250.0
        const val PLACEHOLDER_MAX_WIDTH = 450.0
        const val SPACING = 15.0
        const val PADDING = 20.0
    }
}