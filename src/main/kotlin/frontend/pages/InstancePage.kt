package frontend.pages

import javafx.scene.Parent
import javafx.scene.layout.Pane
import backend.instance.InstanceObject

class InstancePage(instanceObj: InstanceObject) : UIPage() {

    override fun createContents(): Parent {
        return Pane()
    }
}