package fall2018.csc2017.GameCentre;

import android.graphics.Bitmap;
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
     * The BitMap of background image, if there is not one, remain null
     */
    public static Bitmap BACKGROUND_BMAP = null;

    /**
     * The tiles on the board in row-major order.
     */
    protected Tile[][] tiles = new Tile[Board.NUM_ROWS][Board.NUM_COLS];

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
}


