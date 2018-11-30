package fall2018.csc2017.GameCentre.Sudoku;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.Tile;

/**
 * The sudoku board
 */
public class SudokuBoard extends fall2018.csc2017.GameCentre.Board implements Serializable{

    /**
     * The number of rows.
     */
    public static int NUM_ROWS = 9;

    /**
     * The number of columns.
     */
    public static int NUM_COLS = 9;

    protected int score = 0; // Probably have the score calculating num_moves? Change formula as theres no board sze complexity.

    private long startTime = 0;

    protected int clickedROW = 0;
    protected int clickedCOL = 0;

    /**
     * A stack that keeps track of completed moves.
     */
    private Stack<int[][]> undoTrack = new Stack<>();

    public SudokuBoard(List<Tile> tiles) {

        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != SudokuBoard.NUM_ROWS; row++) {
            for (int col = 0; col != SudokuBoard.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    public void select(int position){
        int row = position / SudokuBoard.NUM_COLS;
        int col = position % SudokuBoard.NUM_COLS;
//        int HighlightId =44;
//        tiles[row][col].setID(44);
//        tiles[row][col].setBackground(R.drawable.selected_tile_s);
        clickedROW = row;
        clickedCOL = col;
    }

    public void deselect(int ID){
        int translatedTileID = ID + 33;
//        boolean selected = false;
//        for(Tile[] tile: this.tiles){
//            for (Tile tile2: tile){
//                if (tile2.getId()==44){
//                    tile2.setID(ID);
//                    tile2.setBackground(R.drawable.blank_tile_s);
//                }
//            }
//       }
        tiles[clickedROW][clickedCOL] = new Tile(translatedTileID);
        setChanged();
        notifyObservers();
    }

}
