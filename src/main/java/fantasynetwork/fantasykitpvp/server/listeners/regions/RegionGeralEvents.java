package fantasynetwork.fantasykitpvp.server.listeners.regions;

import fantasynetwork.fantasykitpvp.FantasyKitPvP;
import fantasynetwork.fantasykitpvp.spawnprotection.Region;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class RegionGeralEvents implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!e.getFrom().getBlock().equals(e.getTo().getBlock())) {
            Region to = FantasyKitPvP.region.getRegionByLocation(e.getTo());
            Region from = FantasyKitPvP.region.getRegionByLocation(e.getFrom());
            if (from == to) return;
            if (to != null) {
                if (FantasyKitPvP.kit.hasKit(p)) {
                    p.teleport(e.getFrom());
                    //vector para empurrar para fora
                }
            }
            if (from != null) {
                // vector para empurrar para fora
            }
        }
    }
    Region r = FantasyKitPvP.region.getRegion("Spawn");
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            Player p = (Player)e.getEntity();
            Player t = (Player)e.getDamager();
            if (r.isOnProtection(p.getLocation())
                    && !r.isOnProtection(t.getLocation())
                    || r.isOnProtection(t.getLocation())
                    && !r.isOnProtection(p.getLocation())
                    || r.isOnProtection(p.getLocation())
                    &&r.isOnProtection(t.getLocation()) ) {
                e.setCancelled(true);
            }

        }
    }
}
