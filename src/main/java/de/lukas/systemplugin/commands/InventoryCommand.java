package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        Inventory inventory = Bukkit.createInventory(null, 3*9, "§7Player Managment");
        inventory.setItem(10, new ItemBuilder(Material.PAPER).setDisplayname("§aSpieler Information").setLore("§7Name: " + p.getName(), "§7UUID: " + p.getUniqueId(), "§7Current Gamemode: §a" + p.getGameMode()).build());

        if(p.getGameMode() == GameMode.CREATIVE){
            inventory.setItem(12, new ItemBuilder(Material.IRON_SWORD).setDisplayname("§aGamemode Switch").setLore("","§cCreative", "", "§7Survival", "", "§7Spectator", "", "§7Adventure", "", "Switch zu surival!").setLocalizedName("creative").build());
        } else if (p.getGameMode() == GameMode.SURVIVAL){
            inventory.setItem(12, new ItemBuilder(Material.IRON_SWORD).setDisplayname("§aGamemode Switch").setLore("","§7Creative", "", "§cSurvival", "", "§7Spectator", "", "§7Adventure", "", "Switch zu spectator!").setLocalizedName("survival").build());
        } else if(p.getGameMode() == GameMode.SPECTATOR){
            inventory.setItem(12, new ItemBuilder(Material.IRON_SWORD).setDisplayname("§aGamemode Switch").setLore("","§7Creative", "", "§7Survival", "", "§cSpectator", "", "§7Adventure", "", "Switch zu adventure!").setLocalizedName("spectator").build());
        } else if(p.getGameMode() == GameMode.ADVENTURE){
            inventory.setItem(12, new ItemBuilder(Material.IRON_SWORD).setDisplayname("§aGamemode Switch").setLore("","§7Creative", "", "§7Survival", "", "§7Spectator", "", "§cAdventure", "", "Switch zu creative!").setLocalizedName("adventure").build());
        }

        inventory.setItem(14, new ItemBuilder(Material.GRASS_BLOCK).setDisplayname("§aZum Spawn").setLocalizedName("spawn").build());

        if(!(p.getWorld().hasStorm())){
            inventory.setItem(16, new ItemBuilder(Material.CLOCK).setDisplayname("§aWeather Switch").setLore("", "§aClear", "", "§7Rain", "", "§7Thunder", "", "§bSwitches to §a Rain").setLocalizedName("clear").build());
        } else if (p.getWorld().hasStorm()){
            inventory.setItem(16, new ItemBuilder(Material.CLOCK).setDisplayname("§aWeather Switch").setLore("", "§7Clear", "", "§aRain", "", "§7Thunder", "", "§bSwitches to §a Clear").setLocalizedName("rain").build());
        } else if (p.getWorld().isThundering()){
            inventory.setItem(16, new ItemBuilder(Material.CLOCK).setDisplayname("§aWeather Switch").setLore("", "§7Clear", "", "§7Rain", "", "§aThunder", "", "§bSwitches to §a Clear").setLocalizedName("thunder").build());

        }


        p.openInventory(inventory);



        return false;
    }
}
