package me.bobomcc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class scoreboardManager {
    Scoreboard playerScoreboard;
    Objective playerObjective;
    Player player;
    public scoreboardManager(Player p){
        this.player = p;
        resetScoreboard();
    }
    protected void update(int timeLeft, boolean isUltimateAvailable, String abilityName, String teamName){
        resetScoreboard();
        if(timeLeft < 0) timeLeft = 0;
        playerObjective.getScore(ChatColor.GREEN + "Ability: " + ChatColor.RESET + abilityName).setScore(40);
        playerObjective.getScore(ChatColor.GREEN + "Ultimate: " + ChatColor.RESET + isUltimateAvailable).setScore(30);
        playerObjective.getScore(ChatColor.GREEN + "Ability Cooldown: "+ ChatColor.RESET + timeLeft).setScore(20);
        playerObjective.getScore(ChatColor.GREEN + "Team: " + ChatColor.RESET + teamName).setScore(10);
        player.setScoreboard(playerScoreboard);
    }

    private void resetScoreboard(){
        playerScoreboard = player.getServer().getScoreboardManager().getNewScoreboard();
        playerObjective = playerScoreboard.registerNewObjective("test", "dummy");
        playerObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        playerObjective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Stats");
    }
}
