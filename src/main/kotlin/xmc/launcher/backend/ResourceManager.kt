package xmc.launcher.backend

import java.io.File

object ResourceManager {

    private val DATA_FOLDER = this.getDataFolder()
    private val CACHES_FOLDER = File(this.getDataFolder(), "caches")

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