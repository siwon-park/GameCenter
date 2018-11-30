package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.io.Serializable;

import fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesBoard;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class Tile implements Comparable<Tile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * The unique id.
     */
    private int id;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets new Id
     * @param newId the Id to be set
     */
    public void setID(int newId) {
        this.id = newId;
    }

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    public Tile(int id, int background) {
        this.id = id;
        this.background = background;
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId
     */
    public Tile(int backgroundId) {
        id = backgroundId + 1;
        if (isBlank()) {
            background = R.drawable.tile_blank;
            return;
        }
        switch (backgroundId + 1) {
            case 1:
                background = R.drawable.tile_1;
                break;
            case 2:
                background = R.drawable.tile_2;
                break;
            case 3:
                background = R.drawable.tile_3;
                break;
            case 4:
                background = R.drawable.tile_4;
                break;
            case 5:
                background = R.drawable.tile_5;
                break;
            case 6:
                background = R.drawable.tile_6;
                break;
            case 7:
                background = R.drawable.tile_7;
                break;
            case 8:
                background = R.drawable.tile_8;
                break;
            case 9:
                background = R.drawable.tile_9;
                break;
            case 10:
                background = R.drawable.tile_10;
                break;
            case 11:
                background = R.drawable.tile_11;
                break;
            case 12:
                background = R.drawable.tile_12;
                break;
            case 13:
                background = R.drawable.tile_13;
                break;
            case 14:
                background = R.drawable.tile_14;
                break;
            case 15:
                background = R.drawable.tile_15;
                break;
            case 16:
                background = R.drawable.tile_16;
                break;
            case 17:
                background = R.drawable.tile_17;
                break;
            case 18:
                background = R.drawable.tile_18;
                break;
            case 19:
                background = R.drawable.tile_19;
                break;
            case 20:
                background = R.drawable.tile_20;
                break;
            case 21:
                background = R.drawable.tile_21;
                break;
            case 22:
                background = R.drawable.tile_22;
                break;
            case 23:
                background = R.drawable.tile_23;
                break;
            case 24:
                background = R.drawable.tile_24;
                break;
            case 25:
                background = R.drawable.tile_blank;
                break;
            case 26:
                background = R.drawable.tile_1_s;
                break;
            case 27:
                background = R.drawable.tile_2_s;
                break;
            case 28:
                background = R.drawable.tile_3_s;
                break;
            case 29:
                background = R.drawable.tile_4_s;
                break;
            case 30:
                background = R.drawable.tile_5_s;
                break;
            case 31:
                background = R.drawable.tile_6_s;
                break;
            case 32:
                background = R.drawable.tile_7_s;
                break;
            case 33:
                background = R.drawable.tile_8_s;
                break;
            case 34:
                background = R.drawable.tile_9_s;
                break;
            case 35:
                background = R.drawable.tile_1_user_s;
                break;
            case 36:
                background = R.drawable.tile_2_user_s;
                break;
            case 37:
                background = R.drawable.tile_3_user_s;
                break;
            case 38:
                background = R.drawable.tile_4_user_s;
                break;
            case 39:
                background = R.drawable.tile_5_user_s;
                break;
            case 40:
                background = R.drawable.tile_6_user_s;
                break;
            case 41:
                background = R.drawable.tile_7_user_s;
                break;
            case 42:
                background = R.drawable.tile_8_user_s;
                break;
            case 43:
                background = R.drawable.tile_9_user_s;
                break;
            case 44:
                background = R.drawable.blank_tile_s;
                break;
            default:
                background = R.drawable.tile_blank;
        }
    }

    /**
     * Checks if tile is blank
     * @return whether or not tile is blank
     */
    public boolean isBlank() {
        return id == (SlidingTilesBoard.NUM_COLS * SlidingTilesBoard.NUM_ROWS);
    }

    /**
     * Comparable for comparing tile ids
     * @param o
     * @return
     */
    @Override
    public int compareTo(@NonNull Tile o) {
        return o.id - this.id;
    }
}
