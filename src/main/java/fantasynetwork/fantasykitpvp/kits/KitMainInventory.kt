package fantasynetwork.fantasykitpvp.kits

import fantasynetwork.fantasykitpvp.FantasyKitPvP
import fantasynetwork.fantasykitpvp.utils.player.hasKit
import fantasynetwork.fantasykitpvp.utils.player.setKit
import fr.minuskube.inv.ClickableItem
import fr.minuskube.inv.content.InventoryProvider
import fr.minuskube.inv.SmartInventory
import fr.minuskube.inv.content.InventoryContents
import org.bukkit.entity.Player


class KitMainInventory: InventoryProvider {
    companion object {
        val INVENTORY = SmartInventory.builder()
                .id("mainKitInventory")
                .provider(KitMainInventory())
                .size(6, 9)
                .title("§5§lKITS ➜ §aSeletor de kits.")
                .build()
    }

    override fun update(player: Player?, contents: InventoryContents?) {
    }

    override fun init(player: Player, contents: InventoryContents) {
        for (kit in FantasyKitPvP.kit.kits){
            contents.add(ClickableItem.of(kit.getIcon()){
                if (!player.hasKit()){
                    player.setKit(kit)
                    player.closeInventory()
                }
            })
        }

    }
}