package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("system.command.deop")) {
            if (Main.getLang() == "en")
            {
                sender.sendMessage(Main.getPrefix() + "You do not have permission to use this command.");
            } else if (Main.getLang() == "de") {
                sender.sendMessage(Main.getPrefix() + "Du hast leider nicht genug rechte um diesen befehl auszuführen.");
            }

            return true;
        }

        if (args.length < 1) {
            if (Main.getLang() == "en") {
                sender.sendMessage(Main.getPrefix() + "Usage: /deop <player>");
            } else if (Main.getLang() == "de") {
                sender.sendMessage(Main.getPrefix() + "Benutze: /deop <player>");
            }
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            if (Main.getLang() == "en") {
                sender.sendMessage(Main.getPrefix() + "Player not found.");
            } else if (Main.getLang() == "de") {
                sender.sendMessage(Main.getPrefix() + "Spieler nicht gefunden.");
            }
            return true;
        }

        player.setOp(false);
        Bukkit.broadcastMessage(Main.getPrefix() + player.getName() + " has had their operator status revoked.");

        return true;
    }
}
