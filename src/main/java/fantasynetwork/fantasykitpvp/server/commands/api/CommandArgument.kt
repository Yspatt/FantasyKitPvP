package fantasynetwork.fantasykitpvp.server.commands.api

import org.bukkit.command.CommandSender

public abstract class CommandArgument {

    private var argument: String = ""
    private var permission: String = ""
    fun getArgument(): String {
        return argument
    }

    abstract fun run(p: CommandSender, argument: String,args:Array<String>)

    fun getPermission(): String {
        return permission
    }

    constructor(argument: String, permission: String) {
        this.argument = argument
        this.permission = permission
    }
}
