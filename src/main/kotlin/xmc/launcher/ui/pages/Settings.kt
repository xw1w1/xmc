package xmc.launcher.ui.pages

import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import xmc.XMC

class Settings : UIPage() {

    val page: HBox = HBox()

    override fun open() {
        XMC.getInstance().setPage(this.contentPane())
    }

    override fun contentPane(): Parent {
        page.apply {
            children.addAll(Button("test"), Label("test"))
        }
        return page
    }
}