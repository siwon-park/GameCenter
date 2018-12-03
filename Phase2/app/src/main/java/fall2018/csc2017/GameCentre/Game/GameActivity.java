package fall2018.csc2017.GameCentre.Game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.GameCentre.AccountManager;
import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.CustomAdapter;
import fall2018.csc2017.GameCentre.LoadAndSave;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveFile;
import fall2018.csc2017.GameCentre.UserAreaActivity;

/**
 * The game activity.
 */
abstract public class GameActivity extends AppCompatActivity implements Observer {

    /**
     * The board manager.
     */
    protected BoardManager boardManager;

    /**
     * The buttons to display.
     */
    protected ArrayList<Button> tileButtons;

    /**
     * Grid View and calculated column height and width based on device size
     */
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;
    private AccountManager accountManager;
    private SaveFile saveFile;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
        if (accountManager == null) {
            accountManager = new AccountManager();
            LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        }

        loadCurrentBoardManager();
        Board.NUM_COLS = boardManager.getSavedNumCols();
        Board.NUM_ROWS = boardManager.getSavedNumRows();
        boardManager.setStartingScoreAndTime();
        accountManager.getCurrentAccount().setGamePlayed(true);
        saveFile = (SaveFile) LoadAndSave.loadFromFile(accountManager.getCurrentAccount().getSavedGameFileName(), this);
        if (saveFile == null) {
            saveFile = new SaveFile();
            LoadAndSave.saveToFile(
                    accountManager.getCurrentAccount()
                            .getSavedGameFileName(), saveFile, this);
        }

        createTileButtons(this);
        switch (boardManager.getGameName()) {
            case BoardManager.SLIDING_TILES_GAME:
                setContentView(R.layout.activity_slidingtile);
                break;
            case BoardManager.MATCHING_CARDS_GAME:
                setContentView(R.layout.activity_matchingcards);
                break;
            default:
                setContentView(R.layout.activity_sudoku);
        }
        addUndoButtonListener();
        addSaveButtonListener();
        addSudoku1Listener();
        addSudoku2Listener();
        addSudoku3Listener();
        addSudoku4Listener();
        addSudoku5Listener();
        addSudoku6Listener();
        addSudoku7Listener();
        addSudoku8Listener();
        addSudoku9Listener();

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(Board.NUM_COLS);
        gridView.setBoardManager(boardManager);
        gridView.setAccountManager(accountManager);
        boardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / Board.NUM_COLS;
                        columnHeight = displayHeight / Board.NUM_ROWS;

                        display();
                    }
                });
    }


    /**
     * Activate the undo button.
     */
    abstract protected void addUndoButtonListener();

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBoardManager();
                boardManager.setSaved(true);
                makeToastSavedText();
            }
        });
    }

    /**
     * Save the board manager as a serializable object
     */
    private void saveBoardManager() {
        saveFile.addSave(boardManager);
        LoadAndSave.saveToFile(accountManager.getCurrentAccount().getSavedGameFileName(
               ), saveFile, this);
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    abstract protected void createTileButtons(Context context);

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    abstract protected void updateTileButtons();

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        boardManager.updateScore();
        saveCurrentBoardManager();
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
    }

    /**
     * Dispatch onResume() to fragments.
     */
    @Override
    protected void onResume() {
        super.onResume();
        boardManager.updateStartTime();
    }

    /**
     * display updates whenever board updates
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        display();
        autoSave();
        if(boardManager.puzzleSolved()){
            returnToMain();
        }
    }

    /**
     * Save the current game
     */
    private void saveCurrentBoardManager() {
        LoadAndSave.saveToFile(accountManager.getCurrentAccount().getCurrentGameFileName(),
                boardManager, this);
    }

    /**
     * Loads current saved game
     */
    private void loadCurrentBoardManager() {
        boardManager = (BoardManager) LoadAndSave.loadFromFile(
                accountManager.getCurrentAccount().getCurrentGameFileName(), this);
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Autosave that saves after every move
     */
    private void autoSave() {
        saveCurrentBoardManager();
    }

    /**
     * Returns to main user area screen
     */
    private void returnToMain(){
        Intent next = new Intent(this, UserAreaActivity.class);
        startActivity(next);
    }

    protected void addSudoku1Listener() {}
    protected void addSudoku2Listener() {}
    protected void addSudoku3Listener() {}
    protected void addSudoku4Listener() {}
    protected void addSudoku5Listener() {}
    protected void addSudoku6Listener() {}
    protected void addSudoku7Listener() {}
    protected void addSudoku8Listener() {}
    protected void addSudoku9Listener() {}

}
