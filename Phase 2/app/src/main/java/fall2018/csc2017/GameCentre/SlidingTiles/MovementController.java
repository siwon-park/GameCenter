package fall2018.csc2017.GameCentre.SlidingTiles;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.GameCentre.AccountManager;


public class MovementController {
    /**
     * A board manager that inherits methods from BoardManager
     */
    private BoardManager boardManager = null;
    /**
     * An account manager that inherits methods from AccountManager
     */
    private AccountManager accountManager = null;

    /**
     * A default movement controller constructor
     */
    public MovementController() {
    }

    /**
     * Setter method for BoardManager
     * @param boardManager
     */
    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Setter method for AccountManager
     * @param accountManager
     */
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Processes tile movement during game play and displays win or invalid messages if needed
     * @param context
     * @param position
     * @param display
     */
    public void processTapMovement(Context context, int position, boolean display) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                Integer score = boardManager.getScore();
                accountManager.getCurrentAccount().setScore(score);
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
