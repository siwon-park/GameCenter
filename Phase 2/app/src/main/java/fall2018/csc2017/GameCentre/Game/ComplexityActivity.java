package fall2018.csc2017.GameCentre.Game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fall2018.csc2017.GameCentre.AccountManager;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.LoadAndSave;
import fall2018.csc2017.GameCentre.R;

//import fall2018.csc2017.GameCentre.BoardManager;

//import fall2018.csc2017.GameCentre.SlidingTiles.BoardManager;

abstract public class ComplexityActivity extends AppCompatActivity {

    /**
     * The board manager.
     */
    protected BoardManager boardManager;
    /**
     * The account manager.
     */
    protected AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
        if (accountManager == null) {
            accountManager = new AccountManager();
            LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        }
        boardManager = (BoardManager) LoadAndSave.loadFromFile(
                accountManager.getCurrentAccount().getCurrentGameFileName(), this);
        switch (boardManager.getGameName()) {
            case BoardManager.SLIDING_TILES_GAME:
                setContentView(R.layout.activity_complexity);
                break;
            case BoardManager.MATCHING_CARDS_GAME:
                setContentView(R.layout.activity_matching_complexity);
                break;
            default:
                // There is no complexity for Sudoku game.
        }

        addButton2Listener();
        addButton3Listener();
        addButton4Listener();
        addButton5Listener();
        addButton6Listener();
    }

    /**
     * Activate the 2x2 button.
     */
    abstract protected void addButton2Listener();


    /**
     * Activate the 3x3 button.
     */
    abstract protected void addButton3Listener();

    /**
     * Activate the 4x4 button.
     */
    abstract protected void addButton4Listener();

    /**
     * Activate the 5x5 button.
     */
    abstract protected void addButton5Listener();

    /**
     * Activate the 6x6 button.
     */
    abstract protected void addButton6Listener();

    /**
     * Save the current BoardManager.
     */
    protected void saveCurrentBoardManager() {
        LoadAndSave.saveToFile(accountManager.getCurrentAccount().getCurrentGameFileName(),
                boardManager, this);
    }

}
