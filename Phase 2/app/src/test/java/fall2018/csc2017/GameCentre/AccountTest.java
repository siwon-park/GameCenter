package fall2018.csc2017.GameCentre;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static fall2018.csc2017.GameCentre.BoardManager.SLIDING_TILES_GAME;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

public class AccountTest {
    private String name;
    private String username;
    private String password;
    private Account account;

    @Before
    public void setUp() throws Exception {
        name = "a";
        username = "ab";
        password = "abc";
        account = new Account(name, username, password);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMatchPasswordAndGettersForSeveralMemberVariables() {
        String savedGameFileName = SLIDING_TILES_GAME + username + "_saved_game" + ".ser";;
        String currentGameFileName = username + "_current_game" + ".ser";
        assertEquals(savedGameFileName, account.getSavedGameFileName(SLIDING_TILES_GAME));
        assertEquals(currentGameFileName, account.getCurrentGameFileName());
        assertEquals(name, account.getName());
        assertEquals(username, account.getUsername());
        assertTrue(account.matchPassword(password));
    }

    @Test
    public void testGetAndSetScore() {
        ScoreInfo score1 = new ScoreInfo(123, "a", "SLIDING_TILES");
        ScoreInfo score2 = new ScoreInfo(999, "a", "MATCHING_CARDS");
        ScoreInfo[] scores = {score1, score2};

        for (ScoreInfo score: scores){
            account.setScore(score);
        }
        for (int i = 0; i < account.getScores().size(); i++) {
            assertEquals(scores[i], account.getScores().get(i));
        }
    }

    @Test
    public void testSetSavedAndGetSaved() {
        account.setSaved(true, SLIDING_TILES_GAME);
        boolean res = account.getSaved(SLIDING_TILES_GAME);
        assertTrue(res);
        account.setSaved(false, SLIDING_TILES_GAME);
        res = account.getSaved(SLIDING_TILES_GAME);
        assertFalse(res);
    }

    @Test
    public void testSetGamePlayedAndGetGamePlayed() {
        account.setGamePlayed(true);
        boolean res = account.getGamePlayed();
        assertTrue(res);
        account.setGamePlayed(false);
        res = account.getGamePlayed();
        assertFalse(res);
    }

    @Test
    public void testSetGamePlayedIDAndGetGamePlayedID(){
        account.setGamePlayedId("SLIDING_TILES");
        String res = account.getGamePlayedId();
        assert(res.equals("SLIDING_TILES"));
        account.setGamePlayedId("MATCHING_CARDS");
        assert(!res.equals(account.getGamePlayedId()));

    }
}