package fantasynetwork.fantasykitpvp.users;

import com.google.common.collect.Lists;
import fantasynetwork.fantasykitpvp.mysql.connections.UserConnection;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class UserManager {

    private List<User> users = Lists.newArrayList();

    public List<User> getUsers() {
        return users;
    }
    public Optional<User> getUser(Predicate<User> p){
        return users.stream().filter(p).findFirst();
    }
    public User getUser(String name){
        return getUser(p -> p.getUuid().equals(Bukkit.getPlayer(name).getUniqueId())).orElse(null);
    }
    public User getUser(UUID uuid){
        return getUser(p -> p.getUuid().equals(uuid)).orElse(null);
    }
    public void loadAllUsers(){
        users.addAll(UserConnection.getInstance().getAllUsers());
    }
    public void saveAllUsers(){
        users.forEach(e-> UserConnection.getInstance().save(e));
    }
}
