package fantasynetwork.fantasykitpvp.utils

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

data class Armor(var helmet:ItemStack,var chestplate:ItemStack,var leggings:ItemStack,var boots:ItemStack) {
    constructor(armor: ArmorType):this(
            ItemStack(Material.getMaterial(armor.name + "_HELMET")),
            ItemStack(Material.getMaterial(armor.name + "_CHESTPLATE")),
            ItemStack(Material.getMaterial(armor.name + "_LEGGINGS")),
            ItemStack(Material.getMaterial(armor.name + "_BOOTS"))
    )
}
enum class ArmorType{
    LEATHER,
    CHAINMAIL,
    GOLD,
    IRON,
    DIAMOND
}