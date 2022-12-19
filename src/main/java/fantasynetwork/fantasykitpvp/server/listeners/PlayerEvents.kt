package fantasynetwork.fantasykitpvp.server.listeners

import fantasynetwork.fantasykitpvp.FantasyKitPvP
import fantasynetwork.fantasykitpvp.kits.KitMainInventory
import fantasynetwork.fantasykitpvp.server.scoreboard.ServerScoreboard
import fantasynetwork.fantasykitpvp.users.User
import fantasynetwork.fantasykitpvp.utils.item.ItemStackBuilder
import fantasynetwork.fantasykitpvp.utils.player.clear
import fantasynetwork.fantasykitpvp.utils.player.getAccount
import fantasynetwork.fantasykitpvp.utils.player.removeKit
import fantasynetwork.fantasykitpvp.warps.WarpMainInventory
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.*
import org.bukkit.scheduler.BukkitRunnable
import sun.audio.AudioPlayer.player
import com.sun.xml.internal.ws.api.message.AddressingUtils.getAction
import fantasynetwork.fantasykitpvp.utils.chat.sendActionBar
import org.bukkit.entity.EntityType
import org.bukkit.util.Vector


class PlayerEvents: Listener {

    @EventHandler
    fun onJoin(e:PlayerJoinEvent){
        e.joinMessage = null
        e.player.restore()
        e.player.welcomeCosmetics()
        ServerScoreboard.build(e.player)
    }
    @EventHandler
    fun onQuit(e:PlayerQuitEvent){
        e.quitMessage = null
        e.player.removeKit()
    }
    @EventHandler
    fun onBreakBlock(e:BlockBreakEvent){
       // e.isCancelled = true
    }
    @EventHandler
    fun onPlaceBlock(e:BlockPlaceEvent){
        //e.isCancelled = true
    }

    /*
        Kill/Death warn && respawn
     */
    @EventHandler
    fun onDeath(e:PlayerDeathEvent){
        e.deathMessage = null
        e.entity.removeKit()
        if (e.entity.killer != null) {
            val killer = e.entity.killer
            val kAccount = killer.getAccount()
            val p = e.entity
            val pAccount = p.getAccount()
            kAccount.streak +=1
            val coins = if(kAccount.streak ==0)3 else 3 * kAccount.streak
            kAccount.coins += coins
            kAccount.kills += 1
            pAccount.streak = 0
            pAccount.deaths += 1
            killer.sendMessage("§7Você matou o jogador §a${p.name}§7. §6(+ $coins coins)")
            killer.sendActionBar("§6+3 coins")
            p.sendMessage("§7Você foi morto pelo jogador §a${killer.name}§7.")
            if (kAccount.streak %5 == 0){
                Bukkit.broadcastMessage("§a${killer.name} §7Está com um §aKillStreak§7 de §b${kAccount.streak}§7.")
                killer.world.spawnEntity(killer.location, EntityType.FIREWORK)
            }
        }
        object : BukkitRunnable() {
            override fun run() {
                e.entity.spigot().respawn()
            }
        }.runTaskLater(FantasyKitPvP.instance, 20)
    }

    @EventHandler
    fun onRespawn(e:PlayerRespawnEvent){
        e.player.restore()
    }
    @EventHandler
    fun onDropItem(e: PlayerDropItemEvent){
        if (e.itemDrop.itemStack.type != Material.BOWL){
            e.isCancelled = true
        }else{
            e.itemDrop.remove()
        }
    }
    @EventHandler
    fun onInteract(e:PlayerInteractEvent) {
        if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
            if (e.player.itemInHand != null &&e.player.itemInHand.type != Material.AIR &&  e.player.itemInHand.hasItemMeta()) {
                if (e.player.itemInHand.itemMeta.displayName.equals("§aSeletor de kits")) {
                    KitMainInventory.INVENTORY.open(e.player)
                } else if (e.player.itemInHand.itemMeta.displayName.equals("§aSeletor de warps")) {
                    WarpMainInventory.INVENTORY.open(e.player)
                } else {

                }
            }
        }
    }
    @EventHandler
    fun onSoup(e:PlayerInteractEvent){
        val p = e.player
        if (p.health == p.maxHealth) {
        } else {
            val regen = +7.0
            if ((e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) && p.itemInHand.type == Material.MUSHROOM_SOUP) {
                p.health = (if (p.health + regen > p.maxHealth) p.maxHealth else p.health + regen)
                p.itemInHand.type = Material.BOWL
                p.sendActionBar("§a+4")
            }
        }
    }


    fun Player.restore(){
        this.clear()
        inventory.setItem(1, ItemStackBuilder(Material.EMERALD).setName("§aSeletor de kits").build())
        inventory.setItem(4, ItemStackBuilder(Material.CHEST).setName("§aSeletor de warps").build())
    }

    fun Player.welcomeCosmetics(){
        for (i in 1..100){
            this.sendMessage("")
        }
        playSound(location, Sound.ANVIL_USE,1f,0.2f)
        this.sendMessage("§6Mensagem template")
        this.sendMessage("")
        this.sendMessage("§e Olá $name, seja bem vindo(a) ao campo de treinamento do reino!")
        this.sendMessage("§eMelhore suas habilidades no confronto aqui.")
        this.sendMessage("")
        this.sendMessage("")

        this.sendTitle("§5§lFANTASY §7§l- §e§lKITPVP","§aSeja bem-vindo explorador!")
    }

}