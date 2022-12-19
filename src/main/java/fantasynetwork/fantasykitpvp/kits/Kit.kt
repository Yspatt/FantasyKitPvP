package fantasynetwork.fantasykitpvp.kits

import fantasynetwork.fantasykitpvp.managers.kit.kitutils.Weapon
import fantasynetwork.fantasykitpvp.utils.Armor
import fantasynetwork.fantasykitpvp.utils.Cooldown
import fantasynetwork.fantasykitpvp.utils.kit.KitCooldown
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect

interface Kit {
    fun getName():String
    fun getIcon():ItemStack
    fun getCooldown(): Long?
    fun getEffects():List<PotionEffect>?
    fun getItens():List<Weapon>
    fun getArmor(): Armor?
    fun getPrice(): Int
}