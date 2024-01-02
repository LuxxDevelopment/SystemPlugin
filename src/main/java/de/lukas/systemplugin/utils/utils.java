package de.lukas.systemplugin.utils;

import de.lukas.systemplugin.Main;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class utils {

    public static void setWartung(boolean wartung){
        File f = new File("plugins/RC/wartung.yml");
        YamlConfiguration cfg = new YamlConfiguration();
        if(!f.exists()){utils.CreateWartungConfig();}
        try {
            cfg.load(f);
            cfg.set("system.wartung", wartung);
            Main.wartung = wartung;
            cfg.save(f);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void CreateWartungConfig(){
        File f = new File("plugins/RC/wartung.yml");
        YamlConfiguration cfg = new YamlConfiguration();
        try {
            if(f.exists()) {
                cfg.load(f);
                Main.wartung = cfg.getBoolean("system.wartung");
            } else {
                cfg.set("system.wartung", false);
                cfg.save(f);
                Main.wartung = false;
            }
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean senderInstanceCheck(CommandSender sender, String noPlayerMessage){
        if(!(sender instanceof Player)){
            sender.sendMessage(Main.getPrefix() + noPlayerMessage);
            return false;
        }
        return true;
    }


    public static boolean permissionCheck(String permission, CommandSender sender){
        if (!(sender.hasPermission(permission))){
            if(Main.getLang().equalsIgnoreCase("de")){
                sender.sendMessage(Main.getPrefix() + "Du hast leider nicht genug rechte um diesen befehl auszuführen");
                return false;
            } else if (Main.getLang().equalsIgnoreCase("en")) {
                sender.sendMessage(Main.getPrefix() + "Sorry but you don't have enough permissions to run this command");
                return false;
            }
        }
        return true;
    }

    public static Player gettargetPlayer(String targetPlayerName, CommandSender sender){
        Player t = Bukkit.getPlayer(targetPlayerName);
        if(t == null){
            sender.sendMessage(Main.getPrefix() + "Der spieler §c" + targetPlayerName + "§7 ist gerade offline.");
            return null;
        }
        return t;
    }

    public static int getPlayerPing(CommandSender sender){
        return Objects.requireNonNull(sender.getServer().getPlayer(sender.getName())).getPing();
    }

    public static int getPlayerPingOther(CommandSender sender, Player targetPlayer){
        return Objects.requireNonNull(sender.getServer().getPlayer(targetPlayer.getName())).getPing();
    }

    public static void sendCopiableText(CommandSender player, String text, String code) {
        BaseComponent[] baseComponents = new ComponentBuilder(text)
                .event(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, code))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Klicke um deinen Code Zu Kopieren!").create()))
                .create();

        player.spigot().sendMessage(baseComponents);
    }

    public static String generateRandomPassword(int length) {
        String characters = "0123456789";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // Generate a random number using Random() class
            Random random = new Random();

            // Select a random character from the list of characters
            int randomIndex = random.nextInt(characters.length());
            char randomCharacter = characters.charAt(randomIndex);

            // Append the randomly selected character to the password
            password.append(randomCharacter);
        }

        // Return the generated password
        return password.toString();
    }
}
