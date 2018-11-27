package fall2018.csc2017.GameCentre.SlidingTiles;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.GameCentre.AccountManager;
import fall2018.csc2017.GameCentre.BackgroundManager;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.CustomAdapter;
//import fall2018.csc2017.GameCentre.MatchingCards.GestureDetectGridView;
import fall2018.csc2017.GameCentre.LoadAndSave;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveFile;

/**
 * The game activity.
 */
public class GameActivity extends AppCompatActivity implements Observer {

    /**
     * The board manager.
     */
    private SlidingTilesBoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

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
        if (saveFile == null) {
            saveFile = new SaveFile();
            LoadAndSave.saveToFile(accountManager.getCurrentAccount().getSavedGameFileName(), saveFile, this);
        }
        loadCurrentBoardManager();
        SlidingTilesBoard.NUM_COLS = boardManager.getSavedNumCols();
        SlidingTilesBoard.NUM_ROWS = boardManager.getSavedNumRows();
        boardManager.setStartingScoreAndTime();
        accountManager.getCurrentAccount().setGamePlayed(true);
        createTileButtons(this);
        setContentView(R.layout.activity_slidingtile);
        addUndoButtonListener();
        addSaveButtonListener();

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(SlidingTilesBoard.NUM_COLS);
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

                        columnWidth = displayWidth / SlidingTilesBoard.NUM_COLS;
                        columnHeight = displayHeight / SlidingTilesBoard.NUM_ROWS;

                        display();
                    }
                });
    }


    /**
     * Activate the undo button.
     */
    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.UndoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.undoMove();
            }
        });
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBoardManager();
                accountManager.getCurrentAccount().setSaved(true);
                makeToastSavedText();
            }
        });
    }

    /**
     * Save the board manager as a serializable object
     */
    private void saveBoardManager() {
        saveFile.addSave(boardManager);
        LoadAndSave.saveToFile(accountManager.getCurrentAccount().getSavedGameFileName(),
                saveFile, this);
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        SlidingTilesBoard board = (SlidingTilesBoard) boardManager.getBoard();
        tileButtons = new ArrayList<>();

        if (SlidingTilesBoard.BACKGROUND_BMAP != null) {
            BackgroundManager backgrdMgr = new BackgroundManager(this);
            HashMap backgroundIdMap = backgrdMgr.getbackgrdTileList();
            for (int row = 0; row != SlidingTilesBoard.NUM_ROWS; row++) {
                for (int col = 0; col != SlidingTilesBoard.NUM_COLS; col++) {
                    Button tmp = new Button(context);
                    int tileId = board.getTile(row, col).getId();
                    if (tileId != SlidingTilesBoard.NUM_COLS * SlidingTilesBoard.NUM_ROWS) {
                        Drawable backgrdDrawable = (Drawable) backgroundIdMap.get(tileId);
                        tmp.setBackground(backgrdDrawable);
                    } else {
                        tmp.setBackgroundResource(R.drawable.tile_blank);
                    }
                    this.tileButtons.add(tmp);
                }
            }
        } else {
            for (int row = 0; row != SlidingTilesBoard.NUM_ROWS; row++) {
                for (int col = 0; col != SlidingTilesBoard.NUM_COLS; col++) {
                    Button tmp = new Button(context);
                        tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                    this.tileButtons.add(tmp);
                }
            }

        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        // cast
        SlidingTilesBoard board = (SlidingTilesBoard) boardManager.getBoard();
        int nextPos = 0;

        if (SlidingTilesBoard.BACKGROUND_BMAP != null) {
            BackgroundManager backgrdMgr = new BackgroundManager(this);
            HashMap backgroundIdMap = backgrdMgr.getbackgrdTileList();
            for (Button b : tileButtons) {
                int row = nextPos / SlidingTilesBoard.NUM_ROWS;
                int col = nextPos % SlidingTilesBoard.NUM_COLS;
                int tileId = board.getTile(row, col).getId();
                if (tileId != SlidingTilesBoard.NUM_COLS * SlidingTilesBoard.NUM_ROWS) {
                    Drawable backgrdDrawable = (Drawable) backgroundIdMap.get(tileId);
                    b.setBackground(backgrdDrawable);
                } else {
                    b.setBackgroundResource(R.drawable.tile_blank);
                }
                nextPos++;
            }
        } else {
            for (Button b : tileButtons) {
                int row = nextPos / SlidingTilesBoard.NUM_ROWS;
                int col = nextPos % SlidingTilesBoard.NUM_COLS;
                b.setBackgroundResource(board.getTile(row, col).getBackground());
                nextPos++;
            }
        }
    }

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
        boardManager = (SlidingTilesBoardManager) LoadAndSave.loadFromFile(
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
     * Returns to main SlidingTiles screen
     */
    private void returnToMain(){
        Intent next = new Intent(this, StartingActivity.class);
        startActivity(next);
    }
}
