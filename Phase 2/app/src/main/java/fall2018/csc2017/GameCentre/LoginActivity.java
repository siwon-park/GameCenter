package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * Login activity
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * An account manager that inherits methods from AccountManager class
     */
    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addRegisterHereButtonListener();
        addSignInButtonListener();
        accountManager = (AccountManager) LoadAndSave.loadFromFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
        if (accountManager == null) {
            accountManager = new AccountManager();
            LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        }
    }

    /**
     * Activate the Register Here button.
     */
    private void addRegisterHereButtonListener() {
        Button registerHereButton = findViewById(R.id.bRegisterHere);
        registerHereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegister();
            }
        });
    }

    /**
     * Activate the Sign In button.
     */
    private void addSignInButtonListener() {
        Button signInButton = findViewById(R.id.bLogin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText etUsername = findViewById(R.id.etUsername);
                final EditText etPassword = findViewById(R.id.etPassword);
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (accountManager.getAccount(username) == null) {
                    makeToastUserNotRegistered();
                    return;
                }
                boolean isMatch = accountManager.getAccount(username).matchPassword(password);
                if (isMatch) {
                    accountManager.setCurrentAccount(accountManager.getAccount(username));
                    switchToUserArea();
                } else {
                    makeToastIncorrectPassword();
                }
            }
        });
    }

    /**
     * Display that a user inputted at login is not registered.
     */
    private void makeToastUserNotRegistered() {
        Toast.makeText(this, "User Not Registered", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that the password inputted at login is incorrect.
     */
    private void makeToastIncorrectPassword() {
        Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the RegisterActivity view to play the game.
     */
    private void switchToRegister() {
        Intent tmp = new Intent(this, RegisterActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the SlidingTilesStartingActivity view to play the game.
     */
    private void switchToUserArea() {
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        Intent tmp = new Intent(this, UserAreaActivity.class);
        startActivity(tmp);
    }
}
