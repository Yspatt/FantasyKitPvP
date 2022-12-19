package fantasynetwork.fantasykitpvp.server.commands.staff

import fantasynetwork.fantasykitpvp.server.commands.api.FantasyCommand
import fantasynetwork.fantasykitpvp.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GamemodeCommand:FantasyCommand("gamemode") {

    override fun getAliases(): MutableList<String> {
        return mutableListOf("gm")
    }

    override fun unknownArgument(sender: CommandSender, argument: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun run(p: Player, sender: CommandSender, label: String, args: Array<String>) {
        if (!p.hasPermission("fantasy.commands.gm")){
            p.sendMessage(Utils.permission)
        }else{
            if (args.isEmpty() || args[0].toIntOrNull() == null){
                p.sendMessage("§cUse /$label (0,1,2,3) (player).")
            }else{
                if (args.size == 1) {
                    p.gameMode = GameMode.getByValue(args[0].toInt())
                    p.sendMessage("§aVocê alterou seu modo de jogo para §f${p.gameMode}§a.")
                }else{
                    val t = Bukkit.getPlayer(args[1]) ?: p.sendMessage("§cEste jogador não está online.")
                    t as Player
                    t.gameMode = GameMode.getByValue(args[0].toInt())
                    p.sendMessage("§aVocê alterou o modo de jogo de §f${t.name} §apara §f${t.gameMode}§a.")
                }
            }
        }
    }
}