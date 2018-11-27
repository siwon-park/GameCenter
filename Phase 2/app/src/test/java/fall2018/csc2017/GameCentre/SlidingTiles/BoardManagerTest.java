package fall2018.csc2017.GameCentre.SlidingTiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardManagerTest {
    private BoardManager boardManager;
    @Before
    public void setUp() throws Exception {
        boardManager = new BoardManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getBoard() {
    }

    @Test
    public void puzzleSolved() {
    }

    @Test
    public void isValidTap() {
    }

    @Test
    public void touchMove() {
    }

    @Test
    public void undoMove() {
    }

    @Test
    public void testIsSolvableWithOddWidthBoardSolvable() {
        int rows = 3;
        int cols = 3;
        boolean solvable = true;
        int[] arr = {1, 8, 2, 9, 4, 3, 7, 6, 5};
        testBoardIsSolvable(arr, rows, cols, solvable);
    }

    @Test
    public void testIsSolvableWithOddWidthBoardNonSolvable() {
        int rows = 3;
        int cols = 3;
        boolean solvable = false;
        int[] arr = {8, 1, 2, 9, 4, 3, 7, 6, 5};
        testBoardIsSolvable(arr, rows, cols, solvable);
    }

    @Test
    public void testIsSolvableWithEvenWidthBoardSolvable() {
        int rows = 4;
        int cols = 4;
        boolean solvable = true;
        int[] arr = {13, 2, 10, 3, 1, 12, 8, 4, 5, 16, 9, 6, 15, 14, 11, 7};
        testBoardIsSolvable(arr, rows, cols, solvable);
    }

    @Test
    public void testIsSolvableWithEvenWidthBoardNonSolvable() {
        int rows = 4;
        int cols = 4;
        boolean solvable = false;
        int[] arr = {3, 9, 1, 15, 14, 11, 4, 6, 13, 16, 10, 12, 2, 7, 8, 5};
        testBoardIsSolvable(arr, rows, cols, solvable);
    }

    private void testBoardIsSolvable(int[] arr, int rows, int cols, boolean solvable) {
//        SlidingTilesBoard.NUM_COLS = rows;
//        SlidingTilesBoard.NUM_ROWS = cols;
//        List<Tile> tiles = new ArrayList<>();
//        for (int tileId : arr) {
//            tiles.add(new Tile(tileId - 1));
//        }
//        SlidingTilesBoard board = new SlidingTilesBoard(tiles);
//        boolean result = boardManager.isSolvable(board);
//        assertEquals(solvable, result);
    }
}
