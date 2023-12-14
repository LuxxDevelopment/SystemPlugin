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

public class FreezeCommand implements CommandExecutor {

    public static List<Player> freezed = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(args.length < 1) {
            if(!sender.hasPermission("system.staff.run.freeze.self")) {
                sender.sendMessage(Main.getPrefix() + "Du hast leider nicht genug rechte um diesen befehl auszuführen");
                return false;
            }
            Player p = (Player)sender;
            if(freezed.contains(p)){
                freezed.remove(p);
                sender.sendMessage(Main.getPrefix() + "Du hast dich aufgetaut");
            } else {
                freezed.add(p);
                sender.sendMessage(Main.getPrefix() + "Du bist $cangefrohren");
            }
        } else if (args.length == 1) {
            if(!sender.hasPermission("system.staff.run.system.freeze.other")) {
                sender.sendMessage(Main.getPrefix() + "Du hast leider nicht genug rechte um diesen befehl auszuführen");
                return false;
            }

            Player t = Bukkit.getPlayer(args[0]);
            if(t == null){
                sender.sendMessage(Main.getPrefix() + "Konnte den spieler §cnicht§7 finden!");
                return false;
            }
            if(freezed.contains(t)){
                freezed.remove(t);
                sender.sendMessage(Main.getPrefix() + "Du hast §a" + t.getName() + "§7 aufgetaut");
                t.sendMessage(Main.getPrefix() + "Du wurdest von §a" + sender.getName() + "§7 aufgetaut");
            } else {
                freezed.add(t);
                sender.sendMessage(Main.getPrefix() + "Du hast §a" + t.getName() + "§7 eingefroren");
                t.sendMessage(Main.getPrefix() + "Du wurdest von §a" + sender.getName() + "§7 eingefroren");
            }

        }
        return false;
    }
}
