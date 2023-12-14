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

public class VanishCommand implements CommandExecutor {

    public static List<Player> vanished = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(args.length < 1){
            if(!s.hasPermission("system.staff.run.vanish.self")) {
                s.sendMessage(Main.getPrefix() + "You do not have enough permissions to run this command!");
                return false;
            }
            if(!(s instanceof Player)){
                s.sendMessage(Main.getPrefix() + "Try /vanish <Player>.");
                return false;
            }

            Player p = (Player)s;

            if(vanished.contains(p)){
                vanished.remove(p);
                p.sendMessage(Main.getPrefix() + "Your are now §aVisible §7to other players!");
                for(Player all : Bukkit.getOnlinePlayers()){
                    all.showPlayer(p);
                }
            } else {
                vanished.add(p);
                p.sendMessage(Main.getPrefix() + "Your are now §aInvisible §7to other players!");
                for(Player all : Bukkit.getOnlinePlayers()){
                    all.hidePlayer(p);
                }
            }
        } else if (args.length == 1){
            if(!s.hasPermission("system.staff.run.vanish.other")) {
                s.sendMessage(Main.getPrefix() + "You do not have enough permissions to run this command!");
                return false;
            }

            Player t = Bukkit.getPlayer(args[0]);
            if(t == null){
                s.sendMessage(Main.getPrefix() + "Couldn't find the player please check the name!");
            }

            if(vanished.contains(t)){
                vanished.remove(t);
                t.sendMessage(Main.getPrefix() + "You have been made §aVisible§7!");
                s.sendMessage(Main.getPrefix() + "Successfully§c deactivated §7" + t.getName() + "'s §7Invisibility mode");
                for(Player all : Bukkit.getOnlinePlayers()){
                    all.showPlayer(t);
                }
            } else {
                vanished.add(t);
                t.sendMessage(Main.getPrefix() + "You have been made §aInvisible§7!");
                s.sendMessage(Main.getPrefix() + "Successfully§a activated §7" + t.getName() + "'s Invisibility mode");
                for(Player all : Bukkit.getOnlinePlayers()){
                    all.hidePlayer(t);
                }
            }
        }


        return false;
    }
}
