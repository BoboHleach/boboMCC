package me.bobomcc;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class scoreboardManager {
    Scoreboard playerScoreboard;
    Objective playerObjective;
    Score timerScore, ultimateScore;
    Player player;
    public scoreboardManager(Player p){
        this.player = p;
        reset();
    }
    protected void update(int timeLeft, boolean isUltimateAvailable){
        reset();
        timerScore = playerObjective.getScore("Timer: "+ timeLeft);
        timerScore.setScore(0);
        ultimateScore = playerObjective.getScore("Ultimate: " + isUltimateAvailable);
        ultimateScore.setScore(1);
        playerObjective.setDisplayName("Cooldown");
        player.setScoreboard(playerScoreboard);
    }

    private void reset(){
        playerScoreboard = player.getServer().getScoreboardManager().getNewScoreboard();
        playerObjective = playerScoreboard.registerNewObjective("test", "dummy");
        playerObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        playerObjective.setDisplayName("Timer");
    }
}
