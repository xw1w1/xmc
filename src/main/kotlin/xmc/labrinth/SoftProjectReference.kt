package xmc.labrinth

import xmc.labrinth.v2.LabrinthAPI
import xmc.labrinth.wrappers.Project
import xmc.labrinth.wrappers.Version
import java.io.File

/**
 * Used to "soft-wrap" any file in instance folder, like resourcepack, mod or
 */
data class SoftProjectReference(
    val file: File,
    val projectType: Project.Type
) {

    fun hash(): String {
        return Utils.sha512(this.file)
    }

    fun fetchProjectCurrentVersion(): Version {
        return LabrinthAPI.getVersionFromHash(hash())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SoftProjectReference

        if (file != other.file) return false
        if (projectType != other.projectType) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}