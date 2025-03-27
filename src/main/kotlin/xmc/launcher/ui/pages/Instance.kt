package xmc.launcher.ui.pages

import javafx.scene.Parent
import javafx.scene.layout.Pane
import xmc.XMC
import xmc.launcher.backend.instance.InstanceObject

class Instance(instanceObj: InstanceObject) : UIPage() {
    override fun open() {
        XMC.getInstance().setPage(this.contentPane())
    }

    override fun contentPane(): Parent {
        return Pane()
    }
}