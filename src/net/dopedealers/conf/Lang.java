package net.dopedealers.conf;

import net.dopedealers.Registry;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Lang
{
    private File file;
    private FileConfiguration config;

    public Lang() {
        this.file = new File(Registry.Instance.getDataFolder(), "lang.yml");
        this.config = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
        this.genConfig();
    }

    public void save() {
        try {
            this.config.save(this.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void genConfig() {
        if (!this.config.contains("alerts")) {
            this.config.set("prefix", (Object)"&8[&9XC&8] ");

            this.config.set("alerts", (Object)"&c%player% &7failed &7[&c%check%&7] [&bVL%vl%&7]");
        }

        this.save();
    }



    public String prefix() {
        return this.config.getString("prefix");
    }

    public String alert() {
        return this.config.getString("alerts");
    }
}
