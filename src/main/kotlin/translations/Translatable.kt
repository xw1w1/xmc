package translations

abstract class Translatable(var key: String) {
    abstract fun translate(): String
    fun translatableKey(): String = key
    fun translatableKey(key: String) { this.key = key }
}