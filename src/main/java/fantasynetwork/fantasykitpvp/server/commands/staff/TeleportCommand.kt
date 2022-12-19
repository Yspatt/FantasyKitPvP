package fantasynetwork.fantasykitpvp.server.commands.staff

import fantasynetwork.fantasykitpvp.server.commands.api.FantasyCommand
import fantasynetwork.fantasykitpvp.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TeleportCommand: FantasyCommand("tp") {
    override fun unknownArgument(sender: CommandSender, argument: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun run(p: Player, sender: CommandSender, label: String, args: Array<String>) {
        if (!p.hasPermission("fantasy.commands.tp")){
            p.sendMessage(Utils.permission)
        }else{
            if (args.isEmpty()){
                p.sendMessage("§cUse /tp (player) (player).")
            }else{
                val t =Bukkit.getPlayer(args[0]) ?: p.sendMessage("§cEste jogador não está online.")
                t as Player
                if (args.size == 1){
                    p.teleport(t)
                    p.sendMessage("§aVocê se teleportou até §f${t.name}§a.")
                }else{
                    val t1 =Bukkit.getPlayer(args[1]) ?: p.sendMessage("§cEste jogador não está online.")
                    t1 as Player
                    t.teleport(t1)
                    p.sendMessage("§aVocê teleportou §f${t.name}§a até §f${t1.name}§a.")
                }
            }
        }
    }
}