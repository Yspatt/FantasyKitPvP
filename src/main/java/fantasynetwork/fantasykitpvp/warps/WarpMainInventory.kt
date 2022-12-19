package fantasynetwork.fantasykitpvp.warps

import fantasynetwork.fantasykitpvp.FantasyKitPvP
import fr.minuskube.inv.ClickableItem
import fr.minuskube.inv.SmartInventory
import fr.minuskube.inv.content.InventoryContents
import fr.minuskube.inv.content.InventoryProvider
import org.bukkit.entity.Player
import fr.minuskube.inv.content.SlotPos
import fr.minuskube.inv.content.SlotIterator



class WarpMainInventory: InventoryProvider {
    companion object {
        val INVENTORY = SmartInventory.builder()
                .id("mainWarpInventory")
                .provider(WarpMainInventory())
                .size(3, 9)
                .title("§5§lWARPS ➜ §aSeletor de warps.")
                .build()
    }

    override fun update(player: Player?, contents: InventoryContents?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun init(player: Player, contents: InventoryContents) {
        val it = contents.newIterator(SlotIterator.Type.HORIZONTAL, SlotPos(1, 1))
        val pagination = contents.pagination()
        val items = arrayOfNulls<ClickableItem>(28)
        it.blacklist(1, 8).blacklist(2, 0).blacklist(2, 8).blacklist(3, 0).blacklist(3, 8).blacklist(4, 0).blacklist(4, 8)
        val warps = FantasyKitPvP.warp.warps
        for (w in 0..warps.size) {
            val warp = warps[w]
            items[w] = ClickableItem.of(warp.getIcon()) {
                    player.teleport(warp.getLocation())
                    player.closeInventory()

            }
            pagination.pageItems[w] = items[w]

        }

        pagination.addToIterator(it)

    }
}