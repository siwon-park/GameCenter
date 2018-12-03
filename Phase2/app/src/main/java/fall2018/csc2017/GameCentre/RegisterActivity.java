package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Register new account activity
 */
public class RegisterActivity extends AppCompatActivity {
    /**
     * The account manager.
     */
    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addRegisterButtonListener();
        accountManager = (AccountManager) LoadAndSave.loadFromFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
        if (accountManager == null) {
            accountManager = new AccountManager();
            LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        }
    }

    /**
     * Activate the Register button.
     */
    private void addRegisterButtonListener() {
        Button registerButton = findViewById(R.id.bRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText etName = findViewById(R.id.etName);
                final EditText etUsername = findViewById(R.id.etUsername);
                final EditText etPassword = findViewById(R.id.etPassword);
                if (accountManager.getAccount(etUsername.getText().toString()) != null) {
                    makeToastAccountAlreadyExists();
                    return;
                }
                if (etUsername.getText().toString().equals("") |
                        etPassword.getText().toString().equals("")) {
                    makeToastAccountNotFilled();
                    return;
                }
                Account account = new Account(
                        etName.getText().toString(),
                        etUsername.getText().toString(),
                        etPassword.getText().toString());
                accountManager.addAccount(account);
                switchToLogin();
            }
        });
    }

    /**
     * Display that a user inputted at login is not registered.
     */
    private void makeToastAccountAlreadyExists() {
        Toast.makeText(this, "Account Already Exists", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that a user that Password or User name has not been filled.
     */
    private void makeToastAccountNotFilled() {
        Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the LoginActivity view to play the game.
     */
    private void switchToLogin() {
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        Intent tmp = new Intent(this, LoginActivity.class);
        startActivity(tmp);
    }
}
