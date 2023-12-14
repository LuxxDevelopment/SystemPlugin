package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WartungsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(args.length == 0) {
            if (Main.wartung == Boolean.FALSE){
                Main.wartung = Boolean.TRUE;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.getName().equalsIgnoreCase(sender.getName()) && !player.hasPermission("system.staff.bypass.wartung")) {
                        player.kickPlayer("§7[§5RabbitCraft§7]\n\n§cWartungsarbeiten\nMaintenance");
                        Main.wartungbann.add(player);
                    }

                }
            } else {
                Main.wartung = Boolean.FALSE;
                for (Player player : Main.wartungbann) {
                    Main.wartungbann.remove(player);
                }
            }
        }
        return false;
    }
}
