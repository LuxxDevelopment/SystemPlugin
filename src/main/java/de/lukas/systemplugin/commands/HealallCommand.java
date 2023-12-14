package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealallCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(args.length == 0) {
            for( Player all : Bukkit.getOnlinePlayers()){
                all.setHealth(20);
                all.setFoodLevel(20);
                all.getScoreboard().getTeam("leben").setPrefix("§a" + Math.round((all.getHealth() * 100) / 100));
            }
        }

        sender.sendMessage(Main.getPrefix() + "Alle spieler wurden §ageheielt");
        return false;
    }
}
