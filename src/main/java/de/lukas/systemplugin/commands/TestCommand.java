package de.lukas.systemplugin.commands;

import com.google.gson.Gson;
import de.lukas.systemplugin.Main;
import de.lukas.systemplugin.utils.DiscordWebhook;
import de.lukas.systemplugin.utils.Embed;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] strings) {
        DiscordWebhook.sendWebhook("[secret]", "Test");
        return false;
    }


}
