package fantasynetwork.fantasykitpvp.users;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;

public class User {

    private UUID uuid;
    private Integer deaths =0;
    private Integer kills = 0;
    private Integer streak = 0;
    private Long coins = 0L;
    private List<String> kits = Lists.newArrayList();

    public User(UUID uuid){
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Integer getStreak() {
        return streak;
    }

    public void setStreak(Integer streak) {
        this.streak = streak;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Long getCoins() {
        return coins;
    }

    public void setCoins(Long coins) {
        this.coins = coins;
    }

    public List<String> getKits() {
        return kits;
    }

    public void setKits(List<String> kits) {
        this.kits = kits;
    }

}
