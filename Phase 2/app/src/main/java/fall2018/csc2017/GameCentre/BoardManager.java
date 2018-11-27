package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.GameCentre.MatchingCards.MatchingCardsBoard;
import fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesBoard;

import static fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesBoard.NUM_COLS;
import static fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesBoard.NUM_ROWS;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
abstract public class BoardManager implements Serializable {
    public static final String SLIDING_TILES_GAME = "Sliding Tiles";
    public static final String MATCHING_CARDS_GAME = "Matching Cards";
    public static final String WHACK_A_MOLE_GAME = "Whack A Mole";

    /**
     * The board being managed.
     */
    protected Board board;
    /**
     * Here is the score formula:
     * Score = 100 + NUM_ROWS * NUM_COLS * 2 - moves - time_in_seconds / 60
     * We give more points for a larger board. We also deduct 1 point for each move, but we revert
     * the score for each move that is undone. For every 1 minute, we deduct 1 point.
     */
    protected int score = 0;
    /**
     * Records start time of game or the resume time of game (in milliseconds)
     */
    private long startTime = 0;
    /**
     * The saved number of rows
     */
    private int savedNumRows = NUM_ROWS;
    /**
     * The saved number of columns
     */
    private int savedNumCols = NUM_COLS;
    /**
     * The id of the game that is in play
     */
    private int gameID;

    /**
     * The name of the Game
     */
    private String gameName;

    /**
     * Return the current board.
     */
    public abstract Board getBoard();

    protected abstract void createBoard(List<Tile> tiles);

    public abstract boolean puzzleSolved();

    protected abstract boolean isValidTap(int position);

    protected abstract void touchMove(int position);

    /**
     * getter method for the GameID
     * @return gameID
     */
    public int getGameID() { return gameID; }

    public void setGameID(int gameID) {this.gameID = gameID;}


    protected void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Getter Method for the Game Name
     * @return the ID of the game
     */
    protected String getGameName() { return gameName; }

    /**
     * getter method for the score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Updates score
     */
    public void updateScore() {
        long endTime = System.currentTimeMillis();
        score -= (endTime - startTime) / 1000 / 60;
    }

    /**
     * Sets starting score and time
     */
    public void setStartingScoreAndTime() {
        score = 100 + NUM_ROWS * NUM_COLS * 2;
        updateStartTime();
    }

    /**
     * Updates start time
     */
    public void updateStartTime() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Getter Method (for testing purposes)
     * @return the start time
     */
    long getStartTime() {
        return startTime;
    }

    /**
     * Setter Method (for testing purposes)
     */
    void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter Method
     * @return saved number of columns
     */
    public int getSavedNumCols() {
        return savedNumCols;
    }

    /**
     * Getter Method
     * @return saved number of rows
     */
    public int getSavedNumRows() {
        return savedNumRows;
    }

    /**
     * Setter Method
     * @param numCols
     * @return the set number of columns
     */
    public void setSavedNumCols(int numCols) {
        this.savedNumCols = numCols;
        NUM_COLS = numCols;
    }

    /**
     * Setter Method
     * @param numRows
     * @return the set number of rows
     */
    public void setSavedNumRows(int numRows) {
        this.savedNumRows = numRows;
        NUM_ROWS = numRows;
    }
}
