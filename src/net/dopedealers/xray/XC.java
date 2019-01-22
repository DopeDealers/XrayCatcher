package net.dopedealers.xray;

import net.dopedealers.Registry;
import net.dopedealers.util.C;
import net.dopedealers.util.UtilTime;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class XC implements Listener, CommandExecutor {
    public Registry ac;
    public List<Player> xrayCatcher;
    public Map<UUID, Long> lastPatch;

    public XC(final Registry ac) {
        this.xrayCatcher = new ArrayList<Player>();
        this.lastPatch = new HashMap<UUID, Long>();
        this.ac = ac;
        this.ac.getCommand("xray").setExecutor((CommandExecutor)this);
        this.ac.RegisterListener((Listener)this);
    }

    @EventHandler
    public void breakblock(final BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getBlock().getType() != Material.DIAMOND_ORE) {
            return;
        }
        if (event.getBlock().hasMetadata("AlreadyDone")) {
            return;
        }
        final Player player = event.getPlayer();
        if (this.lastPatch.containsKey(player.getUniqueId())) {
            final Long time = this.lastPatch.get(player.getUniqueId());
            final Long maxtime = this.ac.getMainConfig().getConfig().getInt("xrayduration") * 1000L;
            if (UtilTime.nowlong() - time < maxtime) {
                for (final Player staff : this.xrayCatcher) {
                    staff.sendMessage(String.valueOf(Registry.Instance.PREFIX) + C.Reset + player.getName() + C.Gray + " might be using " + C.Blue + "Xray");
                }
            }
        }
        for (int x = -7; x < 7; ++x) {
            for (int y = -7; y < 7; ++y) {
                for (int z = -7; z < 7; ++z) {
                    final Location location = event.getBlock().getLocation().add((double)x, (double)y, (double)z);
                    if (location.getBlock().getType() == Material.DIAMOND_ORE) {
                        location.getBlock().setMetadata("AlreadyDone", (MetadataValue)new FixedMetadataValue((Plugin)this.ac, (Object)true));
                    }
                }
            }
        }
        this.lastPatch.put(player.getUniqueId(), UtilTime.nowlong());
    }

    @EventHandler
    public void blockplace(final BlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getBlock().getType() != Material.DIAMOND_ORE) {
            return;
        }
        event.getBlock().setMetadata("AlreadyDone", (MetadataValue)new FixedMetadataValue((Plugin)this.ac, (Object)true));
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String cmdname, final String[] args) {
        if (!sender.hasPermission(this.ac.STAFF)) {
            sender.sendMessage(String.valueOf(C.Red) + "No permission.");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command!");
            return true;
        }
        final Player player = (Player)sender;
        if (this.xrayCatcher.contains(player)) {
            sender.sendMessage(String.valueOf(Registry.Instance.PREFIX) + C.Gray + "Xray alerts " + C.Red + "OFF");
            this.xrayCatcher.remove(player);
        }
        else {
            sender.sendMessage(String.valueOf(Registry.Instance.PREFIX) + C.Gray + "Xray alerts " + C.Green + "ON");
            this.xrayCatcher.add(player);
        }
        return true;
    }
}

