package xmc.launcher.ui.pages

import javafx.scene.Parent
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import xmc.XMC
import xmc.launcher.ui.elements.TranslatableLabel
import xmc.launcher.ui.elements.TranslatableTooltip

class CreateNewInstance : UIPage() {

    override fun open() {
        XMC.getInstance().setPage(this.contentPane())
    }

    override fun contentPane(): Parent {
        val box = VBox().apply {
            val instanceName = TranslatableLabel("xmc.launcher.ui-page.new-instance.element.instance-name").label()
            instanceName.apply {
                tooltip = TranslatableTooltip("xmc.launcher.ui-page.new-instance.element.instance-name.tooltip").tooltip()
                font = Font.font("Unbound Medium", FontWeight.BOLD, 20.0)
            }
            children.add(instanceName)
        }
        this.root.children.add(box)
        return this.root
    }
}