package fantasynetwork.fantasykitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FantasyConfig {
    private File file;
    private FileConfiguration config;


    public FantasyConfig(String name, JavaPlugin m) {
        file = new File(m.getDataFolder(), name);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            m.saveResource(name, false);
        }

        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public void set(String path, Object value) {
        config.set(path, value);
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public Double getDouble(String path) {
        return config.getDouble(path);
    }

    public Integer getInt(String path) {
        return config.getInt(path);
    }

    public Boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public Object getObject(String path){ return config.get(path);}

    public void saveLocation(String path, Location loc) {
        set(path, loc.getWorld().getName() + "@" + loc.getBlockX() + "@" + loc.getBlockY() + "@" + loc.getBlockZ() + "@" + loc.getYaw() + "@" + loc.getPitch());
    }

    public Location getLocation(String path) {
        String[] s = getString(path).split("@");
        return new Location(Bukkit.getWorld(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Float.valueOf(s[4]), Float.valueOf(s[5]));
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return config.getConfigurationSection(path);
    }

    public List<String> getList(String path) {
        return config.getStringList(path);
    }
}
