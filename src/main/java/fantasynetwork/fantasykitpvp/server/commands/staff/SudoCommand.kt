package fantasynetwork.fantasykitpvp.server.commands.staff

import fantasynetwork.fantasykitpvp.server.commands.api.FantasyCommand
import fantasynetwork.fantasykitpvp.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SudoCommand: FantasyCommand("sudo") {

    override fun unknownArgument(sender: CommandSender, argument: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun run(p: Player, sender: CommandSender, label: String, args: Array<String>) {
        if (!p.hasPermission("fantasy.commands.tp")){
            p.sendMessage(Utils.permission)
        }else{
            if (args.size <2){
                p.sendMessage("§cUse /sudo (player) (sudo).")
            }else{
                val t = Bukkit.getPlayer(args[0]) ?: p.sendMessage("§cEste jogador não está online.")
                t as Player
                val message = args.drop(1).joinToString(" ")
                t.chat(message)
                p.sendMessage("§aVocê forçou §f${t.name}§a a executar §f'${message}'§a.")
            }
        }
    }
}