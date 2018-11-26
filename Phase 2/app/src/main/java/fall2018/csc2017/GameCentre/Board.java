package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Observable;

abstract public class Board extends Observable implements Serializable, Iterable<Tile> {
    /**
     * The number of rows.
     */
    public static int NUM_ROWS = 4;
    /**
     * The number of columns.
     */
    public static int NUM_COLS = 4;
    /**
     * The tiles on the board in row-major order.
     */
    protected Tile[][] tiles = new Tile[Board.NUM_ROWS][Board.NUM_COLS];

    private String name;

    /**
     * The Id of the Game
     * 0 - Sliding Tiles
     * 1 - Matching Cards
     * 2 - TBA
     */
    private int gameID;

    protected void setName(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    /**
     * Return the number of tiles on the board.
     * @return the number of tiles on the board
     */
    public int numTiles() {
        return NUM_ROWS * NUM_COLS;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * This returns the tile iterator.
     * @return tile iterator
     */
    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new BoardIterator();
    }

    /**
     * Tile iterator for the board
     * @author Jerry Qian
     */
    private class BoardIterator implements Iterator<Tile> {
        int nextRow = 0;
        int nextCol = 0;
        @Override
        public boolean hasNext() {
            return nextRow < Board.NUM_ROWS;
        }

        @Override
        public Tile next() {
            Tile result = tiles[nextRow][nextCol];
            nextCol++;
            if (nextCol == Board.NUM_COLS) {
                nextCol = 0;
                nextRow++;
            }
            return result;
        }
    }

    protected abstract boolean isSolvable();

    public abstract boolean puzzleSolved();

    protected abstract boolean isValidTap(int position);

    protected abstract void touchMove(int position);

    public abstract void undoMove();


    public abstract int getScore();

    /**
     * Updates score
     */
    public abstract void updateScore();

    /**
     * Sets starting score and time
     */
    public abstract void setStartingScoreAndTime();

    /**
     * Updates start time
     */
    public abstract void updateStartTime();

    /**
     * Getter Method (for testing purposes)
     * @return the start time
     */
    public abstract long getStartTime();

    /**
     * Setter Method (for testing purposes)
     */
    public abstract void setStartTime(long startTime);

    /**
     * Getter Method for the Game ID
     * @return the ID of the game
     */
    public int getGameID() { return gameID; }

    /**
     * getter method for the score
     * @return score
     */
}


