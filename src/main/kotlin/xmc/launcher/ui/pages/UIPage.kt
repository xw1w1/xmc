package xmc.launcher.ui.pages

import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.effect.InnerShadow
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.VBox
import javafx.scene.paint.Color

abstract class UIPage {

    var root = VBox().apply {
        spacing = PAGE_SPACING
        padding = Insets(PAGE_PADDING)
        val fillColorSub = Color.rgb(33, 32, 32, 0.5)
        effect = InnerShadow(PAGE_BG_SHADOW_RADIUS, Color.rgb(33, 33, 33, 0.8))
        background = Background(BackgroundFill(fillColorSub, CornerRadii(15.0, 0.0, 0.0, 0.0, false), Insets.EMPTY))
    }

    abstract fun open()
    abstract fun contentPane(): Parent

    companion object {
        const val PAGE_SPACING = 20.0
        const val PAGE_PADDING = 25.0
        const val PAGE_BG_SHADOW_RADIUS = 10.0
    }
}