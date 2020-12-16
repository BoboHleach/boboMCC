package me.bobomcc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

public class playerJoinEvent implements Listener {
    HashMap<Player, playerClass> playerToAbilityHashMap;
    HashMap<Player, String> playerTeamHashMap;
    main plugin;
    public playerJoinEvent(eventHandler e, main plugin){
        this.plugin = plugin;
        this.playerToAbilityHashMap = e.playerToAbilityHashMap;
        this.playerTeamHashMap = e.playerTeamHashMap;
    }
    @EventHandler
    protected void PlayerJoinEvent(PlayerJoinEvent e){
        playerToAbilityHashMap.putIfAbsent(e.getPlayer(), new playerClass(e.getPlayer(), plugin));
        playerTeamHashMap.putIfAbsent(e.getPlayer(), e.getPlayer().getUniqueId().toString());
        System.out.println(playerToAbilityHashMap);
    }
}
