package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import de.lukas.systemplugin.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Msg_Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!utils.senderInstanceCheck(sender, "Du must ein §aspieler§7 sein!")){return false;}

        Player p = (Player) sender;

        if(args.length >= 1){
            Player t = utils.gettargetPlayer(args[0], sender); if(t==null){return false;}
            String msg = "";
            for (int i = 1; i < args.length; i++){
                msg = msg + " " + args[1];
            }
            p.sendMessage(Main.getPrefix() + "§a" + p.getName() + "§5 --> §c" + t.getName() + "§5>> " + msg);
            t.sendMessage(Main.getPrefix() + "§c" + t.getName() + "§5 --> §a" + p.getName() + "§5>> " + msg);

        } else {
            p.sendMessage(Main.getPrefix() + "Benutze /msg <playername> <message>");
        }
        return false;
    }
}
