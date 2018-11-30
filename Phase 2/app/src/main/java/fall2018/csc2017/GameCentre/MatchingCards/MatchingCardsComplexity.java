package fall2018.csc2017.GameCentre.MatchingCards;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.Game.ComplexityActivity;
import fall2018.csc2017.GameCentre.LoadAndSave;
import fall2018.csc2017.GameCentre.R;

public class MatchingCardsComplexity extends ComplexityActivity {

    /**
     * Activate the 2x2 button.
     */
    @Override
    protected void addButton2Listener() {
        Button startButton = findViewById(R.id.matchingbutton2);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Todo: remove the following extra code after fixing BoardManager()
                Board.NUM_ROWS = 2;
                Board.NUM_COLS = 2;
                boardManager.createBoard();
                boardManager.setSavedNumRows(2);
                boardManager.setSavedNumCols(2);
                switchToGame();
            }
        });
    }

    /**
     * An empty function for 3x3 button though it does not exist here
     */
    @Override
    protected void addButton3Listener() {

    }

    /**
     * Activate the 4x4 button.
     */
    @Override
    protected void addButton4Listener() {
        Button startButton = findViewById(R.id.matchingbutton4);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.NUM_ROWS = 4;
                Board.NUM_COLS = 4;
                boardManager.createBoard();
                boardManager.setSavedNumCols(4);
                boardManager.setSavedNumRows(4);
                switchToGame();
            }
        });
    }

    /**
     * An empty function for 5x5 button though it does not exist here
     */
    @Override
    protected void addButton5Listener() {

    }

    /**
     * Activate the 6x6 button.
     */
    @Override
    protected void addButton6Listener() {
        Button startButton = findViewById(R.id.matchingbutton6);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.NUM_ROWS = 6;
                Board.NUM_COLS = 6;
                boardManager.createBoard();
                boardManager.setSavedNumRows(6);
                boardManager.setSavedNumCols(6);
                switchToGame();
            }
        });
    }

    /**
     * Save the current BoardManager.
     */
    protected void saveCurrentBoardManager() {
        LoadAndSave.saveToFile(accountManager.getCurrentAccount().getCurrentGameFileName(),
                boardManager, this);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        Intent tmp = new Intent(this, MatchingCardsGameActivity.class);
        saveCurrentBoardManager();
        startActivity(tmp);
    }
}
