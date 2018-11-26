package fall2018.csc2017.GameCentre.MatchingCards;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.Tile;

/**
 * The sliding tiles board.
 */
public class Board extends fall2018.csc2017.GameCentre.Board implements Serializable {
    // TODO: Change NUM_ROWS and NUM_COLS to non-static and add getter/setter methods becuz they belong to each board.

//    /**
//     * The BitMap of background image, if there is not one, remain null
//     */
//    public static Bitmap BACKGROUND_BMAP = null;

    private int pairID = 0;

    private Vector<int[]> clearedCards = new Vector<>();

    private Stack<int[]> lastClicks = new Stack<>();

    private boolean flipInProgress = false;

    private Stack<int[]> toBeFlipped = new Stack<>();

    private int gameID = 1;

    public int getPairID() {
        return pairID;
    }

    public Stack<int[]> getLastClicks() {
        return lastClicks;
    }

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    public Board(List<Tile> tiles) {
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    void clearTiles(int row, int col) {
//        Tile temp = tiles[row1][col1];
//        tiles[row1][col1] = tiles[row2][col2];
//        tiles[row2][col2] = temp;
        tiles[row][col].setBackground(R.drawable.tile_blank);

        setChanged();
        notifyObservers();
    }

    void flipCards() {
        long startTime = System.currentTimeMillis();

//        while(System.currentTimeMillis() <= startTime + 1500) {
            flipInProgress = true;
            setChanged();
            notifyObservers();
//        }
//
//        flipInProgress = false;
//        setChanged();
//        notifyObservers();
    }

    public Stack<int[]> getToBeFlipped() {
        return toBeFlipped;
    }

    public boolean getFlipInProgress() {
        return flipInProgress;
    }

    @Override
    protected boolean isSolvable() {
        return true;
    }

    @Override
    public boolean puzzleSolved() {
        boolean solved = true;
        if (clearedCards.size() != this.numTiles()) {
            solved = false;
        }
        if (solved) {
            updateScore();
        }
        return solved;
    }

    @Override
    protected boolean isValidTap(int position) {
        int row = position / NUM_COLS;
        int col = position % NUM_COLS;
        if (!clearedCards.isEmpty()) {
            for (int i = 0; i != clearedCards.size(); i++) {
                if (clearedCards.elementAt(i)[0] == row &&
                        clearedCards.elementAt(i)[1] == col) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void touchMove(int position) {
        //1D representation of a 2D Cell
        int row = position / NUM_COLS;
        int col = position % NUM_COLS;
        int currentID = this.getTile(row, col).getId();
        int[] clickedOn = {row, col};
        lastClicks.push(clickedOn);
        this.flipCards();

        if (pairID == 0) {
            clearedCards.add(clickedOn);
            if (currentID % 2 == 0) {
                pairID = currentID - 1;
            } else {
                pairID = currentID + 1;
            }
        } else {
            if (pairID == currentID) {
                int[] toBeCleared = clearedCards.lastElement();
                this.clearTiles(toBeCleared[0], toBeCleared[1]);
                clearedCards.add(clickedOn);
                this.clearTiles(row, col);
                pairID = 0;
            } else {
                clearedCards.removeElementAt(clearedCards.size() - 1);
                pairID = 0;
            }
            lastClicks.pop();
            lastClicks.pop();
        }
    }

    @Override
    public void undoMove() {

    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public void updateScore() {

    }

    @Override
    public void setStartingScoreAndTime() {

    }

    @Override
    public void updateStartTime() {

    }

    @Override
    public long getStartTime() {
        return 0;
    }

    @Override
    public void setStartTime(long startTime) {

    }
//    @Override
//    public String toString() {
//        return "Board{" +
//                "tiles=" + Arrays.toString(tiles) +
//                '}';
//    }
}
