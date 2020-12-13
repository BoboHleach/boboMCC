package me.boboclass;

import org.bukkit.entity.Player;

public class playerClass {
    public String name = "playerClass";
    Player playerObject;
    int delay = 0;
    boolean hasUltimate= true;
    boolean isCompressed = false;

    public void normal(){
        playerObject.sendMessage("Normal Used");
        this.delay = 0;
    }

    public void ultimate(){
        playerObject.sendMessage("Ultimate Used");
        this.hasUltimate = false;
    }

    public String returnName (){
        return this.name;
    }
}
