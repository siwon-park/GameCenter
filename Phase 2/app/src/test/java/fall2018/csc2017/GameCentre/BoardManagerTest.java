package fall2018.csc2017.GameCentre;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static fall2018.csc2017.GameCentre.SlidingTiles.Board.NUM_COLS;
import static fall2018.csc2017.GameCentre.SlidingTiles.Board.NUM_ROWS;
import static org.junit.Assert.*;

public class BoardManagerTest {
    private fall2018.csc2017.GameCentre.BoardManager boardManager;
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
    public void getScore() {
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

}
