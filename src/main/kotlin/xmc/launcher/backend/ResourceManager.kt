package xmc.launcher.backend

import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.File
import java.util.LinkedList

object ResourceManager {

    private val bgCards = LinkedList<ImageView>()
    private val DATA_FOLDER = this.getDataFolder()
    private val CACHES_FOLDER = File(this.getDataFolder(), "caches")

    fun getBackgroundCards(): List<ImageView> {
        var list = LinkedList<ImageView>()
        if (bgCards.isEmpty()) {
            val cards = LinkedList<ImageView>()
            for (i in 1..7) {
                val path = "embedded/card/0$i.png"
                val stream = ResourceManager::class.java.classLoader.getResourceAsStream(path)
                cards.add(ImageView(Image(stream)))
            }
            list = cards
        } else list.addAll(bgCards)
        return list
    }

    fun getConfigurationsFolder(): File {
        return File(this.getDataFolder(), "preferences")
    }

    fun getCachesFolder(): File {
        return this.CACHES_FOLDER
    }

    fun getDataFolder(): File {
        return File(ResourceManager::class.java.protectionDomain.codeSource.location.toURI())
    }
}