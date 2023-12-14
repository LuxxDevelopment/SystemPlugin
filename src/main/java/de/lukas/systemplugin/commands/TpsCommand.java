package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.plugin.java.JavaPlugin;


public class TpsCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    private final Server server;
    private BukkitTask task;

    public TpsCommand(Server server, JavaPlugin plugin) {
        this.plugin = plugin;
        this.server = server;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tps")) {
            if (args.length == 0) {
                // Start displaying TPS
                task = new TpsDisplayTask().runTaskTimer(plugin, 0, 20);
                sender.sendMessage("Started TPS display.");
            } else if (args.length == 1 && args[0].equalsIgnoreCase("stop")) {
                // Stop displaying TPS
                if (task != null) {
                    task.cancel();
                    task = null;
                }
                sender.sendMessage("Stopped TPS display.");
            } else {
                sender.sendMessage("Invalid arguments. Usage: /tps [stop]");
                return false;
            }
            return true;
        }
        return false;
    }

    private static class TpsDisplayTask extends BukkitRunnable {
        @Override
        public void run() {
            double tps;
            tps = Math.min(20.0, Math.round((1.0 / (Bukkit.getAverageTickTime() * 1.0E-6)) * 100.0) / 100.0);
            Main.getInstance().getLogger().info("Server TPS: " + tps);
        }
    }
}
