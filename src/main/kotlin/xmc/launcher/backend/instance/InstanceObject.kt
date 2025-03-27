package xmc.launcher.backend.instance

import xmc.labrinth.SoftProjectReference
import java.util.LinkedList

class InstanceObject(val name: String) {
    val mods: List<SoftProjectReference> = LinkedList<SoftProjectReference>()
}