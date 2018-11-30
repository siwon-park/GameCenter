package fall2018.csc2017.GameCentre.Sudoku;

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
        SudokuBoard board = (SudokuBoard) boardManager.getBoard();
        int nextPos = 0;

        for (Button b : tileButtons) {
            int row = nextPos / Board.NUM_ROWS;
            int col = nextPos % Board.NUM_COLS;
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }
    protected void addSudoku1Listener() {
        Button sudoku1 = findViewById(R.id.sudoku1);
        sudoku1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              SudokuBoard board = (SudokuBoard) boardManager.getBoard();
              board.deselect(1);
            }
        });
    }

    protected void addSudoku2Listener() {
        Button sudoku2 = findViewById(R.id.sudoku2);
        sudoku2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SudokuBoard board = (SudokuBoard) boardManager.getBoard();
                board.deselect(2);
            }
        });
    }

    protected void addSudoku3Listener() {
        Button sudoku3 = findViewById(R.id.sudoku3);
        sudoku3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SudokuBoard board = (SudokuBoard) boardManager.getBoard();
                board.deselect(3);
            }
        });
    }

    protected void addSudoku4Listener() {
        Button sudoku4 = findViewById(R.id.sudoku4);
        sudoku4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SudokuBoard board = (SudokuBoard) boardManager.getBoard();
                board.deselect(4);
            }
        });
    }


    protected void addSudoku5Listener() {
        Button sudoku5 = findViewById(R.id.sudoku5);
        sudoku5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SudokuBoard board = (SudokuBoard) boardManager.getBoard();
                board.deselect(5);
            }
        });
    }


    protected void addSudoku6Listener() {
        Button sudoku6 = findViewById(R.id.sudoku6);
        sudoku6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SudokuBoard board = (SudokuBoard) boardManager.getBoard();
                board.deselect(6);
            }
        });
    }


    protected void addSudoku7Listener() {
        Button sudoku7 = findViewById(R.id.sudoku7);
        sudoku7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SudokuBoard board = (SudokuBoard) boardManager.getBoard();
                board.deselect(7);
            }
        });
    }

    protected void addSudoku8Listener() {
        Button sudoku8 = findViewById(R.id.sudoku8);
        sudoku8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SudokuBoard board = (SudokuBoard) boardManager.getBoard();
                board.deselect(8);
            }
        });
    }
    protected void addSudoku9Listener() {
        Button sudoku9 = findViewById(R.id.sudoku9);
        sudoku9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SudokuBoard board = (SudokuBoard) boardManager.getBoard();
                board.deselect(9);
            }
        });
    }
}


