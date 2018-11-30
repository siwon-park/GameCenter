package fall2018.csc2017.GameCentre.MatchingCards;

import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Stack;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.Game.GameActivity;
import fall2018.csc2017.GameCentre.R;

/**
 * The game activity.
 */
public class MatchingCardsGameActivity extends GameActivity implements Observer {
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
//        Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(R.drawable.meme);
                this.tileButtons.add(tmp);
            }
        }

    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    @Override
    protected void updateTileButtons() {
        MatchingCardsBoard board = (MatchingCardsBoard) boardManager.getBoard();
        int nextPos = 0;

        if (board.getFlipInProgress()) {
            Stack<int[]> toBeFlipped = board.getLastClicks();
            for (Button b : tileButtons) {
                int row = nextPos / Board.NUM_ROWS;
                int col = nextPos % Board.NUM_COLS;
                if (board.getTile(row, col).getBackground() != R.drawable.tile_blank) {
                    if (needToFlip(row, col)) {
                        b.setBackgroundResource(board.getTile(row, col).getBackground());
                    }else {
                        b.setBackgroundResource(R.drawable.meme);
                    }
                } else {
                    b.setBackgroundResource(R.drawable.tile_blank);
                }
                nextPos++;
            }
        } else {
            for (Button b : tileButtons) {
                int row = nextPos / Board.NUM_ROWS;
                int col = nextPos % Board.NUM_COLS;
                if (board.getTile(row, col).getBackground() != R.drawable.tile_blank) {
                    b.setBackgroundResource(R.drawable.meme);
                }else {
                    b.setBackgroundResource(R.drawable.tile_blank);
                }
                nextPos++;
            }
        }
    }

    private boolean needToFlip(int row, int col) {
        Stack<?> toBeFlipped = (Stack<?>) ((MatchingCardsBoardManager)boardManager).getLastClicks().clone();
        while (!toBeFlipped.empty()) {
            int[] temp = (int[]) toBeFlipped.pop();
            int rowOfFlip = temp[0];
            int colOfFlip = temp[1];
            if (row == rowOfFlip && col == colOfFlip) {
                return true;
            }
        }
        return false;
    }

}
