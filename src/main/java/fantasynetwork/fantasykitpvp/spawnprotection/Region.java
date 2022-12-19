package fantasynetwork.fantasykitpvp.spawnprotection;

import org.bukkit.Location;

public class Region {

    private String name;
    private Location pos1,pos2;


    public Region(String name, Location pos1, Location pos2){
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }
    public String getName() {
        return name;
    }

    public Location getPos1() {
        return pos1;
    }

    public Location getPos2() {
        return pos2;
    }



    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }


    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public boolean isOnProtection(Location loc){
        ZoneVector curr = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        ZoneVector min = new ZoneVector(Math.min(pos1.getBlockX(), pos2.getBlockX()), Math.min(pos1.getBlockY(), pos2.getBlockY()), Math.min(pos1.getBlockZ(), pos2.getBlockZ()));
        ZoneVector max = new ZoneVector(Math.max(pos1.getBlockX(), pos2.getBlockX()), Math.max(pos1.getBlockY(), pos2.getBlockY()), Math.max(pos1.getBlockZ(), pos2.getBlockZ()));
        return curr.isInAABB(min, max);
    }



}
