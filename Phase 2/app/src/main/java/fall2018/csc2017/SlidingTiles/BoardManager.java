package fall2018.csc2017.SlidingTiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import fall2018.csc2017.GameCentre.Tile;

import static fall2018.csc2017.SlidingTiles.Board.NUM_COLS;
import static fall2018.csc2017.SlidingTiles.Board.NUM_ROWS;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;


    /**
     * A stack that keeps track of completed moves.
     */
    private Stack<int[][]> undoTrack = new Stack<>();

    /**
     * Here is the score formula:
     * Score = 100 + NUM_ROWS * NUM_COLS * 2 - moves - time_in_seconds / 60
     * We give more points for a larger board. We also deduct 1 point for each move, but we revert
     * the score for each move that is undone. For every 1 minute, we deduct 1 point.
     */
    int score = 0;

    /**
     * Records start time of game or the resume time of game (in milliseconds)
     */
    long startTime = 0;

    /**
     * The saved number of rows
     */
    private int savedNumRows = NUM_ROWS;

    /**
     * The saved number of columns
     */
    private int savedNumCols = NUM_COLS;

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    public BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Manage a new shuffled board.
     */
    public BoardManager() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = NUM_ROWS * NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        createBoard(tiles);
    }

    BoardManager(List<Tile> tiles) {
        createBoard(tiles);
    }

    private void createBoard(List<Tile> tiles) {
        // board = null;
        while (board == null || !isSolvable(board)) {
            Collections.shuffle(tiles);
            board = new Board(tiles);
        }
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    public boolean puzzleSolved() {
        boolean solved = true;
        Iterator<Tile> iter = board.iterator();
        int expectedId = 1;
        while (iter.hasNext()) {
            if (expectedId != iter.next().getId()) {
                solved = false;
                break;
            }
            expectedId++;
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
    boolean isValidTap(int position) {

        int row = position / NUM_COLS;
        int col = position % NUM_COLS;
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == NUM_COLS - 1 ? null : board.getTile(row, col + 1);
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
    void touchMove(int position) {
        //1D representation of a 2D Cell
        int row = position / NUM_COLS;
        int col = position % NUM_COLS;
        int blankId = board.numTiles(); // numRows * NUM_COLS per row + extra

        // tiles is the blank tile, swap by calling Board's swap method.\int[] dx = {0, 0, -1, 1};
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int row1 = dx[i] + row;
            int col1 = dy[i] + col;
            if (row1 >= 0 && col1 >= 0 && row1 < NUM_ROWS && col1 < NUM_COLS) {
                if (board.getTile(row1, col1).getId() == blankId) {
                    int[][] lastStep = {{row1, col1}, {row, col}};
                    undoTrack.push(lastStep);
                    board.swapTiles(row, col, row1, col1);

                    score--;

                }
            }
        }
    }

    /**
     * Undo a move up to unlimited times until the board is as original.
     */
    public void undoMove(){
        if(!undoTrack.isEmpty()) {
            int[][] lastStep = undoTrack.pop();

            board.swapTiles(lastStep[0][0], lastStep[0][1], lastStep[1][0], lastStep[1][1]);
            score++;
        }
    }

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
        startTime = System.currentTimeMillis();
    }

    /**
     * Updates start time
     */
    public void updateStartTime() {
        startTime = System.currentTimeMillis();
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

    /**
     *
     * @param board
     * @return whether or not the board is solvable
     */
    boolean isSolvable(Board board) {
        int invCount = getInvCount(board);
        if (NUM_ROWS % 2 == 1) {
            return (invCount % 2 == 0);
        } else {
            int blankTile = findReverseRowOfBlankTile(board);
            if (blankTile % 2 == 1) {
                return (invCount % 2 == 0);
            } else {
                return (invCount % 2 == 1);
            }
        }
    }

    private int findReverseRowOfBlankTile(Board board) {
        for (int i = NUM_ROWS - 1; i >= 0; i--) {
            for (int j = NUM_ROWS - 1; j >= 0; j--) {
                if (board.getTile(i,j).getId() == board.numTiles()) {
                    return NUM_ROWS - i;
                }
            }
        }
        return -1;
    }

    /**
     * Counts inversions to determine if board is solvable
     * @param board
     * @return number of inversions
     */
    private int getInvCount(Board board) {
        List<Tile> list = new ArrayList<>();
        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                Tile tile = board.getTile(i,j);
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
}
