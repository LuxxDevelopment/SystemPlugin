package de.lukas.systemplugin.commands;

import de.lukas.systemplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RegelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (Objects.equals(Main.getLang(), "de")) {
            sender.sendMessage("----------§7[§5RabbitCraft§7] REGELN §7[§5RabbitCraft§7]----------");
            sender.sendMessage("           §7Die Regeln findest du in unserem Discord"            );
            sender.sendMessage("----------§7[§5RabbitCraft§7] REGELN §7[§5RabbitCraft§7]----------");
        } else {
            sender.sendMessage("----------§7[§5RabbitCraft§7] REGELN §7[§5RabbitCraft§7]----------");
            sender.sendMessage("           §7Die Regeln findest du in unserem Discord"            );
            sender.sendMessage("----------§7[§5RabbitCraft§7] REGELN §7[§5RabbitCraft§7]----------");
        }
        return false;
    }
}
