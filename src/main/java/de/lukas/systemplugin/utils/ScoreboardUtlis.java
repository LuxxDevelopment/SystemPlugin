package de.lukas.systemplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardUtlis {
    public static Scoreboard getBaseScoreboard(Player player){
        Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = s.registerNewObjective("main", "main", "§5Rabbit Craft");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.getScore("§awww.killerhase75.com").setScore(0);
        objective.getScore("§c").setScore(1);
        objective.getScore("Dein Leben").setScore(3);
        objective.getScore("§a").setScore(4);
        objective.getScore(player.isOp() ? "§cSTAFF" : "§7Spieler").setScore(5);
        objective.getScore("Dein Rang:").setScore(6);
        objective.getScore("§c").setScore(7);
        objective.getScore("Geld:").setScore(9);

        Team t = s.registerNewTeam("leben");
        t.addEntry(ChatColor.AQUA + "" + ChatColor.RED);
        objective.getScore(ChatColor.AQUA + "" + ChatColor.RED).setScore(2);

        Team x = s.registerNewTeam("money");
        x.addEntry(ChatColor.AQUA + "" + ChatColor.RED);
        objective.getScore(ChatColor.AQUA + "" + ChatColor.RED).setScore(8);

        x.setPrefix("§a" + Math.round(player.getHealth() * 100) / 100);
        //-                    9
        //Geld:                8
        //-                    7
        //Dein Rang:           6
        // ADMIN / SPIELER     5
        //-                    4
        //Deine Leben:         3
        //Leben         /      2
        //-                    1
        //www.killerhase75.com 0
        return s;
    }
}
