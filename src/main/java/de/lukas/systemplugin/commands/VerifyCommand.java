package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import de.lukas.systemplugin.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class VerifyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!utils.senderInstanceCheck(sender, "Du must ein §aspieler§7 sein!")){return false;}
        if(!utils.permissionCheck("system.player.run.verify", sender)){return false;}
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://password-code-generator.p.rapidapi.com/pass/32/no"))
                .header("X-RapidAPI-Key", "20b03ecf64msh11d60e1c1b07a30p13ec6djsnde43ec1f38a2")
                .header("X-RapidAPI-Host", "password-code-generator.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        sender.sendMessage(Main.getPrefix() + "Dein code ist " + response.body().toString() + "er ist 5min gültig");

        return false;
    }
}
