package fall2018.csc2017.GameCentre.MatchingCards;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.MatchingCards.MatchingCardsBoard;
import fall2018.csc2017.GameCentre.MatchingCards.MatchingCardsBoardManager;
import fall2018.csc2017.GameCentre.Tile;

import static org.junit.Assert.*;

public class MatchingCardsBoardTest {
    /**
     * The board manager for testing.
     */
    MatchingCardsBoardManager boardManager;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
//    private List<Tile> makeTiles() {
//        List<Tile> tiles = new ArrayList<>();
//        final int numTiles = MatchingCardsBoard.NUM_ROWS * MatchingCardsBoard.NUM_COLS;
//        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
//            tiles.add(new Tile(tileNum + 1, tileNum));
//        }
//
//        return tiles;
//    }

    /**
     * Make a MatchingCardsBoard
     */
    private void setUp() {
//        List<Tile> tiles = makeTiles();
//        MatchingCardsBoard board = new MatchingCardsBoard(tiles);
        boardManager = new MatchingCardsBoardManager();
    }

//
//    @Test
//    public void getLastClicks() {
//
//    }

    @Test
    public void clearTiles() {
        setUp();
        MatchingCardsBoard board = (MatchingCardsBoard) boardManager.getBoard();
        board.clearTiles(0, 0);
        assert(boardManager.getBoard().getTile(0, 0).isBlank());
    }
//
//    @Test
//    public void flipCards() {
//    }
//
//    @Test
//    public void getToBeFlipped() {
//    }
//
//    @Test
//    public void getFlipInProgress() {
//
//}
}