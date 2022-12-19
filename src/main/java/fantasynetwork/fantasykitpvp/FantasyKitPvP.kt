package fantasynetwork.fantasykitpvp

import com.google.common.collect.Lists
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fantasynetwork.fantasykitpvp.kits.KitManager
import fantasynetwork.fantasykitpvp.mysql.connections.UserConnection
import fantasynetwork.fantasykitpvp.warps.WarpManager
import fantasynetwork.fantasykitpvp.server.listeners.PlayerChatEvent
import fantasynetwork.fantasykitpvp.server.commands.staff.GamemodeCommand
import fantasynetwork.fantasykitpvp.server.commands.staff.SudoCommand
import fantasynetwork.fantasykitpvp.server.commands.staff.TeleportCommand
import fantasynetwork.fantasykitpvp.server.listeners.PlayerEvents
import fantasynetwork.fantasykitpvp.server.listeners.totens.TotemSignEvent
import fantasynetwork.fantasykitpvp.server.listeners.user.UserEvents
import fantasynetwork.fantasykitpvp.server.scoreboard.ServerScoreboard
import fantasynetwork.fantasykitpvp.service.Service
import fantasynetwork.fantasykitpvp.spawnprotection.RegionManager
import fantasynetwork.fantasykitpvp.users.UserManager
import fr.minuskube.inv.InventoryManager
import net.milkbowl.vault.chat.Chat
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.Database

class FantasyKitPvP : JavaPlugin(){

    var prefix : String = "§c§lFantasy§7§lKitPvP" // << Precisa da prefix
    private val services = Lists.newArrayList<Service<*, *>>()

    override fun onEnable() {
        UserConnection.getInstance().checkDatabase()
        setupVar()
     //   setupChat()
        register()
        ServerScoreboard.startTask()

        services.forEach(Service<*, *>::init)
        Bukkit.getConsoleSender().sendMessage("${prefix}§a foi carregado!")
    }

    override fun onDisable() {
        services.forEach(Service<*, *>::stop)
        user.saveAllUsers()
        ServerScoreboard.task.cancel()
    }

    // -> Functions <- //
    private fun setupChat(): Boolean {
        val rsp = server.servicesManager.getRegistration(Chat::class.java)
        chat = rsp.provider
        return chat != null
    }

    private fun registerEvents(vararg event:Listener){
        for (e in event){
            server.pluginManager.registerEvents(e,this)
        }
    }

    private fun register(){
        // -> Commands <- //
        GamemodeCommand().register()
        TeleportCommand().register()
        SudoCommand().register()

        // -> Events <- //
        registerEvents(
                PlayerEvents(),
                UserEvents(),
                PlayerChatEvent(),
                TotemSignEvent())
        kit.register()

        // -> Services/Controller <- //

        // -> User Service & Controller <- //
    }

    private fun setupVar(){
        instance = this
        kit = KitManager()
        warp = WarpManager()
        gson = GsonBuilder().create()
        inventory = InventoryManager(this)
        inventory.init()
        user = UserManager()
        region = RegionManager()
        user.loadAllUsers()
        region.loadRegions()
        warp.loadAllWarps()
        setupChat()
    }

    companion object{
        @JvmStatic lateinit var instance:FantasyKitPvP
        lateinit var dataSource: Database
        lateinit var chat:Chat
        lateinit var user:UserManager
        lateinit var kit: KitManager
        lateinit var warp: WarpManager
        lateinit var inventory:InventoryManager
        lateinit var gson:Gson
        lateinit var region:RegionManager
    }
}