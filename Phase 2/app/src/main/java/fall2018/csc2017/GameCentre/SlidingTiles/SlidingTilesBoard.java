package fall2018.csc2017.GameCentre.SlidingTiles;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.Tile;

/**
 * The sliding tiles board.
 */
public class SlidingTilesBoard extends fall2018.csc2017.GameCentre.Board implements Serializable {
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
     * A stack that keeps track of completed moves.
     */
    private Stack<int[][]> undoTrack = new Stack<>();

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    public SlidingTilesBoard(List<Tile> tiles) {
//        setGameName(BoardManager.SLIDING_TILES_GAME);
//        setGameId(0);

        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != SlidingTilesBoard.NUM_ROWS; row++) {
            for (int col = 0; col != SlidingTilesBoard.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
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
}
