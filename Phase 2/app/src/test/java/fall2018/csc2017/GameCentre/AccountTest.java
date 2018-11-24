package fall2018.csc2017.GameCentre;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AccountTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void matchPassword() {
    }

    @Test
    public void testGetAndSetScore() {
        Account account = new Account("a","ab","abc");
        int[] scores = {100, 50, 250, 110, 220, 33};
        for (int score : scores) {
            account.setScore(score);
        }

        for (int i = 0; i < account.getScores().size(); i++) {
            assertEquals(scores[i], (long) account.getScores().get(i));
        }
    }

    @Test
    public void getSavedGameFileName() {
    }

    @Test
    public void getCurrentGameFileName() {
    }

    @Test
    public void getUsername() {
        // Tested by AccountManagerTest
    }

    @Test
    public void getName() {
    }

    @Test
    public void getSaved() {
    }

    @Test
    public void setSaved() {
    }

    @Test
    public void getGamePlayed() {
    }

    @Test
    public void setGamePlayed() {
    }
}