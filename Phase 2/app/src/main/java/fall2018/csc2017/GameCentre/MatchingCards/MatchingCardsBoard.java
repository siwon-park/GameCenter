package fall2018.csc2017.GameCentre.MatchingCards;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.Tile;

/**
 * The sliding tiles board.
 */
public class MatchingCardsBoard extends fall2018.csc2017.GameCentre.Board implements Serializable {

    /**
     * The last clicks that the user made.
     */
    private Stack<int[]> lastClicks = new Stack<>();

    /**
     * Whether there is a flip in progress
     */
    private boolean flipInProgress = false;

    /**
     * Getter method for LastClicks
     * @return the last clicks that the user made.
     */
    public Stack<int[]> getLastClicks() {
        return lastClicks;
    }

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    public MatchingCardsBoard(List<Tile> tiles) {
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != MatchingCardsBoard.NUM_ROWS; row++) {
            for (int col = 0; col != MatchingCardsBoard.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Clear the tiles that have been matched correctly
     * @param row the tile row
     * @param col the tile column
     */
    public void clearTiles(int row, int col) {
        tiles[row][col].setBackground(R.drawable.tile_blank);
        tiles[row][col].setID(MatchingCardsBoard.NUM_COLS * MatchingCardsBoard.NUM_COLS);
        setChanged();
        notifyObservers();
    }

    /**
     * Flips the cards that have been tapped.
     */
    void flipCards() {
            flipInProgress = true;
            setChanged();
            notifyObservers();
    }

    /**
     * Getter method for FlipInProgress
     * @return whether there is a flip in progress
     */
    public boolean getFlipInProgress() {
        return flipInProgress;
    }
}
