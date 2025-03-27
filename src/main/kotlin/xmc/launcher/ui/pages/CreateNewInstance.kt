package xmc.launcher.ui.pages

import javafx.scene.Parent
import javafx.scene.layout.Pane
import xmc.XMC

class CreateNewInstance : UIPage() {
    override fun open() {
        XMC.getInstance().setPage(this.contentPane())
    }

    override fun contentPane(): Parent {
        return Pane()
    }
}