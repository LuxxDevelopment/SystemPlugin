package de.lukas.systemplugin.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileManager {

    private final JavaPlugin plugin;
    private final String dataFolder;

    public FileManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.dataFolder = plugin.getDataFolder().getAbsolutePath();
    }

    public List<String> loadBadWords() {
        File configFile = new File(dataFolder, "badwords.yml");

        if (!configFile.exists()) {
            plugin.saveDefaultConfig();
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        return config.getStringList("badWords");
    }

    public List<String> loadAdminList() {
        File configFile = new File(dataFolder, "adminlist.yml");

        if (!configFile.exists()) {
            plugin.saveDefaultConfig();
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        return config.getStringList("adminlist");
    }

    public void saveBadWords(List<String> badWords) {
        File configFile = new File(dataFolder, "badwords.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.set("badWords", badWords);

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveAdminList(List<String> admins) {
        File configFile = new File(dataFolder, "adminlist.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.set("admins", admins);

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
