package net.dopedealers.commands;

import net.dopedealers.Registry;
import net.dopedealers.util.C;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlertsCommand implements CommandExecutor
{
    private Registry ac;

    public AlertsCommand(final Registry ac) {
        this.ac = ac;
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String alias, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You have to be a player to run this command!");
            return true;
        }
        final Player player = (Player)sender;
        if (!player.hasPermission("ac.staff")) {
            sender.sendMessage(String.valueOf(C.Red) + "No permission.");
            return true;
        }
        if (this.ac.hasAlertsOn(player)) {
            this.ac.toggleAlerts(player);
            player.sendMessage(String.valueOf(Registry.Instance.PREFIX) + "Alerts toggled " + C.Red + "OFF");
        }
        else {
            this.ac.toggleAlerts(player);
            player.sendMessage(String.valueOf(Registry.Instance.PREFIX) + "Alerts toggled " + C.Green + "ON");
        }
        return true;
    }
}