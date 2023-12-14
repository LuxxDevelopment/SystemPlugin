package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class WeatherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
       if(!(sender instanceof Player)) {return false;}
       Player p = (Player)sender;

       if(args.length == 0){
           p.sendMessage(Main.getPrefix() + "Benutze /weather clear/rain/thunder");
           return false;
       } else if (args.length == 1) {
           if(p.hasPermission("system.weather")){
               if(args[0].equalsIgnoreCase("clear")){
                   p.getWorld().setThundering(false);
                   p.getWorld().setStorm(false);
                   p.sendMessage(Main.getPrefix() + "Set the weather to §aClear");
               } else if (args[0].equalsIgnoreCase("rain")) {
                   p.getWorld().setStorm(true);
                   p.sendMessage(Main.getPrefix() + "Set the weather to §aRain");
               } else if (args[0].equalsIgnoreCase("thunder")) {
                   p.getWorld().setStorm(true);
                   p.getWorld().setThundering(true);
                   p.sendMessage(Main.getPrefix() + "Set the weather to §aRain §7and §aThunder");
               }

           }
       }

        return false;
    }

}
