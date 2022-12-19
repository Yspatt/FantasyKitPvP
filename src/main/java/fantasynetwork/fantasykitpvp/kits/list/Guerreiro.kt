package fantasynetwork.fantasykitpvp.kits.list

import fantasynetwork.fantasykitpvp.kits.Kit
import fantasynetwork.fantasykitpvp.managers.kit.kitutils.Weapon
import fantasynetwork.fantasykitpvp.utils.Armor
import fantasynetwork.fantasykitpvp.utils.ArmorType
import fantasynetwork.fantasykitpvp.utils.item.ItemStackBuilder
import fantasynetwork.fantasykitpvp.utils.kit.KitCooldown
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect

class Guerreiro : Kit {

    override fun getName(): String = "Guerreiro"

    override fun getIcon(): ItemStack = ItemStackBuilder(Material.DIAMOND_SWORD).setName("§aGuerreiro").addLore("lore").build();

    override fun getCooldown()= null

    override fun getEffects(): List<PotionEffect>? = null

    override fun getItens(): List<Weapon> = listOf(
            Weapon(ItemStackBuilder(Material.DIAMOND_SWORD).build(), 0))

    override fun getArmor(): Armor? = Armor(ArmorType.IRON)

    override fun getPrice(): Int = 0

}