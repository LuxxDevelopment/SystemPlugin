package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class BanCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            if (Main.getLang() == "de") {
                sender.sendMessage(Main.getPrefix() + "Benutze: /ban <player> [grund...]");
            } else if (Main.getLang() == "en") {
                sender.sendMessage(Main.getPrefix() + "Usage: /ban <player> [reason...]");
            }
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            if (Main.getLang() == "de") {
                sender.sendMessage(Main.getPrefix() + "Spieler nicht gefunden: §a" + args[0]);
            } else if (Main.getLang() == "en") {
                sender.sendMessage(Main.getPrefix() + "Player was not found: §a" + args[0]);
            }
            return true;
        }
        String reason = null;
        if (Main.getLang() == "de") {
            reason = (args.length > 1) ? String.join(Main.getPrefix() + " ", Arrays.copyOfRange(args, 1, args.length)) : Main.getPrefix() + "Du wurdest von einem administrator Gebannt";
        } else if (Main.getLang() == "en") {
            reason = (args.length > 1) ? String.join(Main.getPrefix() + " ", Arrays.copyOfRange(args, 1, args.length)) : Main.getPrefix() + "You were banned by an administrator";
        }

        player.kickPlayer(reason);
        Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), reason, null, sender.getName());

        if (Main.getLang() == "de") {
            Bukkit.broadcastMessage(Main.getPrefix() + player.getName() + " wurde von " + sender.getName() + " gebend grund: " + reason);
        } else if (Main.getLang() == "en") {
            Bukkit.broadcastMessage(Main.getPrefix() + player.getName() + " wurde von " + sender.getName() + " gebend grund: " + reason);
        }
        return true;
    }
}
