package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.GameMode.*;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)) {return false;}

        Player p = (Player) sender;

        if(args.length == 1){
            if(p.hasPermission("system.gamemode")){
                if(args[0].equalsIgnoreCase("creative")){
                    gamemodeSwtich(p, CREATIVE);
                } else if (args[0].equalsIgnoreCase("survival")){
                    gamemodeSwtich(p, SURVIVAL);
                } else if (args[0].equalsIgnoreCase("spectator")){
                    gamemodeSwtich(p, SPECTATOR);
                } else if (args[0].equalsIgnoreCase("adventure")){
                    gamemodeSwtich(p, ADVENTURE);
                } else if (args[0].equalsIgnoreCase("1")){
                    gamemodeSwtich(p, CREATIVE);
                } else if (args[0].equalsIgnoreCase("0")){
                    gamemodeSwtich(p, SURVIVAL);
                } else if (args[0].equalsIgnoreCase("2")){
                    gamemodeSwtich(p, SPECTATOR);
                } else if (args[0].equalsIgnoreCase("3")){
                    gamemodeSwtich(p, ADVENTURE);
                }
            }
        } else if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[1]);
            if(p.hasPermission("system.other.gamemode")){
                if(args[0].equalsIgnoreCase("creative")) {
                    targetgamemodeSwitch(p, target, CREATIVE);
                } else if(args[0].equalsIgnoreCase("survival")){
                    targetgamemodeSwitch(p,target , SURVIVAL);
                }else if(args[0].equalsIgnoreCase("spectator")){
                    targetgamemodeSwitch(p,target , SPECTATOR);
                } else if(args[0].equalsIgnoreCase("adventure")){
                    targetgamemodeSwitch(p,target , ADVENTURE);
                } else if(args[0].equalsIgnoreCase("0")){
                    targetgamemodeSwitch(p,target , SURVIVAL);
                } else if(args[0].equalsIgnoreCase("1")){
                    targetgamemodeSwitch(p,target , CREATIVE);
                } else if(args[0].equalsIgnoreCase("2")){
                    targetgamemodeSwitch(p,target , SPECTATOR);
                } else if(args[0].equalsIgnoreCase("3")){
                    targetgamemodeSwitch(p,target , ADVENTURE);
                }
            }
        }

        return false;
    }

    public void gamemodeSwtich(Player p, GameMode Gamemode){
        p.setGameMode(Gamemode);
        p.sendMessage(Main.getPrefix() + "Your Gamemode changed to §a" + Gamemode.name().toUpperCase());

    }

    public void targetgamemodeSwitch(Player p, Player target, GameMode Gamemode){
        target.setGameMode(Gamemode);
        p.sendMessage(Main.getPrefix() + "Targets Gamemode changed to §a" + Gamemode.name().toUpperCase());
        target.sendMessage(Main.getPrefix() + "Your Gamemode changed to §a" + Gamemode.name().toUpperCase());
    }
}
