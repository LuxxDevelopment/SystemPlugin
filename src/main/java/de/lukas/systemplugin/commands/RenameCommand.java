package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class RenameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {


        if (args.length == 1) {
            if(!(sender instanceof Player)){
                sender.sendMessage(Main.getPrefix() + "Benutze /rename [spielername] [itemname]");
                return false;
            }
            Player player = (Player) sender;
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            String newName = String.join(" ", args);
            newName = newName.replace("&", "§");
            meta.setDisplayName(newName);
            item.setItemMeta(meta);
            player.sendMessage(Main.getPrefix() + "Item wurde §aumbenannt§7 nach: " + newName);
        } else if (args.length == 2) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null){
                sender.sendMessage(Main.getPrefix() + "Der spieler konnte nicht gefunden werden");
                return false;
            }
            ItemStack item = t.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            String newName = String.join(" ", args[1]);
            newName = newName.replace("&", "§");
            meta.setDisplayName(newName);
            item.setItemMeta(meta);
            t.sendMessage(Main.getPrefix() + "Item wurde §aumbenannt§7 nach: " + newName);
            sender.sendMessage(Main.getPrefix() + "Item wurde §aumbenannt§7 nach: " + newName);
        } else {
            sender.sendMessage(Main.getPrefix() + "Benutze /rename [spielername] [itemname]");
        }
        return false;
    }
}
