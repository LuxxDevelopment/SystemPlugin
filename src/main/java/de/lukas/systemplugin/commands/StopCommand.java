package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class StopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(!sender.hasPermission("system.staff.run.server.stop")) {
            sender.sendMessage(Main.getPrefix() + "Du hast leider nicht genug berechtigungen dieses Befehl auszuführen");
            return false;
        }

        sender.sendMessage(Main.getPrefix() + "Server wird §abeendet");
        Bukkit.getServer().shutdown();
        return false;
    }
}
