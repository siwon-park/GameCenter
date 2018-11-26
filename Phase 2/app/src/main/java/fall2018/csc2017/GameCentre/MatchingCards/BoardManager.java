package fall2018.csc2017.GameCentre.MatchingCards;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

//import fall2018.csc2017.GameCentre.SlidingTiles.Board;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.Tile;

import static fall2018.csc2017.GameCentre.MatchingCards.Board.NUM_COLS;
import static fall2018.csc2017.GameCentre.MatchingCards.Board.NUM_ROWS;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager extends fall2018.csc2017.GameCentre.BoardManager {

    private Board board;

    private int pairID = 0;

    private Stack<int[]> clearedCards = new Stack<>();

    private Stack<int[]> lastClicks = new Stack<>();

    private int[] cardIDs = {
            R.drawable.tile_1, R.drawable.tile_2,
            R.drawable.tile_3, R.drawable.tile_4,
            R.drawable.tile_5, R.drawable.tile_6,
            R.drawable.tile_7, R.drawable.tile_8
    };

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    public BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Manage a new shuffled board.
     */
    public BoardManager() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = NUM_ROWS * NUM_COLS;
        int[] temp = new int[numTiles];
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            if (tileNum % 2 == 0){
                temp[tileNum] = cardIDs[tileNum / 2];
            } else {
                temp[tileNum] = cardIDs[(tileNum - 1) / 2];
            }
            tiles.add(new Tile(tileNum + 1, temp[tileNum]));
        }
        createBoard(tiles);
    }

    BoardManager(List<Tile> tiles) {
        createBoard(tiles);
    }

    public Board getBoard() {
        return board;
    }

    @Override
    protected void createBoard(List<Tile> tiles) {
        // board = null;
        while (board == null) {
            Collections.shuffle(tiles);
            board = new Board(tiles);
        }
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    @Override
    public boolean puzzleSolved() {
        boolean solved = true;
        if (clearedCards.size() != board.numTiles()) {
            solved = false;
        }
        if (solved) {
            updateScore();
        }
        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    @Override
    protected boolean isValidTap(int position) {

        int row = position / NUM_COLS;
        int col = position % NUM_COLS;
        if (!clearedCards.empty()) {
            int[] clickedOn = {row, col};
            return (clearedCards.search(clickedOn) == -1);
        }
        return true;
    }

    public int getPairID() {
        return pairID;
    }

    public Stack<int[]> getLastClicks() {
        return lastClicks;
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    @Override
    protected void touchMove(int position) {
        //1D representation of a 2D Cell
        int row = position / NUM_COLS;
        int col = position % NUM_COLS;
        int currentID = board.getTile(row, col).getId();
        int[] clickedOn = {row, col};
        lastClicks.push(clickedOn);
        board.flipCards();

        if (pairID == 0) {
            clearedCards.push(clickedOn);
            if (currentID % 2 == 0) {
                pairID = currentID - 1;
            } else {
                pairID = currentID + 1;
            }
        } else {
            if (pairID == currentID) {
                int[] toBeCleared = clearedCards.peek();
                board.clearTiles(toBeCleared[0], toBeCleared[1]);
                clearedCards.push(clickedOn);
                board.clearTiles(row, col);
                pairID = 0;
            } else {
                clearedCards.pop();
                pairID = 0;
            }
            lastClicks.pop();
            lastClicks.pop();
        }
    }
}
