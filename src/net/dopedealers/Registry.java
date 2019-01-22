package net.dopedealers;


import net.dopedealers.commands.AlertsCommand;
import net.dopedealers.conf.Lang;
import net.dopedealers.util.Config;
import net.dopedealers.xray.XC;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Registry
extends JavaPlugin
implements Listener
{

    public static Registry Instance;
    public String PREFIX;
    public String STAFF;
    public String ADMIN;
    public List<Player> AlertsOn;
    public Lang lang;
    public XC xray;
    public Config mainConfig;
    public Random rand;

    public Registry() {
        this.rand = new Random();
        this.STAFF = "ac.staff";
        this.ADMIN = "ac.admin";
        this.AlertsOn = new ArrayList<Player>();
    }

    public void onEnable() {
        this.RegisterListener((Listener)this);
        Registry.Instance = this;
        this.lang = new Lang();
        this.PREFIX = this.color(this.lang.prefix());
        this.xray = new XC(this);
        this.getCommand("alerts").setExecutor((CommandExecutor)new AlertsCommand(this));

        this.mainConfig.setDefault("xrayduration", 30);
        this.mainConfig.setDefault("SilenceDay", false);

        }

    public void onDisable() {

    }

    public String color(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public boolean hasAlertsOn(final Player player) {
        return this.AlertsOn.contains(player);
    }

    public void toggleAlerts(final Player player) {
        if (this.hasAlertsOn(player)) {
            this.AlertsOn.remove(player);
        }
        else {
            this.AlertsOn.add(player);
        }
    }

    public void alert(final String message) {
        for (final Player playerplayer : this.AlertsOn) {
            playerplayer.sendMessage(String.valueOf(this.PREFIX) + message);
        }
    }
    public void RegisterListener(final Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, (Plugin)this);
    }
    public Config getMainConfig() {
        return this.mainConfig;
    }

}
