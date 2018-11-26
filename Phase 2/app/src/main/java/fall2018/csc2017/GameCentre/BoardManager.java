package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static fall2018.csc2017.GameCentre.SlidingTiles.Board.NUM_COLS;
import static fall2018.csc2017.GameCentre.SlidingTiles.Board.NUM_ROWS;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager implements Serializable {
    public static final String SLIDING_TILES_GAME = "Sliding Tiles";
    public static final String MATCHING_CARDS_GAME = "Matching Cards";
    public static final String WHACK_A_MOLE_GAME = "Whack A Mole";

    // Todo: use game name in Board instead of gameID here.
    /**
     * The id of the game that is in play
     */

    private Board board;
    private int savedNumCols;
    private int savedNumRows;

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
    // Todo: move createBoard() out of this so that we can set num/col first, then create board
    public BoardManager(String gameName) {
        List<Tile> tiles = new ArrayList<>();
        if (gameName.equals(SLIDING_TILES_GAME)) {
            final int numTiles = NUM_ROWS * NUM_COLS;
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                tiles.add(new Tile(tileNum));
            }
        } else if (gameName.equals(MATCHING_CARDS_GAME)){
            int[] cardIDs = {
                    R.drawable.tile_1, R.drawable.tile_2,
                    R.drawable.tile_3, R.drawable.tile_4,
                    R.drawable.tile_5, R.drawable.tile_6,
                    R.drawable.tile_7, R.drawable.tile_8
            };

            final int numTiles = NUM_ROWS * NUM_COLS;
            int[] temp = new int[numTiles];
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                if (tileNum % 2 == 0) {
                    temp[tileNum] = cardIDs[tileNum / 2];
                } else {
                    temp[tileNum] = cardIDs[(tileNum - 1) / 2];
                }
                tiles.add(new Tile(tileNum + 1, temp[tileNum]));
            }
        } else {
            // Todo: throw exception or log error?
            return;
        }
        createBoard(tiles, gameName);
    }

    BoardManager(List<Tile> tiles, String gameName) {
        createBoard(tiles, gameName);
    }

    public Board getBoard() {
        return board;
    }

    // Todo: need factory pattern
    protected void createBoard(List<Tile> tiles, String gameName) {

        while (board == null || !board.isSolvable()) {
            Collections.shuffle(tiles);
            switch (gameName) {
                case SLIDING_TILES_GAME:
                    board = new fall2018.csc2017.GameCentre.SlidingTiles.Board(tiles, gameName);
                    break;
                case MATCHING_CARDS_GAME:
                    // Todo: create matching cards game
                    board = new fall2018.csc2017.GameCentre.MatchingCards.Board(tiles);
                    break;
                case WHACK_A_MOLE_GAME:
                    // Todo: create Whack A Mole game
                    break;
            }
        }
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    public boolean puzzleSolved() {
        boolean solved = board.puzzleSolved();
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
    public boolean isValidTap(int position) {
        return board.isValidTap(position);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */

    public void touchMove(int position) {
        board.touchMove(position);
    }

    /**
     * Undo a move up to unlimited times until the board is as original.
     */
    public void undoMove(){
        board.undoMove();
    }

    /**
     * getter method for the GameID
     * @return gameID
     */

    public int getScore() {
        return board.getScore();
    }

    /**
     * Updates score
     */
    public void updateScore() {
        board.updateScore();
    }

    /**
     * Sets starting score and time
     */
    public void setStartingScoreAndTime() {
        board.setStartingScoreAndTime();
    }

    /**
     * Updates start time
     */
    public void updateStartTime() {
        board.updateStartTime();
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
}
