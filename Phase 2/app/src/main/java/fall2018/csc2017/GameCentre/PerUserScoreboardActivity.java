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
    /**
     * String user searched
     */
    private static String UserSearched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_user_scoreboard);
        addSearchButtonListener();

        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
        if (UserSearched==null) {
            topScoresToDisplay = accountManager.displayPerUser
                    (accountManager.getCurrentAccount().getName(),
                            accountManager.getCurrentAccount().getGamePlayedId());

        }
        else{
            topScoresToDisplay = accountManager.displayPerUser
                    (UserSearched,
                            accountManager.getCurrentAccount().getGamePlayedId());
        }

        // Listview object linked to our xml
        ListView scores = (ListView)findViewById(R.id.user_scores);

        // Define an adapter

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        topScoresToDisplay);

        // Set the adapter
        scores.setAdapter(adapter);

    }
    /**
     * Activate the Search button.
     */
    private void addSearchButtonListener() {
        Button searchButton = findViewById(R.id.searchButton2);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText etUsername = findViewById(R.id.etUsername3);
                String username = etUsername.getText().toString();

                if (accountManager.getAccount(username) == null) {
                    makeToastUserNotRegistered();
                }
                else{
                    PerUserScoreboardActivity.setUserSearched(username);
                    switchToPerUserScoreboard();
                }
            }
        });
    }

    /**
     * Switch to the UserSearch activity view
     */
    private void switchToPerUserScoreboard() {
        Intent tmp = new Intent(this, PerUserScoreboardActivity.class);
        startActivity(tmp);
    }

    /**
     * Display that a user inputted in the search is not registered.
     */
    private void makeToastUserNotRegistered() {
        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets the user searched to input
     * @param name
     */
    public static void setUserSearched(String name){
        PerUserScoreboardActivity.UserSearched = name;
    }
}
