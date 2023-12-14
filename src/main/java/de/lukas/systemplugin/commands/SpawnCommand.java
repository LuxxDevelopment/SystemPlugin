package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(args.length < 1){
            if(!sender.hasPermission("system.spawn.self")){
                sender.sendMessage(Main.getPrefix() + "Du hast leider nicht genug rechte um diesen befehl auszuführen");
                return false;
            }
            if(!(sender instanceof Player)) {
                sender.sendMessage(Main.getPrefix() + "You must be a player");
                return false;
            }
            Player p = (Player)sender;
            p.teleport(Bukkit.getWorld(p.getWorld().getUID()).getSpawnLocation());
            p.sendMessage(Main.getPrefix() + "Du wurdest zum §aspawn§7 teleportiert");
        } else if (args.length == 1) {
            if(!sender.hasPermission("system.spawn.other")) {
                sender.sendMessage(Main.getPrefix() + "Du hast leider nicht genug rechte um diesen befehl auszuführen");
                return false;
            }

            Player t = Bukkit.getPlayer(args[0]);
            if(t == null){
                sender.sendMessage(Main.getPrefix() + "Konnte den spieler nicht finden!");
                return false;
            }

            t.teleport(Bukkit.getWorld(t.getWorld().getUID()).getSpawnLocation());
            t.sendMessage(Main.getPrefix() + "Du wurdest zum §aspawn§7 teleportiert");
            sender.sendMessage(Main.getPrefix() + "Du hast §a" + t.getName() + "§7 zum welt §aspawn§7 telepotiert");

        }
        return false;
    }
}
