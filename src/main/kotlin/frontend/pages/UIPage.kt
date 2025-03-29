package frontend.pages

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.effect.InnerShadow
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import XMC

abstract class UIPage : VBox() {

    init {
        spacing = PAGE_SPACING
        padding = Insets(PAGE_PADDING)
        effect = InnerShadow(PAGE_BG_SHADOW_RADIUS, SHADOW_COLOR)
        background = Background(BackgroundFill(FILL_COLOR, CornerRadii(15.0, 0.0, 0.0, 0.0, false), Insets.EMPTY))
        alignment = Pos.BASELINE_LEFT
    }

    fun open() { XMC.getInstance().openPage(this) }

    abstract fun createContents(): Parent

    companion object {
        const val PAGE_SPACING = 20.0
        const val PAGE_PADDING = 25.0
        const val PAGE_BG_SHADOW_RADIUS = 10.0

        val FILL_COLOR = Color.rgb(33, 32, 32, 0.5)
        val SHADOW_COLOR = Color.rgb(33, 33, 33, 0.8)
    }
}