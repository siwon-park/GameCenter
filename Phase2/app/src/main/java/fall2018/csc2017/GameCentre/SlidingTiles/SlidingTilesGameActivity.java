package fall2018.csc2017.GameCentre.SlidingTiles;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;

import fall2018.csc2017.GameCentre.BackgroundManager;
import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.Game.GameActivity;
import fall2018.csc2017.GameCentre.R;

/**
 * The game activity.
 */
public class SlidingTilesGameActivity extends GameActivity implements Observer {
    /**
     * Activate the undo button.
     */
    @Override
    protected void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.UndoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.undoMove();
            }
        });
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
     * Update the backgrounds on the buttons to match the tiles.
     */
    protected void updateTileButtons() {
        // cast
        SlidingTilesBoard board = (SlidingTilesBoard) boardManager.getBoard();
        int nextPos = 0;

        if (Board.BACKGROUND_BMAP != null) {
            BackgroundManager backgrdMgr = new BackgroundManager(this);
            HashMap backgroundIdMap = backgrdMgr.getbackgrdTileList();
            for (Button b : tileButtons) {
                int row = nextPos / Board.NUM_ROWS;
                int col = nextPos % Board.NUM_COLS;
                int tileId = board.getTile(row, col).getId();
                if (tileId != Board.NUM_COLS * Board.NUM_ROWS) {
                    Drawable backgrdDrawable = (Drawable) backgroundIdMap.get(tileId);
                    b.setBackground(backgrdDrawable);
                } else {
                    b.setBackgroundResource(R.drawable.tile_blank);
                }
                nextPos++;
            }
        } else {
            for (Button b : tileButtons) {
                int row = nextPos / Board.NUM_ROWS;
                int col = nextPos % Board.NUM_COLS;
                b.setBackgroundResource(board.getTile(row, col).getBackground());
                nextPos++;
            }
        }
    }
}
