package de.lukas.systemplugin.events;

import de.lukas.systemplugin.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        String msg = e.getMessage();
        List<String> swear_words = Main.swear_words;
        for(int i = 0; i <swear_words.size(); i++){
            if(msg.toLowerCase().contains(swear_words.get(i))){
                e.setCancelled(true);
                e.getPlayer().sendMessage(Main.getPrefix() + "Woahh das ist ein wort das wir hier §cnich§7 haben wollen!");
                // Maybe add a warn system =:
            }
        }
    }
}
