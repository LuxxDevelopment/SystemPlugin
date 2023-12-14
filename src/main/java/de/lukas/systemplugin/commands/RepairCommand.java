package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.BufferedCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class RepairCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0){
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();

                if (meta instanceof Damageable) {
                    Damageable damageable = (Damageable) meta;
                    damageable.setDamage(0);
                    item.setItemMeta((ItemMeta) damageable);
                    player.sendMessage(Main.getPrefix() + "Das item wurde §arepariert");
                } else {
                    player.sendMessage(Main.getPrefix() + "Das item konnte §cnicht§7 repariert werden");
                }

                return true;
            }  else {
                sender.sendMessage(Main.getPrefix() + "Benutze /repair [username]");
            }
        } else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            ItemStack item = t.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();

            if (meta instanceof Damageable) {
                Damageable damageable = (Damageable) meta;
                damageable.setDamage(0);
                item.setItemMeta((ItemMeta) damageable);
                t.sendMessage(Main.getPrefix() + "Das item wurde von " + sender.getName() + " §arepariert");
                sender.sendMessage(Main.getPrefix() + "Du hast das item von " + t.getName() + " §arepariert");
            } else {
                sender.sendMessage(Main.getPrefix() + "Das item das " + t.getName() + " hält konnte nicht repariert werden");
            }

            return true;
        }
        return false;
    }
}
