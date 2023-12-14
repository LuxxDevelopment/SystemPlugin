package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KickallCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.getName().equalsIgnoreCase(sender.getName())) {
                player.kickPlayer(getReason(args));
            }

        }
        sender.sendMessage(Main.getPrefix() + "Alle Spieler außer du wurden gekickt.");
        return false;
    }

    private String getReason(String[] args) {
        if (args.length > 1) {
            return String.join(" ", args).substring(args[0].length() + 1);
        } else {
            return "Kicked by an operator.";
        }
    }
}
