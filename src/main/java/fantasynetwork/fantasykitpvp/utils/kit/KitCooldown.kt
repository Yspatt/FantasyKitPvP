package fantasynetwork.fantasykitpvp.utils.kit

import java.util.HashMap
import fantasynetwork.fantasykitpvp.kits.Kit
import org.bukkit.entity.Player

class KitCooldown(var timeInSeconds: Long) {
    
    /*
    -> Uso: val cooldown = Cooldown(tempo)
            cooldown.addPlayer(jogador) -> Adiciona o player no cooldown
            cooldown.start() -> Ininicia o cooldown
            cooldown.getTime()-> Retorna o tempo atual
            cooldown.getRemaining() -> Retorna o tempo que falta para acabar
    
     */

    companion object {
        var cooldowns: MutableMap<Player, KitCooldown> = HashMap()
    }

    fun addPlayer(player:Player){
        cooldowns[player] = this
    }
    
    fun getCooldown(player : Player) : KitCooldown = cooldowns[player] as KitCooldown

    fun start(){

    }
}