package net.dopedealers.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Config
{
    private FileConfiguration Config;
    private java.io.File File;
    private String Name;

    public Config(final JavaPlugin Plugin, final String Path, final String Name) {
        (this.File = new File(Plugin.getDataFolder() + Path)).mkdirs();
        this.File = new File(Plugin.getDataFolder() + Path, String.valueOf(Name) + ".yml");
        try {
            this.File.createNewFile();
        }
        catch (IOException ex) {}
        this.Name = Name;
        this.Config = (FileConfiguration) YamlConfiguration.loadConfiguration(this.File);
    }

    public String getName() {
        return this.Name;
    }

    public FileConfiguration getConfig() {
        return this.Config;
    }

    public void setDefault(final String Path, final Object Set) {
        if (!this.getConfig().contains(Path)) {
            this.Config.set(Path, Set);
            this.save();
        }
    }

    public void save() {
        try {
            this.Config.save(this.File);
        }
        catch (IOException ex) {}
    }

    public void reload() {
        this.Config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.File);
    }
}
