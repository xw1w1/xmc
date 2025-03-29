package api.labrinth.wrappers

/**
 * All project categories available on [https://modrinth.com/](https://modrinth.com/)
 */
enum class Category(val key: String) {
    ADVENTURE("adventure"),
    CURSED("cursed"),
    DECORATION("decoration"),
    ECONOMY("economy"),
    EQUIPMENT("equipment"),
    FOOD("food"),
    GAME_MECHANICS("game_mechanics"),
    LIBRARY("library"),
    MAGIC("magic"),
    MANAGEMENT("management"),
    MINIGAME("minigame"),
    MOBS("mobs"),
    OPTIMIZATION("optimization"),
    SOCIAL("social"),
    STORAGE("storage"),
    TECHNOLOGY("technology"),
    TRANSPORTATION("transportation"),
    UTILITY("utility"),
    WORLD_GENERATION("world_generation")
    ;

    fun translationKey() = "xmc.labrinth.category.$key"
}