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
            R.drawable.blank_tile_s, R.drawable.selected_tile_s
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
        setSavedNumCols(9);
        setSavedNumRows(9);
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
        for (int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                    Tile temp = new Tile(col);
                    tiles.add(temp);

            }
            Collections.shuffle(tiles);
//            tiles.add(new Tile(cardIDs[(int) (Math.random() * 8)]));
        }
        while (board == null) {
//            Collections.shuffle(tiles);
            board = new SudokuBoard(tiles);
        }
    }
    /**
     * Undo a move up to unlimited times until the board is as original.
     */
    public void undoMove() {
    }

    /**
     * Referenced from https://github.com/Knutakir/Android-Sudoku/blob/master/app/src/main/java/me/kirkhorn/knut/android_sudoku/model/Board.java
     * @return whether the sudoku board is correct
     */

    @Override
    public boolean puzzleSolved() {
        for (int n = 0; n <= 81; n++) {
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 0; i <= 9; i++) {
                int num = (board.getTile(n, i)).getId() - 25;
                if (numbers.contains(num)){
                    return false;
                }
                else {
                    numbers.add(num);
                }
            }
        }
        for (int n = 0; n <= 81; n++) {
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 0; i <= 9; i++) {
                int num = (board.getTile(i, n)).getId() - 25;
                if (numbers.contains(num)){
                    return false;
                }
                else {
                    numbers.add(num);
                }
            }
        }

        return true;
    }
    /**
     * Return whether the tile is not a tile given by the original board.
     *
     * @param position the tile to check
     * @return whether the tile at position is a user selectable tile
     */
    @Override
    public boolean isValidTap(int position) {
        boolean valid = false;
        int row = position / SudokuBoard.NUM_COLS;
        int col = position % SudokuBoard.NUM_COLS;
        int startingId = 34;
        int blankId = 43;
        if(board.getTile(row,col).getId() >= startingId &&board.getTile(row,col).getId() <= blankId){
            valid = true;
        }
        return valid;
    }



    @Override
    public void touchMove(int position) {

        SudokuBoard board1 = (SudokuBoard) this.board;
        board1.deselect(43);
        board1.select(position);
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
