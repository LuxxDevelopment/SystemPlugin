package de.lukas.systemplugin.events;

import de.lukas.systemplugin.Main;
import de.lukas.systemplugin.commands.FreezeCommand;
import de.lukas.systemplugin.utils.ScoreboardUtlis;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Listeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(Main.wartung && !e.getPlayer().hasPermission("system.staff.bypass.wartung")){
            e.getPlayer().kickPlayer(Main.getPrefix() + "\nDer Server ist zurzeit in wartungsarbeiten!");
            return;
        } else {
            e.setJoinMessage(Main.getPrefix() + "§a" + e.getPlayer().getName() + " §7has just joined the Server");
            e.getPlayer().setScoreboard(ScoreboardUtlis.getBaseScoreboard(e.getPlayer()));
        }
    }


    @EventHandler
    public void onDisconnect(PlayerQuitEvent e) {
        e.setQuitMessage(Main.getPrefix() + "§c" +e.getPlayer().getName() + " §7has left the Server");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){

    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;
        if (e.getView().getTitle() == "§7Player Managment") {
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().hasLocalizedName()) {
                switch (e.getCurrentItem().getItemMeta().getLocalizedName()) {
                    case "creative":
                        p.setGameMode(GameMode.SURVIVAL);
                        p.updateInventory();

                        break;
                    case "survival":
                        p.setGameMode(GameMode.SPECTATOR);
                        p.updateInventory();
                        break;
                    case "spectator":
                        p.setGameMode(GameMode.ADVENTURE);
                        p.updateInventory();
                        break;
                    case "adventure":
                        p.setGameMode(GameMode.CREATIVE);
                        p.updateInventory();
                        break;
                    case "spawn":
                        p.teleport(Bukkit.getWorld("world").getSpawnLocation());
                        p.closeInventory();
                        break;
                    case "clear":
                        p.getWorld().setStorm(true);
                        p.updateInventory();
                        break;
                    case "rain":
                        p.getWorld().setStorm(false);
                        p.updateInventory();
                        break;
                    case "thunder":
                        p.getWorld().setThundering(false);
                        p.updateInventory();
                        p.sendMessage(Main.getPrefix() + "How the fuck did you make it thunder? I tested and it didn't work... ");
                        break;

                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)){
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                Player p = (Player) e.getEntity();
                p.getScoreboard().getTeam("leben").setPrefix("§a" + Math.round((p.getHealth() * 100) / 100));
            }
        }.runTaskLater(Main.getInstance(), 1);
    }

    @EventHandler
    public void onDamage(EntityRegainHealthEvent e){
        if(!(e.getEntity() instanceof Player)){
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                Player p = (Player) e.getEntity();
                p.getScoreboard().getTeam("leben").setPrefix("§a" + Math.round((p.getHealth() * 100) / 100));
            }
        }.runTaskLater(Main.getInstance(), 1);
    }

    @EventHandler
    public void ServerPing(ServerListPingEvent e) {
        if(!Main.wartung){
            e.setMotd("§d   §m§l--§5§l§m-§8§m§l]-§r §c§lRabitcraft§r§8 ︳§r §fOnline Mode §8§l§m-[§5§l§m-§d§l§m--§r\n         §c§lUPDATE §r§8▸ §r§fThe server is §a§nonline§r§f.");
        } else if (Main.wartung) {
            e.setMotd("§7------------------§cWARTUNG§7-------------------"); // Wartung
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (FreezeCommand.freezed.contains(e.getPlayer())){
            e.setCancelled(true);
            e.getPlayer().sendMessage(Main.getPrefix() + "Du kannst dich zurzeit §cnicht§7 bewegen");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if (FreezeCommand.freezed.contains(e.getPlayer())){
            e.setCancelled(true);
            e.getPlayer().sendMessage(Main.getPrefix() + "Du kannst das zurzeit §cnicht§7 tun");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if (FreezeCommand.freezed.contains(e.getPlayer())){
            e.setCancelled(true);
            e.getPlayer().sendMessage(Main.getPrefix() + "Du kannst das zurzeit §cnicht§7 tun");
        }
    }

}



