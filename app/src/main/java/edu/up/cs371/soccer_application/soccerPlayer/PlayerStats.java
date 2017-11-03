package edu.up.cs371.soccer_application.soccerPlayer;

/**
 * Created by rae-o on 11/2/2017.
 */

public class PlayerStats {

    // private instance variables
    private int playerNum;
    private String name;
    public PlayerStats(String s, int val) {
        name = s;
        val = playerNum;
    }

    public int getplayerNum() {
        return playerNum;
    }

    public String getTeamName() {
        return name;
    }
}
