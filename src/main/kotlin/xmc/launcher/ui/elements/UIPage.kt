package xmc.launcher.ui.elements

import javafx.scene.layout.Pane

abstract class UIPage {
    abstract fun initContentPane()
    abstract fun contentPane(): Pane
}