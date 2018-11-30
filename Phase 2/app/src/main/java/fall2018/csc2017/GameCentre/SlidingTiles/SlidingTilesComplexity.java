package fall2018.csc2017.GameCentre.SlidingTiles;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.Game.ComplexityActivity;
import fall2018.csc2017.GameCentre.Game.ImageActivity;
import fall2018.csc2017.GameCentre.LoadAndSave;
import fall2018.csc2017.GameCentre.R;

public class SlidingTilesComplexity extends ComplexityActivity {

    /**
     * Activate the 3x3 button.
     */
    @Override
    protected void addButton3Listener() {
        Button startButton = findViewById(R.id.button3);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.NUM_ROWS = 3;
                Board.NUM_COLS = 3;
                boardManager.createBoard();
                boardManager.setSavedNumRows(3);
                boardManager.setSavedNumCols(3);
                switchToBackgroundChange();
            }
        });
    }

    /**
     * Activate the 4x4 button.
     */
    @Override
    protected void addButton4Listener() {
        Button startButton = findViewById(R.id.button4);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.NUM_ROWS = 4;
                Board.NUM_COLS = 4;
                boardManager.createBoard();
                boardManager.setSavedNumCols(4);
                boardManager.setSavedNumRows(4);
                switchToBackgroundChange();
            }
        });
    }

    /**
     * Activate the 5x5 button.
     */
    @Override
    protected void addButton5Listener() {
        Button startButton = findViewById(R.id.button5);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.NUM_ROWS = 5;
                Board.NUM_COLS = 5;
                boardManager.createBoard();
                boardManager.setSavedNumRows(5);
                boardManager.setSavedNumCols(5);
                switchToBackgroundChange();
            }
        });
    }

    /**
     * An empty function for 6x6 function because there is no such complexity for this game
     */
    @Override
    protected void addButton6Listener() {

    }

    /**
     * Switch to the ImageActivity view to play the game.
     */
    private void switchToBackgroundChange() {
        Intent tmp = new Intent(this, ImageActivity.class);
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        saveCurrentBoardManager();
        startActivity(tmp);
    }

}
