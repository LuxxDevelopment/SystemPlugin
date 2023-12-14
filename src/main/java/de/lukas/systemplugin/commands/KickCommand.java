package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        System.out.println(cmd.getName());
        if(cmd.getName().equalsIgnoreCase("kickall")){
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer(getReason(args));
            }
                sender.sendMessage(Main.getPrefix() + "Alle Spieler Wurde gekickt.");
        } else {
            if (args.length < 1) {
                sender.sendMessage(Main.getPrefix() + "Benutze: /kick <player> [reason]");
                return true;
            }


            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(Main.getPrefix() + "Spieler mit namen: §c" + args[0] + " §7nicht gefunden");
                return true;
            }
            player.kickPlayer(getReason(args));
            sender.sendMessage(Main.getPrefix() + "Spieler §c" + player.getName() + " §7wurde kicked.");
        }
        return true;
    }

    private String getReason(String[] args) {
        if (args.length > 1) {
            return String.join(" ", args).substring(args[0].length() + 1);
        } else {
            return "Kicked by an operator.";
        }
    }
}
