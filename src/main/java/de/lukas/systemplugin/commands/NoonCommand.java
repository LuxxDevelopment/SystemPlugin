package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class NoonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        setTimeAllWorlds(6000);
        sender.sendMessage(Main.getPrefix() + "Die zeit wurde auf §aMittags §7geändert");
        return false;
    }
    public void setTimeAllWorlds(long time){
        for (World world : Bukkit.getWorlds()) {
            world.setTime(time);
        }
    }
}
