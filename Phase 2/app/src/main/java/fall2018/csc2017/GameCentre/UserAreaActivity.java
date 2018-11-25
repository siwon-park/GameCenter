package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


//import fall2018.csc2017.GameCentre.SlidingTiles.StartingActivity;

public class UserAreaActivity extends AppCompatActivity {

    /**
     * The account manager.
     */
    private AccountManager accountManager;

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
        TextView textView = (TextView) findViewById(R.id.tvName);
        textView.setText("Welcome " + accountManager.getCurrentAccount().getName() + ",");
    }

    private void addSlidingTilesButtonListener() {
        Button SlidingTilesButton = findViewById(R.id.SlidingTilesButton);
        SlidingTilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(fall2018.csc2017.GameCentre.SlidingTiles.StartingActivity.class);
            }
        });
    }
    private void addMatchingCardsButtonListener() {
        Button MatchingCardsButton = findViewById(R.id.MatchingCardsButton);
        MatchingCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(fall2018.csc2017.GameCentre.MatchingCards.GameActivity.class);
            }
        });
    }
   /* private void addWhackAMoleButtonListener() {
        Button WhackAMoleButton = findViewById(R.id.WhackAMoleButton);
        WhackAMoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToWhackAMole();
            }
        });
    }*/

    private void switchToGame(Class gameStartingActivity) {
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        Intent tmp = new Intent(this, gameStartingActivity);
        startActivity(tmp);
    }
}
