package fall2018.csc2017.GameCentre;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static android.content.Context.MODE_PRIVATE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// @RunWith(MockitoJUnitRunner.class)
public class LoadAndSaveTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSaveToFileAndLoadFromFile() throws Exception {
        // test successful case
        LoginActivity testActivity = mock(LoginActivity.class);
        String fileName = "/tmp/account_manager_file.ser";
        when(testActivity.openFileOutput(fileName, MODE_PRIVATE))
                .thenReturn(new FileOutputStream(fileName));
        when(testActivity.openFileInput(fileName))
                .thenReturn(new FileInputStream(fileName));

        AccountManager accountManager = new AccountManager();
        Account account = new Account("a", "a", "a");
        accountManager.setCurrentAccount(account);
        LoadAndSave.saveToFile(fileName, accountManager, testActivity);

        AccountManager accountManagerFromFile = (AccountManager) LoadAndSave.loadFromFile(
                fileName, testActivity);

        assertEquals(accountManager.getCurrentAccount().getName(),
                accountManagerFromFile.getCurrentAccount().getName());
        assertEquals(accountManager.getCurrentAccount().getUsername(),
                accountManagerFromFile.getCurrentAccount().getUsername());

        // Todo: test exceptions
    }
}
