package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
        addSearchButtonListener();

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

    /**
     * Activate the Search button.
     */
    private void addSearchButtonListener() {
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText etUsername = findViewById(R.id.etUsername2);
                String username = etUsername.getText().toString();

                if (accountManager.getAccount(username) == null) {
                    makeToastUserNotRegistered();
                }
                else{
                    UserSearchActivity.setUserSearched(username);
                    switchToUserSearch();
                }
            }
        });
    }

    /**
     * Switch to the UserSearch activity view
     */
    private void switchToUserSearch() {
        Intent tmp = new Intent(this, UserSearchActivity.class);
        startActivity(tmp);
    }

    /**
     * Display that a user inputted in the search is not registered.
     */
    private void makeToastUserNotRegistered() {
        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show();
    }

}
