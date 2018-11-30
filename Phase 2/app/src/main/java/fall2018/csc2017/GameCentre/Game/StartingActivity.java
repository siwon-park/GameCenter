package fall2018.csc2017.GameCentre.Game;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fall2018.csc2017.GameCentre.AccountManager;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.Game.ComplexityActivity;
import fall2018.csc2017.GameCentre.LoadAndSave;
import fall2018.csc2017.GameCentre.LoginActivity;
import fall2018.csc2017.GameCentre.MatchingCards.MatchingCardsComplexity;
import fall2018.csc2017.GameCentre.PerUserScoreboardActivity;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.ScoreboardActivity;
import fall2018.csc2017.GameCentre.SaveFile;
import fall2018.csc2017.GameCentre.SlidingTiles.GameActivity;


/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {
    /**
     * The current board manager
     */
    private BoardManager boardManager;
    /**
     * The account manager.
     */
    private AccountManager accountManager;

    private SaveFile saveFile;

    private int gameID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
        if (accountManager == null) {
            accountManager = new AccountManager();
            LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        }
//        if (saveFile == null) {
//            saveFile = new SaveFile();
//            LoadAndSave.saveToFile(accountManager.getCurrentAccount().getSavedGameFileName(), saveFile, this);
//        }

        loadCurrentBoardManager();
        if (boardManager == null) {
            // This should not happen
            // Todo: log error or throw exception, or use Sliding Tile by default
        }

        switch (boardManager.getGameName()) {
            case BoardManager.SLIDING_TILES_GAME:
                setContentView(R.layout.activity_starting_sliding);
                break;
            case BoardManager.MATCHING_CARDS_GAME:
                setContentView(R.layout.activity_starting_matching);
                break;
            case BoardManager.SUDOKU_GAME:
                setContentView(R.layout.activity_starting_sudoku);
            default: setContentView(R.layout.activity_starting_sliding);
        }
        addStartButtonListener();
        addLoadButtonListener();
        addLoadPrevButtonListener();
        addPerUserScoreboardButtonListener();
        addSignOutButtonListener();
        addScoreboardButtonListener();
        addRateButtonListener();

    }

    /**
     * Switch to the complexity to choose a game.
     */
    private void switchToComplexity() {
        Intent tmp = new Intent(this, MatchingCardsComplexity.class);
        startActivity(tmp);
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boardManager = new BoardManager();
                switch (boardManager.getGameName()) {
                    case BoardManager.SLIDING_TILES_GAME:
                        selectBackground();
                        break;
                    case BoardManager.MATCHING_CARDS_GAME:
                        switchToComplexity();
                        break;
                    case BoardManager.SUDOKU_GAME:
                        switchToGame();
                        break;
                    default:
                        switchToGame();
                }
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        final Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!accountManager.getCurrentAccount().getSaved(boardManager.getGameName())) {
                    makeToastNoSaveFileText();
                } else {
                    loadBoardManager();
                    makeToastLoadedText();
                    switchToGame();
                }
            }
        });
    }

    /**
     * Activate the Load Previous button.
     */
    private void addLoadPrevButtonListener() {
        Button loadPrevButton = findViewById(R.id.LoadPrevButton);
        loadPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!accountManager.getCurrentAccount().getGamePlayed()) {
                    makeToastNoPrevGame();
                } else {
                    loadCurrentBoardManager();
                    makeToastLoadedText();
                    switchToGame();
                }
            }
        });
    }

    /**
     * Activate the sign out button.
     */
    private void addSignOutButtonListener() {
        final Button signOutButton = findViewById(R.id.SignOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountManager.setCurrentAccount(null);
                makeToastSignedOff();
                switchToLogin();
            }
        });
    }

    /**
     * Activate the scoreboard out button.
     */
    private void addScoreboardButtonListener() {
        Button scoreboardButton = findViewById(R.id.ScoreboardButton);
        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                switchToScoreboard();
          }
        });

    }

    /**
     * Activate the scoreboard out button.
     */
    private void addPerUserScoreboardButtonListener() {
        Button scoreboardButton = findViewById(R.id.bUserScoreboard);
        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToPerUserScoreboard();
            }
        });
    }

    /**
     * Load the current BoardManager.
     */
    private void loadBoardManager() {
        saveFile = (SaveFile) LoadAndSave.loadFromFile(
                accountManager.getCurrentAccount()
                        .getSavedGameFileName(boardManager.getGameName()), this);
        boardManager = saveFile.getBM(gameID);
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that the account has no save file.
     */
    private void makeToastNoSaveFileText() {
        Toast.makeText(this, "No save file exists", Toast.LENGTH_SHORT).show();
    }


    /**
     * Display that a user has signed out successfully.
     */
    private void makeToastSignedOff() {
        Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that there is no Prev game.
     */
    private void makeToastNoPrevGame() {
        Toast.makeText(this,"No previous game on record", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate the rate button.
     */
    private void addRateButtonListener() {
        Button startButton = findViewById(R.id.rate);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://play.google.com/store/apps/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadCurrentBoardManager();
        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        Intent tmp;
        switch (boardManager.getGameName()) {
            case BoardManager.SLIDING_TILES_GAME:
                tmp = new Intent(this, GameActivity.class);
                break;
            case BoardManager.MATCHING_CARDS_GAME:
                tmp = new Intent(this, fall2018.csc2017.GameCentre.MatchingCards.GameActivity.class);
                break;
            case BoardManager.SUDOKU_GAME:
                tmp = new Intent(this, fall2018.csc2017.GameCentre.Sudoku.GameActivity.class);
                break;

                default: tmp = new Intent(this, GameActivity.class);
        }
        saveCurrentBoardManager();
        startActivity(tmp);
    }

    /**
     * Switch to the LoginActivity view to log in.
     */

    private void switchToLogin() {
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        Intent tmp = new Intent(this, LoginActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the ScoreboardActivity view
     */
    private void switchToScoreboard() {
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        Intent tmp = new Intent(this, ScoreboardActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the perUserScoreboardActivity view
     */
    private void switchToPerUserScoreboard() {
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        Intent tmp = new Intent(this, PerUserScoreboardActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the select background page
     */
    private void selectBackground() {
        Intent tmp = new Intent(this, ComplexityActivity.class);
        startActivity(tmp);
    }

    /**
     * Saves the current BoardManager
     */
    private void saveCurrentBoardManager() {
        LoadAndSave.saveToFile(accountManager.getCurrentAccount().getCurrentGameFileName(),
                boardManager, this);
    }

    /**
     * Loads the current BoardManager
     */
    private void loadCurrentBoardManager() {
        boardManager = (BoardManager) LoadAndSave.loadFromFile(
                accountManager.getCurrentAccount().getCurrentGameFileName(), this);
    }
}
