package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("system.command.op")) {
            sender.sendMessage(Main.getPrefix() + "You do not have permission to use this command.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(Main.getPrefix() + "Usage: /op <player>");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(Main.getPrefix() + "Player not found.");
            return true;
        }

        player.setOp(true);
        sender.sendMessage(Main.getPrefix() + "I Highly suggest You use a Permission management Plugin like Luckperms: https://luckperms.net");
        Bukkit.broadcastMessage(Main.getPrefix() + "§a" + player.getName() + "§7 has been given operator status.");

        return false;
    }
}
