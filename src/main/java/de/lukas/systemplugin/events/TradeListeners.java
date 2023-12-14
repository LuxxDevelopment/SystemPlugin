package de.lukas.systemplugin.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TradeListeners implements Listener {

    public HashMap<Player, Player> tradingPlayers = new HashMap<Player, Player>();

    public void addPlayersToTradelist(Player p1, Player p2){
        tradingPlayers.put(p1, p2);
    }

    @EventHandler
    public void onPlayerInventoryClos(InventoryCloseEvent e){
        Inventory inventory = (Inventory)e.getInventory();
        if(e.getView().getTitle().equals("TRADE MENU")){
            Player p = (Player) e.getPlayer();
           if(tradingPlayers.containsKey(p)){
               List<HumanEntity> viewers = inventory.getViewers();
               Player p1;
               Player p2;
               if(tradingPlayers.containsKey((Player) viewers.get(0))){
                   p1 = (Player) viewers.get(0);
                   p2 = (Player) viewers.get(1);
               } else {
                   p1 = (Player) viewers.get(1);
                   p2 = (Player) viewers.get(0);
               }
               p1.closeInventory();
               p2.closeInventory();

           } else {



           }
        }
    }

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent e){
        Inventory inventory = (Inventory)e.getInventory();
        if(e.getView().getTitle().equals("TRADE MENU")){
            Player p = (Player) e.getWhoClicked();
            if(tradingPlayers.containsKey(p)){
                if(e.getSlot() <= 8 || e.getSlot() == 17 || e.getSlot() >=27){
                    if(e.getSlot() == 17){
                        accept(p, Objects.requireNonNull(e.getCurrentItem()));
                        e.setCancelled(true);
                    }
                }else {
                    e.setCancelled(true);
                }

            } else {

                if(e.getSlot() >= 17){
                    if(e.getSlot() == 17){
                        accept(p, Objects.requireNonNull(e.getCurrentItem()));
                        e.setCancelled(true);
                    }
                } else {
                    e.setCancelled(true);
                }

            }
        }
    }

    public void accept(Player p, ItemStack item){
        if(item.getType().equals(Material.REDSTONE_BLOCK)) {
            item.setType(Material.EMERALD_BLOCK);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(p.getName());
            item.setItemMeta(meta);
        } else if (item.getType().equals(Material.EMERALD_BLOCK)) {
            if(item.getItemMeta().getDisplayName().equals(p.getName())) {
                item.setType(Material.REDSTONE_BLOCK);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(null);
                item.setItemMeta(meta);
            }
        } else {
            finsihTrade(p.getOpenInventory().getTopInventory());
        }
    }

    public void finsihTrade(Inventory inv){
        List<HumanEntity> viewers = inv.getViewers();
        Player p1;
        Player p2;
        if(tradingPlayers.containsKey((Player) viewers.get(0))){
            p1 = (Player) viewers.get(0);
            p2 = (Player) viewers.get(1);
        } else {
            p1 = (Player) viewers.get(1);
            p2 = (Player) viewers.get(0);
        }
        p1.closeInventory();
        p2.closeInventory();
        for(int i = 0; i<9; i++){
            if(inv.getItem(i) != null){
                p2.getInventory().addItem(Objects.requireNonNull(inv.getItem(i)));
            }
            if(inv.getItem(i + 18) != null){
                p1.getInventory().addItem(Objects.requireNonNull(inv.getItem(i + 18)));
            }
        }
        tradingPlayers.remove(p1);

    }

}
