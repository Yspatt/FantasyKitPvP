package fantasynetwork.fantasykitpvp.utils.chat

import fantasynetwork.fantasykitpvp.FantasyKitPvP
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import com.avaje.ebeaninternal.server.cluster.socket.SocketClusterMessage.packet
import java.awt.SystemColor.text
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer
import net.minecraft.server.v1_8_R3.PacketPlayOutChat
import com.avaje.ebeaninternal.server.cluster.socket.SocketClusterMessage.packet
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer





fun Player.getPrefix(): String {
    return ChatColor.translateAlternateColorCodes('&', FantasyKitPvP.chat.getPlayerPrefix(this))
}
fun Player.getSuffix():String{
    return ChatColor.translateAlternateColorCodes('&', FantasyKitPvP.chat.getPlayerSuffix(this))
}
fun Player.sendActionBar(s:String){
    val packet = PacketPlayOutChat(ChatSerializer.a("{\"text\":\"$text\"}"), 2.toByte())
    (this as CraftPlayer).handle.playerConnection.sendPacket(packet)

}
class Tags{
    companion object {
        val seta = "§e§l➜ "
        val clan = "§b§lCLAN $seta"
    }
}