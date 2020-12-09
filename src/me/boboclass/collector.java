package me.boboclass;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class collector extends playerClass {
    protected collector(Player playerObject){
        this.playerObject = playerObject;
        this.abilityClass = this;
    }
    protected void normal(){
        if(delay <= 0){
            return;
        }
        getOreToPlayer(-5,5);
        this.delay = 30;
    }
    protected void ultimate(){
        if(!hasUltimate) {
            playerObject.sendMessage("Ultimate has been used");
            return;
        }
        getOreToPlayer(-25,25);
        this.hasUltimate = false;
    }

    private void getOreToPlayer(int min, int max){
        double[] coordinates = new double[3];
        coordinates[0] = playerObject.getLocation().getX();
        coordinates[1] = playerObject.getLocation().getY();
        coordinates[2] = playerObject.getLocation().getZ();
        World playerWorld = playerObject.getWorld();
        for(int x = min; x < max;x++){
            for(int y = min; x<max;x++){
                for(int z = min;z<max;z++){
                    Material blockType = playerWorld.getBlockAt((int) (coordinates[0] + x),(int) (coordinates[1]  +y),(int) (coordinates[2] + z)).getType();
                    playerWorld.getBlockAt((int) (coordinates[0] + x),(int) (coordinates[1]  +y),(int) (coordinates[2] + z)).setType(Material.AIR);
                    if(blockType == Material.IRON_ORE){
                        playerObject.getInventory().addItem( new ItemStack(Material.IRON_INGOT, 1));
                    }
                    else if(blockType == Material.DIAMOND_ORE){
                        playerObject.getInventory().addItem( new ItemStack(Material.DIAMOND, 1));
                    }

                    else if(blockType == Material.COAL_ORE){
                        playerObject.getInventory().addItem( new ItemStack(Material.COAL, 1));
                    }

                    else if(blockType == Material.GOLD_ORE){
                        playerObject.getInventory().addItem( new ItemStack(Material.GOLD_INGOT, 1));
                    }
                }
            }
        }
    }
}
