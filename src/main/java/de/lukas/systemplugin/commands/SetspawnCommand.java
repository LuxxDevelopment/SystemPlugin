package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetspawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(!sender.hasPermission("system.setspawn")) {
            sender.sendMessage(Main.getPrefix() + "Du hast leider nicht genug rechte um diesen befehl auszuführen");
            return false;
        }

        if(!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + "You must be a player");
            return false;
        }

        Player p = (Player)sender;

        Bukkit.getWorld(p.getWorld().getUID()).setSpawnLocation(p.getLocation());
        p.sendMessage(Main.getPrefix() + "Welten spawn wurde erfolgreich zu deiner §aposition§7 gesetzt");

        return false;
    }
}
