package fantasynetwork.fantasykitpvp.utils.player

import fantasynetwork.fantasykitpvp.FantasyKitPvP
import fantasynetwork.fantasykitpvp.kits.Kit
import fantasynetwork.fantasykitpvp.utils.Armor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

    fun Player.setArmor(helmet: ItemStack, chestplate: ItemStack, leggings: ItemStack, boots: ItemStack) = with(inventory){
        this.armorContents = arrayOf(boots, leggings, chestplate, helmet)
    }
    fun Player.setArmor(armor: Armor) = with(inventory){
        this.helmet = armor.helmet
        this.chestplate = armor.chestplate
        this.leggings = armor.leggings
        this.boots = armor.boots
    }

    fun Player.clear() {
        inventory.clear()
        inventory.armorContents.forEach { it.type = Material.AIR }
        activePotionEffects.forEach { removePotionEffect(it.type) }
        health = 20.0
        fireTicks = 0
    }

    // -> Kit <- //
    fun Player.getKit(): Kit? =FantasyKitPvP.kit.getKit(this)
    fun Player.hasKit():Boolean =FantasyKitPvP.kit.hasKit(this)
    fun Player.hasKit(kit: Kit):Boolean =FantasyKitPvP.kit.hasKit(player)
    fun Player.setKit(kit: Kit) =FantasyKitPvP.kit.setKit(this,kit)
    fun Player.removeKit() =FantasyKitPvP.kit.removeKit(this)
    fun Player.getAccount() = FantasyKitPvP.user.getUser(this.uniqueId)