package fall2018.csc2017.GameCentre.MatchingCards;

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
import java.util.Stack;

import fall2018.csc2017.GameCentre.AccountManager;
import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.CustomAdapter;
import fall2018.csc2017.GameCentre.Game.GestureDetectGridView;
import fall2018.csc2017.GameCentre.LoadAndSave;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.UserAreaActivity;

/**
 * The game activity.
 */
public class GameActivity extends AppCompatActivity implements Observer {

    /**
     * The board manager.
     */
    // Todo: Need remove this after we fix onCreate()
    private BoardManager boardManager = new MatchingCardsBoardManager();

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

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));

    }

    /*TODO:     Change account manager structure
      TODO:     Account manager needs to differentiate the save files of the 3 games
      TODO:     SlidingTilesBoard manager classes of the 3 games are slightly different
      TODO:     The original code can't tell which game the saved board manager is for
      TODO:     And therefore try to cast one into another and cause error
      TODO:     Uncomment the lines to allow save function after fixing account manager
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        accountManager = (AccountManager) LoadAndSave.loadFromFile(
//                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
//        if (accountManager == null) {
//            accountManager = new AccountManager();
//            LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
//        }
//        loadCurrentBoardManager();


        Board.NUM_COLS = boardManager.getSavedNumCols();
        Board.NUM_ROWS = boardManager.getSavedNumRows();
        boardManager.setStartingScoreAndTime();

//        accountManager.getCurrentAccount().setGamePlayed(true);

        createTileButtons(this);
        setContentView(R.layout.activity_matchingcards);
        addSaveButtonListener();

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(Board.NUM_COLS);
        gridView.setBoardManager(boardManager);

//        gridView.setAccountManager(accountManager);

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
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBoardManager();
                accountManager.getCurrentAccount()
                        .setSaved(true, boardManager.getGameName());
                makeToastSavedText();
            }
        });
    }

    /**
     * Save the board manager as a serializable object
     */
    private void saveBoardManager() {
        LoadAndSave.saveToFile(accountManager.getCurrentAccount().getSavedGameFileName(
                boardManager.getGameName()), boardManager, this);
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
//        SlidingTilesBoard board = (SlidingTilesBoard) boardManager.getBoard();
        tileButtons = new ArrayList<>();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(R.drawable.meme);
                this.tileButtons.add(tmp);
            }
        }

//        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        MatchingCardsBoard board = getBoard();
        int nextPos = 0;

        if (board.getFlipInProgress()) {
            Stack<int[]> toBeFlipped = board.getLastClicks();
            for (Button b : tileButtons) {
                int row = nextPos / Board.NUM_ROWS;
                int col = nextPos % Board.NUM_COLS;
                if (board.getTile(row, col).getBackground() != R.drawable.tile_blank) {
                    if (needToFlip(row, col)) {
                        b.setBackgroundResource(board.getTile(row, col).getBackground());
                    }else {
                        b.setBackgroundResource(R.drawable.meme);
                    }
                } else {
                    b.setBackgroundResource(R.drawable.tile_blank);
                }
                nextPos++;
            }
        }else {
            for (Button b : tileButtons) {
                int row = nextPos / Board.NUM_ROWS;
                int col = nextPos % Board.NUM_COLS;
                if (board.getTile(row, col).getBackground() != R.drawable.tile_blank) {
                    b.setBackgroundResource(R.drawable.meme);
                }else {
                    b.setBackgroundResource(R.drawable.tile_blank);
                }
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
    //TODO: add the autosave back

    @Override
    public void update(Observable o, Object arg) {
        display();
//        autoSave();
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
        boardManager = (MatchingCardsBoardManager) LoadAndSave.loadFromFile(
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
        Intent next = new Intent(this, UserAreaActivity.class);
        startActivity(next);
    }

    private boolean needToFlip(int row, int col) {
        Stack<?> toBeFlipped = (Stack<?>) (getBoard().getLastClicks().clone());
        while (!toBeFlipped.empty()) {
            int[] temp = (int[]) toBeFlipped.pop();
            int rowOfFlip = temp[0];
            int colOfFlip = temp[1];
            if (row == rowOfFlip && col == colOfFlip) {
                return true;
            }
        }
        return false;
    }

    private MatchingCardsBoard getBoard() {
        return (MatchingCardsBoard) boardManager.getBoard();
    }

}
