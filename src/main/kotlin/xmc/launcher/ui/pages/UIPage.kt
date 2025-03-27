package xmc.launcher.ui.pages

import javafx.scene.Parent

abstract class UIPage {
    abstract fun open()
    abstract fun contentPane(): Parent
}