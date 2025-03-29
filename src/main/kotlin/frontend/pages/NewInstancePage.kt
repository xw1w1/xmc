package frontend.pages

import javafx.scene.Parent
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import frontend.element.TranslatableLabel
import frontend.element.TranslatableTooltip

class NewInstancePage : UIPage() {

    override fun createContents(): Parent {
        val box = VBox().apply {
            val instanceName = TranslatableLabel("xmc.launcher.ui-page.new-instance.element.instance-name").label()
            instanceName.apply {
                tooltip = TranslatableTooltip("xmc.launcher.ui-page.new-instance.element.instance-name.tooltip").tooltip()
                font = Font.font("Unbound Medium", FontWeight.BOLD, 20.0)
            }
            children.add(instanceName)
        }
        this.children.add(box)
        return this
    }
}