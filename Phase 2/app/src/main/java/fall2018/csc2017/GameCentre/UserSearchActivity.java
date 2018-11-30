package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserSearchActivity extends AppCompatActivity {

    /**
     * The account manager
     */
    private AccountManager accountManager;
    /**
     * The scores to display
     */
    private String[] topScoresToDisplay;

    /**
     * The User to be searched
     */
    private static String UserSearched;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);

            topScoresToDisplay = accountManager.displayPerSearch
                    (UserSearched, accountManager.getCurrentAccount().getGamePlayedId());


        // Listview object linked to our xml
        ListView scores = (ListView) findViewById(R.id.user_search);

        // Define an adapter

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        topScoresToDisplay);

        // Set the adapter
        scores.setAdapter(adapter);
    }

    /**
     * set the user searched to given user
     * @param user the user to be searched
     */
    public static void setUserSearched(String user){
        UserSearchActivity.UserSearched = user;
    }

}
