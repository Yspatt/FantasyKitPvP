package fantasynetwork.fantasykitpvp.spawnprotection;

public class ZoneVector{
    private int x;
    private int y;
    private int z;

    public ZoneVector(int x, int y, int z){
        this.x = x;
        this.z = z;
        this.y = y;
    }

    public boolean isInAABB(ZoneVector min, ZoneVector max){
        return ((this.x <= max.x) && (this.x >=min.x) && (this.z <= max.z) && (this.z >= min.z) && (this.y <= max.y) && (this.y >= min.y));


    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }
}

