package xmc.launcher.backend.instance

import com.google.gson.JsonObject
import java.io.File
import java.nio.file.Path
import java.util.LinkedList

object InstanceManager {

    private val instances: List<Instance> = LinkedList()

    fun importInstanceData(file: File): JsonObject { return JsonObject()
    }
    fun exportInstanceData(instance: Instance, path: Path): JsonObject { return JsonObject() }
}