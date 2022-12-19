package fantasynetwork.fantasykitpvp.utils

import java.util.HashMap

class Cooldown(private val id: String, private val cooldownName: String, private val timeInSeconds: Int) {
    private var start: Long = 0

    val isInCooldown: Boolean
        get() {
            if (this.timeLeft >= 1) {
                return true
            } else {
                stop(id, cooldownName)
                return false
            }
        }

    val timeLeft: Int
        get() {
            val cooldown = getCooldown(id, cooldownName)
            var f = -1
            if (cooldown != null) {
                val now = System.currentTimeMillis()
                val cooldownTime = cooldown.start
                val totalTime = cooldown.timeInSeconds
                val r = (now - cooldownTime).toInt() / 1000
                f = (r - totalTime) * -1
            }
            return f
        }

    fun stop(id: String, cooldownName: String) {
        cooldowns.remove(id.toString() + cooldownName)
    }

    fun start() {
        this.start = System.currentTimeMillis()
        cooldowns[this.id.toString() + this.cooldownName] = this
    }

    companion object {
        var cooldowns: MutableMap<String, Cooldown> = HashMap()
    }

    fun getCooldown(id: String, cooldownName: String): Cooldown? {
        return cooldowns[id.toString() + cooldownName] as Cooldown
    }
}
