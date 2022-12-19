package fantasynetwork.fantasykitpvp.spawnprotection;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;

public class RegionManager {

    public List<Region> regions = Lists.newArrayList();

    public Region getRegion(String name){
        return regions.stream().filter(e -> e.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Region getRegionByLocation(Location loc) {
        return regions.stream().filter(ea -> ea.isOnProtection(loc)).findFirst().orElse(null);
    }

    public void loadRegions(){
        // Spawn
        Region rg = new Region("Spawn",new Location(Bukkit.getWorld("world"),100,100,100),new Location(Bukkit.getWorld("world"),150,150,150));
        regions.add(rg);
    }
}
