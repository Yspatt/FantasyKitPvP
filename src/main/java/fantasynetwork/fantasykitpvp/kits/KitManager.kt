package fantasynetwork.fantasykitpvp.kits

import fantasynetwork.fantasykitpvp.FantasyKitPvP
import fantasynetwork.fantasykitpvp.kits.list.*
import fantasynetwork.fantasykitpvp.utils.player.clear
import fantasynetwork.fantasykitpvp.utils.player.setArmor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

/* Deve conter:
     -> Lista de usuarios com kits <- FEITO
     -> Kit Selector <-
     -> Kits desabilitados/habilitados <-
     -> Métodos de adicionar e remover kit <- FEITO
 */
class KitManager {
    val kits: List<Kit> = listOf(Guerreiro())
    val kitUsers: MutableMap<Player, Kit> = mutableMapOf()

    fun register(){
        for (k in kits){
            if (k is Listener){
                FantasyKitPvP.instance.server.pluginManager.registerEvents(k,FantasyKitPvP.instance)
            }
        }
    }
    fun hasKit(p:Player):Boolean = kitUsers.containsKey(p)

    fun hasKit(p:Player, kit:Kit) = getKit(p) == kit

    fun getKit(p:Player) = kitUsers[p]

    fun setKit(p:Player,kit:Kit){
        if (!hasKit(p)){
            p.clear()
           // if(kit.getCooldown() != null) kit.getCooldown()!!.setTarget(p)

            p.setArmor(kit.getArmor()!!)
            kit.getItens().forEach{ p.inventory.setItem(it.location,it.item) }
            for (i in 0..p.inventory.size){ p.inventory.addItem(ItemStack(Material.MUSHROOM_SOUP)) }
            kitUsers[p]= kit
        }
    }

    fun removeKit(p:Player) {
        if (hasKit(p)) {
            kitUsers.remove(p)
            p.clear()
        }
    }

}