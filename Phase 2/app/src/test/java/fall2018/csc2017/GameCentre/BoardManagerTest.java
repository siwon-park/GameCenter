package fall2018.csc2017.GameCentre;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static fall2018.csc2017.GameCentre.Board.NUM_COLS;
import static fall2018.csc2017.GameCentre.Board.NUM_ROWS;
import static org.junit.Assert.*;

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
    public void updateScore() {
        boardManager.setStartingScoreAndTime();
        long score = boardManager.getScore();
        boardManager.setStartTime(System.currentTimeMillis() - 60000);
        boardManager.updateScore();
        long endTime = System.currentTimeMillis();
        score -= (endTime - boardManager.getStartTime()) / 1000 / 60;
        assertEquals(score, boardManager.getScore());
    }

    @Test
    public void testGetScoreAndSetStartingScoreAndTime() {
        boardManager.setStartingScoreAndTime();
        assertEquals(100 + NUM_ROWS * NUM_COLS * 2, boardManager.getScore());
    }

    @Test
    public void testUpdateStartTime() {
        boardManager.updateStartTime();
        long startTime = boardManager.getStartTime();
        assertTrue(System.currentTimeMillis() - startTime <= 5000);
    }

    @Test
    public void getSavedNumCols() {
    }

    @Test
    public void getSavedNumRows() {
    }

    @Test
    public void setSavedNumCols() {
    }

    @Test
    public void setSavedNumRows() {
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
        Board.NUM_COLS = rows;
        Board.NUM_ROWS = cols;
        List<Tile> tiles = new ArrayList<>();
        for (int tileId : arr) {
            tiles.add(new Tile(tileId - 1));
        }
        Board board = new Board(tiles);
        boolean result = boardManager.isSolvable(board);
        assertEquals(solvable, result);
    }
}
