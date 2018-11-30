package fall2018.csc2017.GameCentre.Sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.Tile;

public class SudokuBoardManager extends BoardManager implements Serializable{
    /**
     * A stack that keeps track of completed moves.
     */
    private Stack<int[][]> undoTrack = new Stack<>();

    private int[] cardIDs = {
            R.drawable.tile_1_s, R.drawable.tile_2_s,
            R.drawable.tile_3_s, R.drawable.tile_4_s,
            R.drawable.tile_5_s, R.drawable.tile_6_s,
            R.drawable.tile_7_s, R.drawable.tile_8_s,
            R.drawable.tile_9_s, R.drawable.tile_1_user_s,
            R.drawable.tile_2_user_s, R.drawable.tile_3_user_s,
            R.drawable.tile_4_user_s, R.drawable.tile_5_user_s,
            R.drawable.tile_6_user_s, R.drawable.tile_7_user_s,
            R.drawable.tile_8_user_s, R.drawable.tile_9_user_s,
            R.drawable.blank_tile_s
    };

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    public SudokuBoardManager(Board board) {
        this.board = board;
    }

    /**
     * Manage a new shuffled board.
     */
    public SudokuBoardManager() {
        setGameID(2);
        setGameName(SUDOKU_GAME);

        createBoard();
    }

    SudokuBoardManager(List<Tile> tiles) {
        createBoard();
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void createBoard() {
        board = null;
        List<Tile> tiles = new ArrayList<>();
        for (int n = 1; n <= 81; n++) {
            tiles.add(new Tile(cardIDs[(int) (Math.random()*8)]));
            while (board == null) {
                Collections.shuffle(tiles);
                board = new SudokuBoard(tiles);
            }
        }
    }
    /**
     * Undo a move up to unlimited times until the board is as original.
     */
    @Override
    public void undoMove() {
    }
    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    @Override
    public boolean puzzleSolved() {
        return false;
    }
    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */

    @Override
    public boolean isValidTap(int position) {

        int row = position / SudokuBoard.NUM_COLS;
        int col = position % SudokuBoard.NUM_COLS;
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == SudokuBoard.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == SudokuBoard.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    @Override
    public void touchMove(int position) {
        //1D representation of a 2D Cell
        int row = position / Board.NUM_COLS;
        int col = position % Board.NUM_COLS;
        int currentID = board.getTile(row, col).getId();
        int[] clickedOn = {row, col};
    }

    /**
     * Here is the score formula:
     * Score = 100 + NUM_ROWS * NUM_COLS * 2 - moves - time_in_seconds / 60
     * We give more points for a larger board. We also deduct 1 point for each move, but we revert
     * the score for each move that is undone. For every 1 minute, we deduct 1 point.
     */
    public void updateScore() {
        long endTime = System.currentTimeMillis();
        score -= (endTime - startTime) / 1000 / 60;
    }


}
