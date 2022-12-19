package fantasynetwork.fantasykitpvp.server.commands.api

import fantasynetwork.fantasykitpvp.FantasyKitPvP
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.craftbukkit.v1_8_R3.CraftServer
import org.bukkit.entity.Player

abstract class FantasyCommand : Command {

    private var argumentList: List<CommandArgument> = listOf()

    abstract fun unknownArgument(sender: CommandSender, argument: String)
    abstract fun run(p: Player, sender: CommandSender, label: String, args: Array<String>)

    constructor(command: String, arguments: List<CommandArgument>) : super(command) {
        argumentList = arguments
    }

    constructor(command: String) : super(command)

    override fun execute(commandSender: CommandSender, s: String, args: Array<String>): Boolean {
        if (commandSender is Player) {
            // Se o cara digitou alguma coisa & a lista e argumentos não está vazia (o comando não é simples)
            if (args.isNotEmpty() && argumentList.isNotEmpty()){
                for (a in argumentList){
                    if (a.getArgument().equals(args[0],true)){
                        a.run(commandSender,args[0],args)
                    }else{
                        unknownArgument(commandSender,args[0])
                    }
                }
                //O cara digitou argumentos mas não existe nenhum argumento na lista de argumentos (o comando é simples)
            }else if(args.isNotEmpty() && argumentList.isEmpty()){
                run(commandSender,commandSender,s,args)
            }

        }
        return false
    }

    fun register() {
        (FantasyKitPvP.instance.server as CraftServer).commandMap.register("", this)
    }

}