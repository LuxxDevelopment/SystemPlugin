package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import de.lukas.systemplugin.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.command.BufferedCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PingCommand implements CommandExecutor {

    String pingmessage;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(args.length == 0){
            if(!(sender.hasPermission("system.run.ping.self"))){
                sender.sendMessage(Main.getPrefix() + "Du hast leider nicht genug rechte um diesen befehl auszuführen");
                return false;
            }
            if(!utils.senderInstanceCheck(sender, "Benutze /ping <playername>")){return false;}
            if(utils.getPlayerPing(sender) >= 100) {
                pingmessage = "§c" + utils.getPlayerPing(sender);
            } else {
                pingmessage = "§a" + utils.getPlayerPing(sender);
            }
            sender.sendMessage(Main.getPrefix() + "Dein derzeitiger ping ist: " + pingmessage);
        } else if (args.length == 1){
            if(!utils.permissionCheck("system.run.ping.other", sender)){return false;}
            Player t = utils.gettargetPlayer(args[0], sender);
            if(utils.getPlayerPingOther(sender, t) >= 100) {
                pingmessage = "§c" + utils.getPlayerPingOther(sender, t);
            } else {
                pingmessage = "§a" + utils.getPlayerPingOther(sender, t);
            }
            t.sendMessage(Main.getPrefix() + sender.getName() + " hat deinen §aping§7 gecheckt");
            sender.sendMessage(Main.getPrefix() + "Der derzeitige ping von " + t.getName() + " ist " + pingmessage);
        }
        return false;
    }
}
