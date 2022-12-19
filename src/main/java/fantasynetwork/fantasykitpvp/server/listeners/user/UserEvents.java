package fantasynetwork.fantasykitpvp.server.listeners.user;

import fantasynetwork.fantasykitpvp.FantasyKitPvP;
import fantasynetwork.fantasykitpvp.users.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UserEvents implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        User user = FantasyKitPvP.user.getUser(p.getName());
        if (user == null){
            user = new User(p.getUniqueId());
            FantasyKitPvP.user.getUsers().add(user);
        }
    }
}
