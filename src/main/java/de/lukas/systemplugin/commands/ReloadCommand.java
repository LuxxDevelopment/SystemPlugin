package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ReloadCommand implements CommandExecutor {
    private final Server server;
    private final JavaPlugin plugin;

    public ReloadCommand(Server server, JavaPlugin plugin) {
        this.server = server;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (args.length == 1 && args[0].equalsIgnoreCase("data")) {
                sender.sendMessage(Main.getPrefix() + "Game Data reload started");
                Bukkit.reload();
                sender.sendMessage(Main.getPrefix() + "Game Data reloaded");
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("config")) {
                // Reload configuration files
                sender.sendMessage(Main.getPrefix() + "Configuration reload started");
                Bukkit.reloadData();
                sender.sendMessage(Main.getPrefix() + "Configuration reloaded.");
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("confirm")) {
                sender.sendMessage(Main.getPrefix() + "Game Data and configuration reload started");
                // Reload everything
                Bukkit.reloadData();
                Bukkit.reload();
                sender.sendMessage(Main.getPrefix() + "Game data and configuration reloaded.");
                return true;
            }  else {
                sender.sendMessage(Main.getPrefix() + "Game Data and configuration reload started");
                Bukkit.reloadData();
                Bukkit.reload();
                sender.sendMessage(Main.getPrefix() + "Game data and configuration reloaded.");
                return false;
            }
    }
}
