package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import de.lukas.systemplugin.events.TradeListeners;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class TradeCommand implements CommandExecutor {

    HashMap<Player,Player> requestTrade =new HashMap<Player, Player>();

    TradeListeners tradeList;

    public TradeCommand(TradeListeners listener){
        tradeList = listener;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + "Sorry aber nur ein Spieler kann diesen befehl bentzten");
        }
        Player p = (Player)sender;
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("request")){
                Player tradeWith = Bukkit.getPlayer(args[1]);
                if (tradeWith == null){
                    sender.sendMessage(Main.getPrefix() + args[1] + " ist derzeit nicht online");
                }
                p.sendMessage(Main.getPrefix() + "Du hast eine trade anfrage an §a" + args[1] + "§7 geschickt.");
                requestTrade.put(tradeWith, p);
                tradeWith.sendMessage(Main.getPrefix() + "Du hast von §a" + p.getName() + "§7 eine trade anfrage bekommen.");
            }
        } else if (args.length == 1) {
            if(args[0].equalsIgnoreCase("accept")){
                if(requestTrade.containsKey(p)){
                    Player tradeWith = requestTrade.get(p);
                    if(Bukkit.getOnlinePlayers().contains(tradeWith)){
                        Inventory tradeInv = Bukkit.createInventory(null, 27, "TRADE MENU");

                        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                        ItemStack button = new ItemStack(Material.REDSTONE_BLOCK);
                        tradeInv.setItem(9, glass);
                        tradeInv.setItem(10, glass);
                        tradeInv.setItem(11, glass);
                        tradeInv.setItem(12, glass);
                        tradeInv.setItem(13, glass);
                        tradeInv.setItem(14, glass);
                        tradeInv.setItem(15, glass);
                        tradeInv.setItem(16, glass);
                        tradeInv.setItem(17, button);

                        p.openInventory(tradeInv);
                        tradeWith.openInventory(tradeInv);
                        requestTrade.remove(p);
                        tradeList.addPlayersToTradelist(p, tradeWith);
                    }else {
                        p.sendMessage(Main.getPrefix() + "Der spieler hat den server leider verlassen");
                        requestTrade.remove(p);
                    }
                } else {
                    p.sendMessage(Main.getPrefix() + "Du hast §cleider§7 keine Trade anfragen");
                }
            }
        }

        return false;
    }
}
