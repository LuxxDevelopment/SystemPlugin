package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.jetbrains.annotations.NotNull;

public class TimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("system.time")){
            if(args.length == 1) {
                sender.sendMessage(Main.getPrefix() + "Es ist: §a" + Bukkit.getWorld("world").getTime() + "§7 in der Main welt");

                return true;
            } else if (args.length >= 1) {
                if(args[0].equalsIgnoreCase("set")) {
                    if(args[1].equalsIgnoreCase("day")){
                        setTimeAllWorlds(1000);
                        sender.sendMessage(Main.getPrefix() + "Die zeit wurde auf §aTag §7geändert");
                    } else if (args[1].equalsIgnoreCase("night")) {
                        setTimeAllWorlds(13000);
                        sender.sendMessage(Main.getPrefix() + "Die zeit wurde auf §aNacht §7geändert");
                    } else if (args[1].equalsIgnoreCase("noon")) {
                        setTimeAllWorlds(6000);
                        sender.sendMessage(Main.getPrefix() + "Die zeit wurde auf §aMittags §7geändert");
                    } else if (args[1].equalsIgnoreCase("midnight")) {
                        setTimeAllWorlds(18000);
                        sender.sendMessage(Main.getPrefix() + "Die zeit wurde auf §aMitternacht §7geändert");
                    } else {
                        try
                        {
                            setTimeAllWorlds(Long.parseLong(args[1]));
                        } catch (Exception e){
                            sender.sendMessage(Main.getPrefix() + "Benutze /time add/set/query");
                        }

                    }
                } else if (args[0].equalsIgnoreCase("add")) {
                    try
                    {
                        addTimeAllWorlds(Long.parseLong(args[1]));
                        sender.sendMessage(Main.getPrefix() + "Es wurden " + args[1] + " zur zeit §aHinugefügt");
                    } catch (Exception e){
                        sender.sendMessage(Main.getPrefix() + "Es gabe einen fehler bitte versuche es erneut!");
                        return true;
                    }

                } else if (args[0].equalsIgnoreCase("query")) {
                    sender.sendMessage(Main.getPrefix() + "Es ist: §a" + Bukkit.getWorld("world").getTime() + " §7in der Main welt");
                }
            } else {
                sender.sendMessage(Main.getPrefix() + "Es ist: §a" + Bukkit.getWorld("world").getTime() + " §7in der Main welt");
            }
        }

        return false;
    }

    public void setTimeAllWorlds(long time){
        for (World world : Bukkit.getWorlds()) {
            world.setTime(time);
        }
    }

    public void addTimeAllWorlds(long time){
        for (World world: Bukkit.getWorlds()) {
            long currentTime = world.getTime();
            world.setTime(currentTime + time);
        }
    }
}
