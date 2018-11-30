package fall2018.csc2017.GameCentre;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class ScoreInfoTest {
    private int score;
    String name;
    private String GameID;
    private ScoreInfo scoreInfo;

    @Before
    public void setUp() throws Exception {
        score = 999123;
        name = "ab";
        GameID= "SUDOKU";
        scoreInfo = new ScoreInfo(score, name, GameID);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getScore() {
        int res = scoreInfo.getScore();
        assertEquals(999123, res);
    }

    @Test
    public void getName() {
        String res = scoreInfo.getName();
        assertEquals("ab", res);
    }

    @Test
    public void getGameID() {
        String res = scoreInfo.getGameID();
        assertEquals("SUDOKU", res);
    }

    @Test
    public void compareTo() {
        ScoreInfo otherScore = new ScoreInfo(123,"abs", "SUDOKU");
        assertEquals(-999000, scoreInfo.compareTo(otherScore));
    }
}
