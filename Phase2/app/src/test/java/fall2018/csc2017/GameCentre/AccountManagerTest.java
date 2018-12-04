package fall2018.csc2017.GameCentre;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AccountManagerTest {
    private AccountManager accountManager;
    @Before
    public void setUp() throws Exception {
        accountManager = new AccountManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddAndGetAccount() {
        String name = "JQ";
        String username = "510_s";
        String password = "123";
        // First tests true case by creating Account object and adding it
        Account acc = new Account(name, username, password);
        boolean added = accountManager.addAccount(acc);
        assertTrue(added);

        // Second tests false case by adding same Account object
        added = accountManager.addAccount(acc);
        assertFalse(added);

        String name2 = "A";
        String password2 = "24";
        Account accountWithSameUsername = new Account(name2, username, password2);
        added = accountManager.addAccount(accountWithSameUsername);
        assertFalse(added);

        // Test getAccount()
        String fakeUsername = "faker";
        Account account = accountManager.getAccount(username);
        assertEquals(acc, account);

        account = accountManager.getAccount(fakeUsername);
        assertNull(account);
    }

    @Test
    public void testSetAndGetCurrentAccount() {
        String name = "JQ";
        String username = "510_s";
        String password = "123";
        Account acc = new Account(name, username, password);
        accountManager.addAccount(acc);
        accountManager.setCurrentAccount(acc);
        Account acc2 = accountManager.getCurrentAccount();
        assertEquals(acc, acc2);
    }

    @Test
    public void testDisplayPerGame() {
        Account a1 = new Account("a1", "a1", "a1");
        Account a2 = new Account("a2", "a2", "a2");
        Account a3 = new Account("a3", "a3", "a3");
        ScoreInfo score1 = new ScoreInfo(100, "a1", "SLIDING_TILES" );
        ScoreInfo score2 = new ScoreInfo(115, "a1", "SLIDING_TILES" );
        ScoreInfo score3 = new ScoreInfo(5, "a1", "SLIDING_TILES" );
        ScoreInfo score4 = new ScoreInfo(150, "a2", "SLIDING_TILES" );
        ScoreInfo score5 = new ScoreInfo(149, "a2", "SLIDING_TILES" );
        ScoreInfo score6= new ScoreInfo(90, "a2", "SLIDING_TILES" );
        ScoreInfo score7 = new ScoreInfo(101, "a3", "SLIDING_TILES" );
        ScoreInfo score8 = new ScoreInfo(45, "a3", "SLIDING_TILES" );
        ScoreInfo score9 = new ScoreInfo(2, "a3", "SLIDING_TILES" );
        ScoreInfo[] a1Scores = {score1, score2, score3};
        ScoreInfo[] a2Scores = {score4, score5, score6};
        ScoreInfo[] a3Scores = {score7, score8, score9};

        accountManager.addAccount(a1);
        accountManager.addAccount(a2);
        accountManager.addAccount(a3);
        for(ScoreInfo info :a1Scores){
            a1.setScore(info);
        }
        for(ScoreInfo info :a2Scores){
            a2.setScore(info);
        }
        for(ScoreInfo info :a3Scores){
            a3.setScore(info);
        }

        String[] scores = accountManager.displayPerGame("SLIDING_TILES");
        assertEquals(10, scores.length);
        assertEquals("Top Players", scores[0]);
        assertEquals("1.  a2 : 150", scores[1]);
        assertEquals("2.  a2 : 149", scores[2]);
        assertEquals("3.  a1 : 115", scores[3]);
        assertEquals("4.  a3 : 101", scores[4]);
        assertEquals("5.  a1 : 100", scores[5]);
        assertEquals("6.  a2 : 90", scores[6]);
        assertEquals("7.  a3 : 45", scores[7]);
        assertEquals("8.  a1 : 5", scores[8]);
        assertEquals("9.  a3 : 2", scores[9]);

    }

    @Test
    public void testDisplayPerUser() {
        Account a1 = new Account("a1", "a1", "a1");
        ScoreInfo score1 = new ScoreInfo(100, "a1", "SLIDING_TILES" );
        ScoreInfo score2 = new ScoreInfo(115, "a1", "SLIDING_TILES" );
        ScoreInfo score3 = new ScoreInfo(5, "a1", "SLIDING_TILES" );
        ScoreInfo[] a1Scores = {score1, score2, score3};

        accountManager.addAccount(a1);
        accountManager.setCurrentAccount(a1);
        for(ScoreInfo info :a1Scores){
            accountManager.getCurrentAccount().setScore(info);
        }

        String[] scores = accountManager.displayPerUser("a1","SLIDING_TILES");
        List scores_user = a1.getScores();
        assertEquals(3, scores_user.size());
        assertEquals(4, scores.length);
        assertEquals( "a1's Top Scores", scores[0]);
        assertEquals("1.  a1 : 115", scores[1]);
        assertEquals("2.  a1 : 100", scores[2]);
        assertEquals("3.  a1 : 5", scores[3]);
    }
    @Test
    public void testDisplayPerSearch(){
        Account a1 = new Account("a1", "a1", "a1");
        Account a2 = new Account("a2", "a2", "a2");
        Account a3 = new Account("a3", "a3", "a3");
        ScoreInfo score1 = new ScoreInfo(100, "a1", "SLIDING_TILES" );
        ScoreInfo score2 = new ScoreInfo(115, "a1", "SLIDING_TILES" );
        ScoreInfo score3 = new ScoreInfo(5, "a1", "SLIDING_TILES" );
        ScoreInfo score4 = new ScoreInfo(150, "a2", "SLIDING_TILES" );
        ScoreInfo score5 = new ScoreInfo(149, "a2", "SLIDING_TILES" );
        ScoreInfo score6= new ScoreInfo(90, "a2", "SLIDING_TILES" );
        ScoreInfo score7 = new ScoreInfo(101, "a3", "SLIDING_TILES" );
        ScoreInfo score8 = new ScoreInfo(45, "a3", "SLIDING_TILES" );
        ScoreInfo score9 = new ScoreInfo(2, "a3", "SLIDING_TILES" );
        ScoreInfo[] a1Scores = {score1, score2, score3};
        ScoreInfo[] a2Scores = {score4, score5, score6};
        ScoreInfo[] a3Scores = {score7, score8, score9};

        accountManager.addAccount(a1);
        accountManager.addAccount(a2);
        accountManager.addAccount(a3);
        for(ScoreInfo info :a1Scores){
            a1.setScore(info);
        }
        for(ScoreInfo info :a2Scores){
            a2.setScore(info);
        }
        for(ScoreInfo info :a3Scores){
            a3.setScore(info);
        }

        String[] scores1 = accountManager.displayPerSearch("a1","SLIDING_TILES");
        assertEquals(10, scores1.length);
        assertEquals("a1's ranking on Leaderboards", scores1[0]);
        assertEquals("8.  a1 : 5", scores1[3]);
        assertEquals("3.  a1 : 115", scores1[1]);
        assertEquals("5.  a1 : 100", scores1[2]);

        String[] scores2 = accountManager.displayPerSearch("a2","SLIDING_TILES");
        assertEquals(10, scores2.length);
        assertEquals("a2's ranking on Leaderboards", scores2[0]);
        assertEquals("1.  a2 : 150", scores2[1]);
        assertEquals("2.  a2 : 149", scores2[2]);
        assertEquals("6.  a2 : 90", scores2[3]);

        String[] scores3 = accountManager.displayPerSearch("a3","SLIDING_TILES");
        assertEquals(10, scores3.length);
        assertEquals("a3's ranking on Leaderboards", scores3[0]);
        assertEquals("4.  a3 : 101", scores3[1]);
        assertEquals("7.  a3 : 45", scores3[2]);
        assertEquals("9.  a3 : 2", scores3[3]);

    }


    private void addScores(Account account, int[] scores) {
//        for (int score : scores) {
//            account.setScore(score);
//        }
    }
}