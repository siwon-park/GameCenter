package fall2018.csc2017.GameCentre.SlidingTiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.GameCentre.AccountManager;
//import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.LoadAndSave;
import fall2018.csc2017.GameCentre.R;

//import fall2018.csc2017.GameCentre.SlidingTiles.BoardManager;

public class ComplexityActivity extends AppCompatActivity {

    /**
     * The board manager.
     */
    private fall2018.csc2017.GameCentre.BoardManager boardManager;
    /**
     * The account manager.
     */
    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexity);

        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
        if (accountManager == null) {
            accountManager = new AccountManager();
            LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        }

        addButton2Listener();
        addButton3Listener();
        addButton4Listener();
        addButton5Listener();
    }

    /**
     * Activate the 2x2 button.
     */
    private void addButton2Listener() {
        Button startButton = findViewById(R.id.button2);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Todo: remove the following extra code after fixing BoardManager()
                Board.NUM_ROWS = 2;
                Board.NUM_COLS = 2;
                boardManager = new BoardManager(BoardManager.SLIDING_TILES_GAME);
                boardManager.setSavedNumRows(2);
                boardManager.setSavedNumCols(2);
                saveCurrentBoardManager();
                switchToBackgroundChange();
            }
        });
    }


    /**
     * Activate the 3x3 button.
     */
    private void addButton3Listener() {
        Button startButton = findViewById(R.id.button3);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.NUM_ROWS = 3;
                Board.NUM_COLS = 3;
                boardManager = new BoardManager(BoardManager.SLIDING_TILES_GAME);
                boardManager.setSavedNumRows(3);
                boardManager.setSavedNumCols(3);
                saveCurrentBoardManager();
                switchToBackgroundChange();
            }
        });
    }

    /**
     * Activate the 4x4 button.
     */
    private void addButton4Listener() {
        Button startButton = findViewById(R.id.button4);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.NUM_ROWS = 4;
                Board.NUM_COLS = 4;
                boardManager = new BoardManager(BoardManager.SLIDING_TILES_GAME);
                boardManager.setSavedNumCols(4);
                boardManager.setSavedNumRows(4);
                saveCurrentBoardManager();
                switchToBackgroundChange();
            }
        });
    }

    /**
     * Activate the 5x5 button.
     */
    private void addButton5Listener() {
        Button startButton = findViewById(R.id.button5);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.NUM_ROWS = 5;
                Board.NUM_COLS = 5;
                boardManager = new BoardManager(BoardManager.SLIDING_TILES_GAME);
                boardManager.setSavedNumRows(5);
                boardManager.setSavedNumCols(5);
                saveCurrentBoardManager();
                switchToBackgroundChange();
            }
        });
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToBackgroundChange() {
        Intent tmp = new Intent(this, ImageActivity.class);
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        saveCurrentBoardManager();
        startActivity(tmp);
    }

    /**
     * Save the current BoardManager.
     */
    private void saveCurrentBoardManager() {
        LoadAndSave.saveToFile(accountManager.getCurrentAccount().getCurrentGameFileName(),
                boardManager, this);
    }

}
