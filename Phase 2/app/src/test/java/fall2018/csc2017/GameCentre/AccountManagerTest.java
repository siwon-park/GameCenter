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
        int[] a1Scores = {100, 102, 99, 97, 103, 104};
        int[] a2Scores = {97, 94, 99, 102, 97, 98, 105};
        int[] a3Scores = {101, 98, 94, 103, 104, 106};
        addScores(a1, a1Scores);
        addScores(a2, a2Scores);
        addScores(a3, a3Scores);
        accountManager.addAccount(a1);
        accountManager.addAccount(a2);
        accountManager.addAccount(a3);
        String[] scores = accountManager.displayPerGame();
        assertEquals(20, scores.length);
        assertEquals("Top Players", scores[0]);
        assertEquals("1.  a3 : 106", scores[1]);
        assertEquals("2.  a2 : 105", scores[2]);
        assertEquals("3.  a1 : 104", scores[3]);
        assertEquals("4.  a3 : 104", scores[4]);
        assertEquals("5.  a1 : 103", scores[5]);
    }

    @Test
    public void testDisplayPerUser() {
        Account a1 = new Account("a1", "a1", "a1");
        int[] a1Scores = {100, 102, 99, 97, 103, 104};
        addScores(a1, a1Scores);
        accountManager.addAccount(a1);
        accountManager.setCurrentAccount(a1);
        String[] scores = accountManager.displayPerUser();
        assertEquals(7, scores.length);
        assertEquals("Top Scores", scores[0]);
        assertEquals("1.  a1 : 104", scores[1]);
        assertEquals("2.  a1 : 103", scores[2]);
        assertEquals("3.  a1 : 102", scores[3]);
        assertEquals("4.  a1 : 100", scores[4]);
        assertEquals("5.  a1 : 99", scores[5]);
        assertEquals("6.  a1 : 97", scores[6]);
    }

    private void addScores(Account account, int[] scores) {
        for (int score : scores) {
            account.setScore(score);
        }
    }
}