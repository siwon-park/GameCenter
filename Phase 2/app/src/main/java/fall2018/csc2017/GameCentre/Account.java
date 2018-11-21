package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for accounts and their contents.
 */
public class Account implements Serializable {
    /**
     * The saved game file name.
     */
    private String savedGameFileName;

    /**
     * The current game file name.
     */
    private String currentGameFileName;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The username.
     */
    private String username;

    /**
     * The password.
     */
    private String password;

    /**
     * The List of scores.
     */
    private List<Integer> scores;

    /**
     * The boolean for whether the game has been saved.
     */
    private boolean saved;

    /**
     * The boolean for whether the game has ever been played.
     */
    private boolean gamePlayed;

    /**
     * Constructor for account.
     * @param name
     * @param username
     * @param password
     */
    Account(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
        this.saved = false;
        savedGameFileName = username + "_saved_game" + ".ser";
        currentGameFileName = username + "_current_game" + ".ser";
        this.scores = new ArrayList<>();
    }

    /**
     * The boolean checking if input matches password on file.
     * @param password
     * @return whether the password matches what's on file
     */
    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Setter method for setting the score when the game is over
     * @param score
     */
    public void setScore(int score) {
        scores.add(score);
    }

    /**
     * Getter method
     * @return ArrayList of Integer score values
     */
    public List<Integer> getScores() {
        return scores;
    }

    /**
     * Getter method
     * @return the file name of the saved game
     */
    public String getSavedGameFileName() {
        return savedGameFileName;
    }

    /**
     * Getter method
     * @return the file name of the current game
     */
    public String getCurrentGameFileName() {
        return currentGameFileName;
    }

    /**
     * Getter method.
     * @return the username of the user account
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter method.
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method.
     * @return the value of boolean saved
     */
    public boolean getSaved() {return saved;}

    /**
     * Setter method for boolean saved.
     */

    public void setSaved(boolean saved) { this.saved = saved; }

    /**
     * Getter method
     * @return the value of boolean game played
     */
    public boolean getGamePlayed() {return gamePlayed;}

    /**
     * Setter method for boolean game played.
     */
    public void setGamePlayed(boolean gamePlayed) { this.gamePlayed = gamePlayed; }


}
