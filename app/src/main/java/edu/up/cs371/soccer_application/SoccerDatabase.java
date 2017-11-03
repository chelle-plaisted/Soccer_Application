package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    //instance variables
    private Hashtable<String,SoccerPlayer> players = new Hashtable<>();
    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
        // is valid info
        if(firstName == null || lastName == null) {
            return false;
        }
        String key = new String(firstName + "." + lastName);
        // is already in database

        if(players.containsKey(key)) {
            return false;
        }
         SoccerPlayer s = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);

        players.put(key, s);
        return true;
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName)
    {
        String key = new String(firstName + "." + lastName);

        if(players.containsKey(key)) {
            players.remove(key);
            return true;
        }
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
        public SoccerPlayer getPlayer(String firstName, String lastName) {
        String key = new String(firstName + "." + lastName);
        SoccerPlayer thing = players.get(key);
        return thing;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String key = new String(firstName + "." + lastName);
        SoccerPlayer thing = players.get(key);
        if(thing == null) return false;
        thing.bumpGoals();
        return true;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String key = new String(firstName + "." + lastName);
        SoccerPlayer thing = players.get(key);
        if(thing == null) return false;
        thing.bumpAssists();
        return true;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String key = new String(firstName + "." + lastName);
        SoccerPlayer thing = players.get(key);
        if(thing == null) return false;
        thing.bumpShots();
        return true;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String key = new String(firstName + "." + lastName);
        SoccerPlayer thing = players.get(key);
        if(thing == null) return false;
        thing.bumpSaves();
        return true;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String key = new String(firstName + "." + lastName);
        SoccerPlayer thing = players.get(key);
        if(thing == null) return false;
        thing.bumpFouls();
        return true;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String key = new String(firstName + "." + lastName);
        SoccerPlayer thing = players.get(key);
        if(thing == null) return false;
        thing.bumpYellowCards();
        return true;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String key = new String(firstName + "." + lastName);
        SoccerPlayer thing = players.get(key);
        if(thing == null) return false;
        thing.bumpRedCards();
        return true;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        if(teamName == null) {
            return players.size();
        }
        int count = 0;
        Set<String> names = players.keySet();
        for(String s : names) {
            if(players.get(s).getTeamName().equals(teamName)) {
                count++;
            }
        }
        return count;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        if(file == null) return false;

        try {
            PrintWriter printer = new PrintWriter(file);
            Set<String> names = players.keySet();
            for(String s : names) {
                SoccerPlayer temp = players.get(s);

                printer.println(logString("Name: " + temp.getFirstName() + " " + temp.getLastName()));
                printer.println(logString("Team: " + temp.getTeamName()));
                printer.println(logString("Goals: " + temp.getGoals()));
                printer.println(logString("Assists: " + temp.getAssists()));
                printer.println(logString("Shots: " + temp.getShots()));
                printer.println(logString("Saves: " + temp.getSaves()));
                printer.println(logString("Fouls: " + temp.getFouls()));
                printer.println(logString("Yellow Cards: " + temp.getYellowCards()));
                printer.println(logString("Red Cards: " + temp.getRedCards()));
                printer.println();
            }
            return true;
        }
        catch (FileNotFoundException f) {
            return false;
        }
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
