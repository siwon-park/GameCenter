package fall2018.csc2017.GameCentre;

import java.io.Serializable;

/**
 * A class that contains the score and username to be displayed in scoreboard
 * Implements Comparable so that it can be sorted
 */
public class ScoreInfo implements Serializable, Comparable<ScoreInfo> {
    /**
     * The score obtained
     */
    private int score;
    /**
     * The username of who obtained the score
     */
    String name;
    /**
     * The game the score belongs to
     */
    private String gameID;

    /**
     * Initializes score info.
     * @param score the score of user
     * @param name name of user
     * @param gameID ID of game which score belongs to
     */
    public ScoreInfo(int score, String name, String gameID) {
        this.score = score;
        this.name = name;
        this.gameID = gameID;
    }

    /**
     * gets the score of a given ScoreInfo
     * @return score of ScoreInfo
     */
    int getScore() {
        return score;
    }

    /**
     * Gets the name of a given ScoreInfo
     * @return name of ScoreInfo
     */
    String getName() {
        return name;
    }

    /**
     * Gets the GameID of ScoreInfo
     * @return gameID of ScoreInfo
     */
    String getGameID(){return gameID;}

    /**
     * Compares ScoreInfo by sorting them in decreasing order
     * @param compareScoreInfo score info to compare to
     * @return If score is better
     */
    public int compareTo(ScoreInfo compareScoreInfo) {
        return compareScoreInfo.getScore() - this.getScore();
    }
}