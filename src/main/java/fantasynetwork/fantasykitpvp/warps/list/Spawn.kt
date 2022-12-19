package fantasynetwork.fantasykitpvp.warps.list

import fantasynetwork.fantasykitpvp.utils.item.ItemStackBuilder
import fantasynetwork.fantasykitpvp.warps.Warp
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class Spawn: Warp() {
    override fun getName(): String = "Spawn"

    override fun getLocation(): Location = Location(Bukkit.getWorld("world"),0.0,20.0,0.0)

    override fun getIcon(): ItemStack = ItemStackBuilder(Material.EMERALD).setName("§aSpawn").build()
}