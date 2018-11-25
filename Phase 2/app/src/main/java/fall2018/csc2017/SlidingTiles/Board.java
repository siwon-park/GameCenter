package fall2018.csc2017.SlidingTiles;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import fall2018.csc2017.GameCentre.Tile;

/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {
    // TODO: Change NUM_ROWS and NUM_COLS to non-static and add getter/setter methods becuz they belong to each board.
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
    private Tile[][] tiles = new Tile[NUM_ROWS][NUM_COLS];

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles) {
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     * @return the number of tiles on the board
     */
    int numTiles() {
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
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile temp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = temp;

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
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
            return nextRow < NUM_ROWS;
        }

        @Override
        public Tile next() {
            Tile result = tiles[nextRow][nextCol];
            nextCol++;
            if (nextCol == NUM_COLS) {
                nextCol = 0;
                nextRow++;
            }
            return result;
        }
    }
}
