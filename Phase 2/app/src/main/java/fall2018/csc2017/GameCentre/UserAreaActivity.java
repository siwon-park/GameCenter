package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.GameCentre.Game.StartingActivity;
import fall2018.csc2017.GameCentre.MatchingCards.MatchingCardsBoardManager;
import fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesBoardManager;
import fall2018.csc2017.GameCentre.Sudoku.SudokuBoardManager;

public class UserAreaActivity extends AppCompatActivity {

    /**
     * The account manager.
     */
    private AccountManager accountManager;

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userarea);
        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
        if (accountManager == null) {
            accountManager = new AccountManager();
            LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        }
        addSlidingTilesButtonListener();
        addMatchingCardsButtonListener();
        addSudokuButtonListener();
        TextView textView = (TextView) findViewById(R.id.tvName);
        textView.setText("Welcome " + accountManager.getCurrentAccount().getName() + ",");
    }

    /**
     * Activate the Sliding Tiles game button.
     */
    private void addSlidingTilesButtonListener() {
        Button SlidingTilesButton = findViewById(R.id.SlidingTilesButton);
        SlidingTilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO get gameID from SlidingTiles.board
                accountManager.getCurrentAccount().setGamePlayedId(BoardManager.SLIDING_TILES_GAME);
                boardManager = new SlidingTilesBoardManager();
                switchToGame(StartingActivity.class);
            }
        });
    }

    /**
     * Activate the Matching Cards game button.
     */
    private void addMatchingCardsButtonListener() {
        Button MatchingCardsButton = findViewById(R.id.MatchingCardsButton);
        MatchingCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO get gameID from MatchingTiles.board
                accountManager.getCurrentAccount().setGamePlayedId(BoardManager.MATCHING_CARDS_GAME);
                boardManager = new MatchingCardsBoardManager();
                switchToGame(StartingActivity.class);
            }
        });
    }

    /**
     * Activate the Sudoku game button.
     */
    private void addSudokuButtonListener() {
        Button SudokuButton = findViewById(R.id.SudokuButton);
        SudokuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountManager.getCurrentAccount().setGamePlayedId(BoardManager.SUDOKU_GAME);
                boardManager = new SudokuBoardManager();
                switchToSudoku(StartingActivity.class);
            }
        });
    }

    /**
     * Switch to StartingActivity.
     * @param gameStartingActivity the game starting activity.
     */
    private void switchToGame(Class gameStartingActivity) {
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        // save boardManager so that we know which game is being played
        LoadAndSave.saveToFile(
                accountManager.getCurrentAccount().getCurrentGameFileName(), boardManager, this);
        // Todo: replace gameStartingActivity
        Intent tmp = new Intent(this, gameStartingActivity);
        startActivity(tmp);
    }

    /**
     * Switch to StartingActivity.
     * @param gameStartingActivity the game starting activity.
     */
    private void switchToSudoku(Class gameStartingActivity) {
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        // save boardManager so that we know which game is being played
        LoadAndSave.saveToFile(
                accountManager.getCurrentAccount().getCurrentGameFileName(), boardManager, this);
        // Todo: replace gameStartingActivity
        Intent tmp = new Intent(this, gameStartingActivity);
        startActivity(tmp);
    }
}
