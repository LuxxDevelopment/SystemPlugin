package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import de.lukas.systemplugin.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MSG_Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!utils.senderInstanceCheck(sender, "Du must ein §aspieler§7 sein!")){return false;}

        Player p = (Player) sender;

        if(args.length >= 1){
            Player t = utils.gettargetPlayer(args[0], sender);
            t.sendMessage(Main.getPrefix() + "[§5MSG§7] " + args[1-args.length]);
            p.sendMessage(Main.getPrefix() + "[§5MSG§7] " + args[1-args.length]);
        } else {
            p.sendMessage(Main.getPrefix() + "Benutze /msg <playername> <message>");
        }
        return false;
    }
}
