package frontend.element

import javafx.scene.control.Label
import javafx.scene.control.Tooltip
import javafx.scene.text.Text
import backend.Preferences
import translations.I18n
import translations.Translatable

class TranslatableText(key: String) : Translatable(key) {

    fun text() = text

    private val text = Text().apply {
        this.text = I18n.translate(key, Preferences.getCurrentLanguage())
    }

    override fun translate(): String {
        val translated = I18n.translate(this.translatableKey(), Preferences.getCurrentLanguage())
        this.text.textProperty().value = translated
        return translated
    }
}

class TranslatableLabel(key: String) : Translatable(key) {

    fun label() = label

    private val label = Label().apply {
        this.text = I18n.translate(key, Preferences.getCurrentLanguage())
    }

    override fun translate(): String {
        val translated = I18n.translate(this.translatableKey(), Preferences.getCurrentLanguage())
        this.label.textProperty().value = translated
        return translated
    }
}

class TranslatableTooltip(key: String) : Translatable(key) {

    fun tooltip() = tooltip

    private val tooltip = Tooltip().apply {
        this.text = I18n.translate(key, Preferences.getCurrentLanguage())
    }

    override fun translate(): String {
        val translated = I18n.translate(this.translatableKey(), Preferences.getCurrentLanguage())
        this.tooltip.textProperty().value = translated
        return translated
    }
}
