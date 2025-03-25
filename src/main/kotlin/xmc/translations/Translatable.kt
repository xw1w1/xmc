package xmc.translations

abstract class Translatable(var key: String) {
    abstract fun translate(): String
    fun translatableKey(): String = key
}