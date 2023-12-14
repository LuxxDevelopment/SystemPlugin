package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {



        // Player player = Bukkit.getPlayer(args[0]);
        if(args.length == 0){
            if(!(s instanceof Player)){
                s.sendMessage(Main.getPrefix() + "You are not Player! Use: /heal <Player>");
            } else {
                Player p = (Player)s;
                p.setHealth(20);
                p.setFoodLevel(20);
                p.sendMessage(Main.getPrefix() + "Du wurdest §ageheilt");
                p.getScoreboard().getTeam("leben").setPrefix("§a" + Math.round((p.getHealth() * 100) / 100));
            }
        } else if (args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            assert target != null;
            target.sendMessage(Main.getPrefix() + "Du wurdest von " + s.getName() + " §ageheilt");
            target.setHealth(20);
            target.setFoodLevel(20);
            s.sendMessage(Main.getPrefix() + "Du hast " + args[0] + " §ageheilt");
            target.getScoreboard().getTeam("leben").setPrefix("§a" + Math.round((target.getHealth() * 100) / 100));
        } else {
            s.sendMessage(Main.getPrefix() + "Benutze /heal [spielername]");
        }
        return false;
    }
}
