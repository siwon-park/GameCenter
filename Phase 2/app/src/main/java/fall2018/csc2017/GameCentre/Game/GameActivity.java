//package fall2018.csc2017.GameCentre.Game;
//
//import android.widget.Button;
//
//import java.util.ArrayList;
//
//import fall2018.csc2017.GameCentre.AccountManager;
//import fall2018.csc2017.GameCentre.BoardManager;
//import fall2018.csc2017.GameCentre.LoadAndSave;
//
//public class GameActivity {
//    /**
//     * The board manager.
//     */
//    private BoardManager boardManager;
//
//    /**
//     * The buttons to display.
//     */
//    private ArrayList<Button> tileButtons;
//
//    /**
//     * Grid View and calculated column height and width based on device size
//     */
//    private GestureDetectGridView gridView;
//    private static int columnWidth, columnHeight;
//    private AccountManager accountManager;
//
//    protected void onPause() {
//        boardManager.updateScore();
//        saveCurrentBoardManager();
//        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
//    }
//}
