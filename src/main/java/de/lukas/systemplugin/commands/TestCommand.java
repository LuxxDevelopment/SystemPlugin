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
        DiscordWebhook.sendWebhook("https://discord.com/api/webhooks/1177908718524973116/-IQ9x1j3C6g3KXpUNSeEnLytm9PW6zU5qxeY7Z5z-eJas7Ecy5Y7mMiJrTw2nwh8tD2D", "Test");
        return false;
    }


}
