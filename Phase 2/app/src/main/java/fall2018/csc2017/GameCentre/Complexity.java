package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.GameCentre.SlidingTiles.BoardManager;

public class Complexity extends AppCompatActivity {

    /**
     * The board manager.
     */
    private BoardManager boardManager;
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
                boardManager = new BoardManager();
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
                boardManager = new BoardManager();
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
                boardManager = new BoardManager();
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
                boardManager = new BoardManager();
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
