package me.bobomcc;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;

public class playerClass {
    protected main plugin;
    protected Player player;
    protected String name = "default";
    protected float delay = 0;
    protected boolean hasUltimate = false;
    protected boolean isCompress = false;

    private void compressTeleportEvent(playerClass attackedPlayerClass, Player attackedPlayer, Plugin p, int timerDelay){
        attackedPlayer.sendMessage("You have been Compressed, Releasing in 15 seconds");
        attackedPlayerClass.isCompress = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                attackedPlayerClass.isCompress = false;
            }
        }.runTaskLater(p,timerDelay);
    }
    private void getOreToPlayer(int min, int max){
        World playerWorld = player.getWorld();
        for(int x = min; x < max;x++){
            for(int y = min; x<max;x++){
                for(int z = min;z<max;z++){
                    Material blockType = playerWorld.getBlockAt((int) (player.getLocation().getX() + x),(int) (player.getLocation().getY()  +y),(int) (player.getLocation().getZ() + z)).getType();
                    playerWorld.getBlockAt((int) (player.getLocation().getX() + x),(int) (player.getLocation().getY()  +y),(int) (player.getLocation().getZ() + z)).setType(Material.AIR);
                    if(blockType == Material.IRON_ORE){
                        player.getInventory().addItem( new ItemStack(Material.IRON_INGOT, 1));
                    }
                    else if(blockType == Material.DIAMOND_ORE){
                        player.getInventory().addItem( new ItemStack(Material.DIAMOND, 1));
                    }

                    else if(blockType == Material.COAL_ORE){
                        player.getInventory().addItem( new ItemStack(Material.COAL, 1));
                    }

                    else if(blockType == Material.GOLD_ORE){
                        player.getInventory().addItem( new ItemStack(Material.GOLD_INGOT, 1));
                    }
                }
            }
        }
    }
    private void playerTeleport(Player p, int min, int max){
        Random rand = new Random();
        int xCoordinate = rand.nextInt(max + min) - min;
        int zCoordinate = rand.nextInt(max +min) - min;
        player.teleport(new Location(player.getWorld(), player.getLocation().getX() + xCoordinate, player.getWorld().getHighestBlockYAt((int)player.getLocation().getX() + xCoordinate, ( int) player.getLocation().getZ() + zCoordinate) + 1, player.getLocation().getZ()));
    }
    protected void run(Action e, HashMap<Player, playerClass> playerToPlayerClass, Player attackedPlayer){

        if(delay <= 0){
            if(name.equalsIgnoreCase("Collector")){
                if(hasUltimate){
                    if(e == Action.RIGHT_CLICK_AIR || e == Action.RIGHT_CLICK_BLOCK){
                        getOreToPlayer(-25,25);
                        this.hasUltimate = false;
                        this.delay = 30;
                    }

                }
                if(e == Action.LEFT_CLICK_AIR || e == Action.LEFT_CLICK_BLOCK){
                    if(delay > 0) return;
                    getOreToPlayer(-5,5);
                    this.delay = 30;
                }
            }
            else if(name.equalsIgnoreCase("Compress")){
                if(hasUltimate){
                    if(e == Action.RIGHT_CLICK_AIR || e == Action.RIGHT_CLICK_BLOCK){
                        compressTeleportEvent(playerToPlayerClass.get(player),attackedPlayer,plugin,7200);
                        hasUltimate= false;
                        this.delay = 30;
                    }
                }
                if(e == Action.LEFT_CLICK_AIR || e == Action.LEFT_CLICK_BLOCK){
                    compressTeleportEvent(playerToPlayerClass.get(player),attackedPlayer,plugin,600);
                    this.delay = 30;
                }
            }
            else if(name.equalsIgnoreCase("Copy")){
                if(hasUltimate){
                    if(e == Action.RIGHT_CLICK_AIR || e == Action.RIGHT_CLICK_BLOCK){
                        this.name = playerToPlayerClass.get(attackedPlayer).name;
                        hasUltimate= false;
                        this.delay = 0;
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                name = "Copy";
                            }
                        }.runTaskLater(plugin,2400);
                    }
                }
                if(e == Action.LEFT_CLICK_AIR || e == Action.LEFT_CLICK_BLOCK){
                    this.name = playerToPlayerClass.get(attackedPlayer).name;
                    this.delay = 30;
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            name = "Copy";
                        }
                    }.runTaskLater(plugin,600);
                }
            }
            else if(name.equalsIgnoreCase("Warp")){
                if(hasUltimate){
                    if(e == Action.RIGHT_CLICK_AIR || e == Action.RIGHT_CLICK_BLOCK){
                        attackedPlayer.teleport(attackedPlayer.getLocation().add(0,25,0));
                        hasUltimate = false;
                    }
                }
                if(e == Action.LEFT_CLICK_AIR || e == Action.LEFT_CLICK_BLOCK){
                    playerTeleport(player,-10,10);
                    this.delay = 30;
                }
            }
        }
    }
}
