package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import de.lukas.systemplugin.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WartungCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(!utils.permissionCheck("system.staff.run.wartung", sender)){return false;}
        if(Main.wartung){
            utils.setWartung(false);
            sender.sendMessage(Main.getPrefix() + "Wartungs modus wurde §cdeaktiviert");
        } else {
            utils.setWartung(true);
            sender.sendMessage(Main.getPrefix() + "Wartungs modus wurde §aaktiviert");
            for(Player p : Bukkit.getOnlinePlayers()){
                if(!p.hasPermission("system.staff.bypass.wartung")){
                    p.kickPlayer(Main.getPrefix() + "\nDer Server ist zurzeit in wartungsarbeiten!");
                }
            }
        }
        return false;
    }
}
