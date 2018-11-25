package fall2018.csc2017.GameCentre.MatchingCards;

import android.graphics.Bitmap;

import java.util.Iterator;
import java.util.List;

import fall2018.csc2017.GameCentre.Tile;

/**
 * The sliding tiles board.
 */
public class Board extends fall2018.csc2017.GameCentre.Board {
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
    Board(List<Tile> tiles) {
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
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

//    @Override
//    public String toString() {
//        return "Board{" +
//                "tiles=" + Arrays.toString(tiles) +
//                '}';
//    }

}
