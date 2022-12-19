package fantasynetwork.fantasykitpvp.server.listeners

import fantasynetwork.fantasykitpvp.utils.chat.getPrefix
import fantasynetwork.fantasykitpvp.utils.chat.getSuffix
import fantasynetwork.fantasykitpvp.utils.Cooldown
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class PlayerChatEvent : Listener {

    @EventHandler
    fun onChat(e:AsyncPlayerChatEvent){
        val p = e.player
            e.isCancelled = true
            Bukkit.broadcastMessage("${p.getPrefix()}${p.getSuffix()} ${p.name} §7: §f" + e.message)
    }

}
