package xmc.launcher.backend.instance

import xmc.labrinth.SoftProjectReference
import xmc.launcher.backend.ResourceManager
import java.util.LinkedList

class InstanceObject(val name: String) {
    val mods: List<SoftProjectReference> = LinkedList<SoftProjectReference>()
    val resourcepacks: List<SoftProjectReference> = LinkedList<SoftProjectReference>()
    val shaders: List<SoftProjectReference> = LinkedList<SoftProjectReference>()

    val card = ResourceManager.getBackgroundCards().random()
}