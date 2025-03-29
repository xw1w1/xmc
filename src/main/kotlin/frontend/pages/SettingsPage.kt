package frontend.pages

import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox

class SettingsPage : UIPage() {

    val page: HBox = HBox()

    override fun createContents(): Parent {
        page.apply {
            children.addAll(Button("test"), Label("test"))
        }
        return page
    }
}