package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import de.lukas.systemplugin.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GiveAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!utils.senderInstanceCheck(sender,"Du musst leider ein §aSpieler§7 sein.")){return false;}
        if(!utils.permissionCheck("system.staff.run.give.all",sender)){return false;}
        Player p = (Player)sender;
        if((p.getItemInHand() != null) || (!p.getItemInHand().getType().equals(Material.AIR))){
            if(args.length != 0){p.sendMessage(Main.getPrefix() + "Benutze /giveall"); return false;}
            ItemStack is = p.getItemInHand();
            String ItemName = null;
            if(is.getItemMeta().hasDisplayName()){ItemName = is.getItemMeta().getDisplayName();} else {ItemName = is.getType().toString();}
            ItemName = ItemName.replaceAll("_", " ");

            Bukkit.broadcastMessage(Main.getPrefix() + "Allen Spielern wurde " + is.getAmount() + "mal §a" + ItemName + "§7 gegeben");
            for(Player t : Bukkit.getOnlinePlayers()){
                t.getInventory().addItem(is);
                t.updateInventory();
            }
        } else {
            p.sendMessage(Main.getPrefix() + "Du solltest schon etwas in deiner hand halten!");
        }

        return false;
    }
}
