package fall2018.csc2017.GameCentre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A class for the per-user scoreboard
 */
public class PerUserScoreboardActivity extends AppCompatActivity {

    /**
     * The account manager
     */
    private AccountManager accountManager;
    /**
     * The scores to display
     */
    private String[] topScoresToDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_user_scoreboard);

        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);

        topScoresToDisplay = accountManager.displayPerUser
                (accountManager.getCurrentAccount().getUsername(),
                        accountManager.getCurrentAccount().getGamePlayedId());



        // Listview object linked to our xml
        ListView scores = (ListView)findViewById(R.id.user_scores);

        // Define an adapter

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        topScoresToDisplay);

        // Set the adapter
        scores.setAdapter(adapter);

    }

}
