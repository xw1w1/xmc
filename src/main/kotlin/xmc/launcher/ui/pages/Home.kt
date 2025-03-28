package xmc.launcher.ui.pages

import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.effect.InnerShadow
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import xmc.XMC
import xmc.launcher.backend.instance.InstanceManager
import xmc.launcher.backend.instance.InstanceObject
import xmc.launcher.ui.elements.TranslatableText

class Home : UIPage() {

    private val tiles = mutableListOf<Region>()
    private val instances = InstanceManager.randomInstancesList(10)

    override fun open() {
        XMC.getInstance().setPage(this.contentPane())
    }

    override fun contentPane(): Parent {
        val recentlyAdded = TranslatableText("xmc.launcher.ui-page.home.title").text().apply {
            font = Font.font("Unbound Medium", FontWeight.BOLD, 20.0)
        }

        val tileContainersHolder = FlowPane().apply {
            hgap = SPACING
            vgap = SPACING
        }

        instances.forEach {
            val card = this.createCard(it)
            tiles.add(card)
            tileContainersHolder.children.add(card)
        }

        root.children.addAll(
            recentlyAdded,
            tileContainersHolder,
        )

        return root
    }

    private fun createCard(obj: InstanceObject): Button {
        return Button().apply {
            val card = obj.card
            val buttonWidth = widthProperty().get()

            graphic = card.apply {
                fitWidth = buttonWidth
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