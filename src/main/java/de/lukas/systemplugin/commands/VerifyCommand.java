package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import de.lukas.systemplugin.utils.utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class VerifyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!utils.senderInstanceCheck(sender, "Du must ein §aspieler§7 sein!")){return false;}
        if(!utils.permissionCheck("system.player.run.verify", sender)){return false;}
        String code = utils.generateRandomPassword(16);
        utils.sendCopiableText(sender, Main.getPrefix() + "Dein Code ist §a" + code + "§7 er ist 10min gültig", code);
        code = "";
        return false;
    }
}
