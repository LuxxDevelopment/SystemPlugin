package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!s.hasPermission("system.fly")) {
            s.sendMessage(Main.getPrefix() + "Sorry you do not have permission to run This command");
            return true;
        }


        if(!(s instanceof Player)) {
            s.sendMessage(Main.getPrefix() + "How did you think you could fly??");
        }

        Player p = (Player)s;

        if(args.length == 0){

            if(p.getAllowFlight()){
                p.setAllowFlight(false);
                p.sendMessage(Main.getPrefix() + "Your Flight mode has been §cdisabled!");
            } else {
                p.setAllowFlight(true);
                p.sendMessage(Main.getPrefix() + "Your Flight mode has been §aactivated!");
            }

            return true;
        }

        if(args.length == 1){

            Player t = Bukkit.getPlayer(args[0]);

            if(t == null){
                s.sendMessage(Main.getPrefix() + "Player was not Found!");
                return true;
            }

            if(p.getName().equals(t.getName())){
                if(p.getAllowFlight()){
                    p.setAllowFlight(false);
                    p.sendMessage(Main.getPrefix() + "Your Flight mode has been §cdisabled!");
                    return false;
                } else {
                    p.setAllowFlight(true);
                    p.sendMessage(Main.getPrefix() + "Your Flight mode has been §aactivated!");
                    return false;
                }
            }

            if(t.getAllowFlight()){
                t.setAllowFlight(false);
                t.sendMessage(Main.getPrefix() + "Your Flight mode has been §c disabled§7!");
                s.sendMessage(Main.getPrefix() + "§aSuccessfully §7disabled the Flight mode for: §a" + t.getName());
            } else {
                t.setAllowFlight(true);
                t.sendMessage(Main.getPrefix() + "Your Flight mode has been §a enabled§7!");
                s.sendMessage(Main.getPrefix() + "§aSuccessfully §7enabled the Flight mode for: §a" + t.getName());
            }

            return false;

        }

        return false;
    }
}
