package me.bobomcc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

public class playerJoinEvent implements Listener {
    HashMap<Player, playerClass> playerToAbilityHashMap;
    main plugin;
    public playerJoinEvent(eventHandler e, main plugin){
        this.plugin = plugin;
        this.playerToAbilityHashMap = e.playerToAbilityHashMap;
    }
    @EventHandler
    protected void PlayerJoinEvent(PlayerJoinEvent e){
        playerToAbilityHashMap.putIfAbsent(e.getPlayer(), new playerClass(e.getPlayer(), plugin));
        System.out.println(playerToAbilityHashMap);
    }
}
