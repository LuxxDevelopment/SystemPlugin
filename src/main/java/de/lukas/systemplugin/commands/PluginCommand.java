package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class PluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
        if(!sender.hasPermission("system.staff.run.pl")){
            sender.sendMessage(Main.getPrefix() + "Du hast leider §cnicht§7 genug berechtigungen diesen Befehl auszuführen");
            return false;
        }
        sender.sendMessage(Main.getPrefix() + "Plugins: " +  Arrays.toString(plugins));
        return false;
    }
}
