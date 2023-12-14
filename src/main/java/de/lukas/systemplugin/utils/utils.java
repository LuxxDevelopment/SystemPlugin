package de.lukas.systemplugin.utils;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class utils {

    public static boolean senderInstanceCheck(CommandSender sender, String noPlayerMessage){
        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + noPlayerMessage);
            return false;
        }
        return true;
    }


    public static boolean permissionCheck(String permission, CommandSender sender){
        if (!(sender.hasPermission(permission))){
            if(Main.getLang().equalsIgnoreCase("de")){
                sender.sendMessage(Main.getPrefix() + "Du hast leider nicht genug rechte um diesen befehl auszuführen");
                return false;
            } else if (Main.getLang().equalsIgnoreCase("en")) {
                sender.sendMessage(Main.getPrefix() + "Sorry but you don't have enough permissions to run this command");
                return false;
            }
        }
        return true;
    }

    public static Player gettargetPlayer(String targetPlayerName, CommandSender sender){
        Player t = Bukkit.getPlayer(targetPlayerName);
        if(t == null){
            sender.sendMessage(Main.getPrefix() + "Der spieler §c" + targetPlayerName + "§7 ist gerade offline.");
        }
        return t;
    }

    public static int getPlayerPing(CommandSender sender){
        return Objects.requireNonNull(sender.getServer().getPlayer(sender.getName())).getPing();
    }

    public static int getPlayerPingOther(CommandSender sender, Player targetPlayer){
        return Objects.requireNonNull(sender.getServer().getPlayer(targetPlayer.getName())).getPing();
    }


}
