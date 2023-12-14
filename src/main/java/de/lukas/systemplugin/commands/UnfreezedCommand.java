package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static de.lukas.systemplugin.commands.FreezeCommand.freezed;

public class UnfreezedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Main.getPrefix() + "Benutze: /unfreeze <playername>");
                return false;
            }
            Player p = (Player) sender;
            if (freezed.contains(p)) {
                freezed.remove(p);
                p.sendMessage(Main.getPrefix() + "Du wurdest  aufgetaut");
                return true;
            } else {
                sender.sendMessage(Main.getPrefix() + "§a" + p.getName() + "§7 Der spieler ist nicht eingefrohren");
                return false;
            }
        } else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (freezed.contains(t)) {
                freezed.remove(t);
                sender.sendMessage(Main.getPrefix() + "§a" + t.getName() + "§7 wurde aufgetaut.");
                t.sendMessage(Main.getPrefix() + "Du wurdest  aufgetaut von" + sender.getName());
                return true;
            } else {
                sender.sendMessage(Main.getPrefix() + " Der spieler " + "§a" + t.getName() + "§7 ist nicht eingefrohren");

            }
            return false;
        } else {
            sender.sendMessage(Main.getPrefix() + "Benutze /unfreeze <playername>");
            return false;
        }
    }
}
