package fantasynetwork.fantasykitpvp.warps

import org.bukkit.Location
import org.bukkit.inventory.ItemStack


abstract class Warp{

    abstract fun getName():String
    abstract fun getLocation():Location
    abstract fun getIcon():ItemStack
}
