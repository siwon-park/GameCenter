package fall2018.csc2017.GameCentre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A class for the per-game leaderboard
 */
public class ScoreboardActivity extends AppCompatActivity {

    /**
     * the account manager
     */
    private AccountManager accountManager;
    /**
     * the scores to display
     */
    private String[] topScoresToDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);

        topScoresToDisplay = accountManager.displayPerGame
                (accountManager.getCurrentAccount().getGamePlayedId());



        //listview object linked to our xml
        ListView scores = (ListView)findViewById(R.id.scores);

        // Define an adapter

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        topScoresToDisplay);

        //set the adapter
        scores.setAdapter(adapter);

    }

}
