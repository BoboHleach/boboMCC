package me.bobomcc;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;

public class playerClass {

    protected main plugin;
    protected Player player;
    public String name, teamName = "default";
    protected float abilityCooldown, holdingDamageAmount = 0;
    protected boolean hasUltimate = true;
    boolean isCompress,isHoldingDamage = false;
    Location previousCompressionLocation;
    protected scoreboardManager playerScoreBoardClass;

    protected playerClass() {
        playerScoreBoardClass = new scoreboardManager(player);
        new BukkitRunnable() {
            public void run() {
                teamName = plugin.event.playerTeamHashMap.get();
                playerScoreBoardClass.update((int) abilityCooldown, hasUltimate, name, teamName);
                abilityCooldown = abilityCooldown - 1;
            }
        }.runTaskTimer(plugin, 20, 20);
    }
    
    protected void setPlugin(main plugin) {
    	this.plugin = plugin;
    }
    
    protected void setPlayer(Player player) {
    	this.player = player;
    }
    
    
    private void teleportCompressedPlayer(playerClass attackedPlayerClass, Player attackedPlayer, int timerDelay) {
        attackedPlayer.sendMessage("You have been Compressed, Releasing in 15 seconds");
        this.previousCompressionLocation = attackedPlayer.getLocation();
        attackedPlayerClass.isCompress = true;
        attackedPlayer.teleport(new Location(player.getWorld(),10000000, 1000000, 100000));
        attackedPlayer.setGameMode(GameMode.SPECTATOR);
        new BukkitRunnable() {
            @Override
            public void run() {
                attackedPlayerClass.isCompress = false;
                attackedPlayer.teleport(previousCompressionLocation);
                attackedPlayer.setGameMode(GameMode.SURVIVAL);
            }
        }.runTaskLater(this.plugin, timerDelay);
    }
    
    private void mineBlock(Location location) {
    	location.getBlock().breakNaturally();
    }
    private void mineBlocksAroundPlayer(int min, int max) {
        for (int x = min; x < max; x++) {
            for (int y = min; y < max; y++) {
                for (int z = min; z < max; z++) {
                    mineBlock(new Location(this.player.getWorld(),x,y,z));
                }
            }
        }
    }

    private int generateRanomdInt(int maximumRange, int minimumRange) {
    	Random rand = new Random();
    	return rand.nextInt(maximumRange-minimumRange) - minimumRange;
    }
    
    private void teleportPlayer(Location location) {
    	this.player.teleport(location);
    }
    private void playerTeleport(int min, int max) {
        int xCoordinate = generateRanomdInt(max, min);
        int zCoordinate = generateRanomdInt(max, min);
        Location newLocation = new Location(this.player.getWorld(),xCoordinate,this.player.getWorld().getHighestBlockYAt(xCoordinate, zCoordinate),zCoordinate);
        teleportPlayer(newLocation);
    }

    protected void copyNormal(Player attackedPlayer, HashMap<Player, playerClass>  playerToPlayerClass) {
            name = playerToPlayerClass.get(attackedPlayer).name;
            new BukkitRunnable() {
                @Override
                public void run() {
                    name = "copy";
                }
            }.runTaskLater(plugin, 600);
    }
}

