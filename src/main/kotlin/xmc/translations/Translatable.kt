package xmc.translations

abstract class Translatable(private val key: String) {
    abstract fun translate(): String
    fun translatableKey(): String = key
}