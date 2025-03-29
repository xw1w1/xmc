package backend.instance

import api.labrinth.SoftProjectReference
import backend.ResourceManager
import java.util.LinkedList

class InstanceObject(val name: String) {
    val mods: List<SoftProjectReference> = LinkedList<SoftProjectReference>()
    val resourcepacks: List<SoftProjectReference> = LinkedList<SoftProjectReference>()
    val shaders: List<SoftProjectReference> = LinkedList<SoftProjectReference>()

    val card = ResourceManager.getBackgroundCards().random()
}