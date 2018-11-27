package fall2018.csc2017.GameCentre.SlidingTiles;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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
     * The Id of the Game
     */
    private int gameID = 0;

    /**
     * A stack that keeps track of completed moves.
     */
    private Stack<int[][]> undoTrack = new Stack<>();

    // TODO: Change NUM_ROWS and NUM_COLS to non-static and add getter/setter methods becuz they belong to each board.
    /**
     * The BitMap of background image, if there is not one, remain null
     */
    public static Bitmap BACKGROUND_BMAP = null;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    public SlidingTilesBoard(List<Tile> tiles, int gameID) {
        setGameID(gameID);

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

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    @Override
    public boolean puzzleSolved() {
        boolean solved = true;
        Iterator<Tile> iter = this.iterator();
        int expectedId = 1;
        while (iter.hasNext()) {
            if (expectedId != iter.next().getId()) {
                solved = false;
                break;
            }
            expectedId++;
        }
//        if (solved) {
//            updateScore();
//        }
        return solved;
    }

    /**
     *
     * @return whether or not the board is solvable
     */
    @Override
    protected boolean isSolvable() {
        int invCount = getInvCount();
        if (NUM_ROWS % 2 == 1) {
            return (invCount % 2 == 0);
        } else {
            int blankTile = findReverseRowOfBlankTile();
            if (blankTile % 2 == 1) {
                return (invCount % 2 == 0);
            } else {
                return (invCount % 2 == 1);
            }
        }
    }

    private int findReverseRowOfBlankTile() {
        for (int i = NUM_ROWS - 1; i >= 0; i--) {
            for (int j = NUM_ROWS - 1; j >= 0; j--) {
                if (this.getTile(i,j).getId() == this.numTiles()) {
                    return NUM_ROWS - i;
                }
            }
        }
        return -1;
    }

    /**
     * Counts inversions to determine if board is solvable
     * @return number of inversions
     */
    private int getInvCount() {
        List<Tile> list = new ArrayList<>();
        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                Tile tile = this.getTile(i,j);
                if (!tile.isBlank()) {
                    list.add(tile);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getId() > list.get(j).getId()) {
                    count++;
                }
            }
        }
        return count;
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
        int blankId = this.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : this.getTile(row - 1, col);
        Tile below = row == NUM_ROWS - 1 ? null : this.getTile(row + 1, col);
        Tile left = col == 0 ? null : this.getTile(row, col - 1);
        Tile right = col == NUM_COLS - 1 ? null : this.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
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
        int blankId = this.numTiles(); // numRows * NUM_COLS per row + extra

        // tiles is the blank tile, swap by calling SlidingTilesBoard's swap method.\int[] dx = {0, 0, -1, 1};
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int row1 = dx[i] + row;
            int col1 = dy[i] + col;
            if (row1 >= 0 && col1 >= 0 && row1 < NUM_ROWS && col1 < NUM_COLS) {
                if (this.getTile(row1, col1).getId() == blankId) {
                    int[][] lastStep = {{row1, col1}, {row, col}};
                    undoTrack.push(lastStep);
                    this.swapTiles(row, col, row1, col1);

                    score--;

                }
            }
        }
    }

    /**
     * Undo a move up to unlimited times until the board is as original.
     */
    @Override
    public void undoMove(){
        if(!undoTrack.isEmpty()) {
            int[][] lastStep = undoTrack.pop();

            this.swapTiles(lastStep[0][0], lastStep[0][1], lastStep[1][0], lastStep[1][1]);
            score++;
        }
    }

    /**
     * getter method for the score
     * @return score
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Updates score
     */
    @Override
    public void updateScore() {
        long endTime = System.currentTimeMillis();
        score -= (endTime - startTime) / 1000 / 60;
    }

    /**
     * Sets starting score and time
     */
    @Override
    public void setStartingScoreAndTime() {
        score = 100 + NUM_ROWS * NUM_COLS * 2;
        updateStartTime();
    }

    /**
     * Updates start time
     */
    @Override
    public void updateStartTime() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Getter Method (for testing purposes)
     * @return the start time
     */
    @Override
    public long getStartTime() {
        return startTime;
    }

    /**
     * Setter Method (for testing purposes)
     */
    @Override
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

//    @Override
//    public String toString() {
//        return "SlidingTilesBoard{" +
//                "tiles=" + Arrays.toString(tiles) +
//                '}';
//    }

}
