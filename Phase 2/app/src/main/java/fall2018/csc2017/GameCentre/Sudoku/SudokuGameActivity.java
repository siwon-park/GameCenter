package fall2018.csc2017.GameCentre.Sudoku;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;

import fall2018.csc2017.GameCentre.BackgroundManager;
import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.Game.GameActivity;
import fall2018.csc2017.GameCentre.R;

public class SudokuGameActivity extends GameActivity implements Observer {
    public static boolean insert = false;

    /**
     * Create an empty function so it can build but this game doesn't have undo button.
     */
    @Override
    protected void addUndoButtonListener() {

    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    @Override
    protected void createTileButtons(Context context) {
        Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();

        if (Board.BACKGROUND_BMAP != null) {
            BackgroundManager backgrdMgr = new BackgroundManager(this);
            HashMap backgroundIdMap = backgrdMgr.getbackgrdTileList();
            for (int row = 0; row != Board.NUM_ROWS; row++) {
                for (int col = 0; col != Board.NUM_COLS; col++) {
                    Button tmp = new Button(context);
                    int tileId = board.getTile(row, col).getId();
                    if (tileId != Board.NUM_COLS * Board.NUM_ROWS) {
                        Drawable backgrdDrawable = (Drawable) backgroundIdMap.get(tileId);
                        tmp.setBackground(backgrdDrawable);
                    } else {
                        tmp.setBackgroundResource(R.drawable.tile_blank);
                    }
                    this.tileButtons.add(tmp);
                }
            }
        } else {
            for (int row = 0; row != Board.NUM_ROWS; row++) {
                for (int col = 0; col != Board.NUM_COLS; col++) {
                    Button tmp = new Button(context);
                    tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                    this.tileButtons.add(tmp);
                }
            }

        }
    }

    /**
     * Create an empty function so it can build but this game doesn't need to update tile buttons.
     */
    @Override
    protected void updateTileButtons() {
    }
}


