package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.HashMap;

import fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesBoard;

/**
 * Manage the images that are used as background of tiles, including cropping, scaling and splitting.
 */

public class BackgroundManager {

    /**
     * HashMap for mapping tile ID and the drawable used as its skin.
     */
    private HashMap<Integer, Drawable> backgrdTileMap;

    /**
     * Dimensions of the grids of tiles
     */
    private int gridWidth, gridHeight;

    /**
     * Context of the activity which calls the constructor
     */
    private Context context;

    /**
     * Create a manager and split the background image
     * @param context
     */
    public BackgroundManager(Context context) {
        this.context = context;
        this.gridWidth = 967;
        this.gridHeight = 1255;
        this.backgrdTileMap = new HashMap<>();
        imageSplitter();
    }

    /**
     * Scale the background image to fit the tile grid.
     *
     * @return a Bitmap of the scaled image.
     */
    private Bitmap scaledBackground(){
        Bitmap scaledBitMap;
        Bitmap uncroppedBMap;
        uncroppedBMap = SlidingTilesBoard.BACKGROUND_BMAP;

        double widthRatio = uncroppedBMap.getWidth() / (double)gridWidth;
        double heightRatio = uncroppedBMap.getHeight() / (double) gridHeight;

            if (heightRatio > widthRatio) {
                int scaledUpHeight = (int) Math.ceil(uncroppedBMap.getHeight() / widthRatio);
                scaledBitMap = Bitmap.createScaledBitmap(uncroppedBMap, gridWidth, scaledUpHeight, true);
            } else {
                int scaledUpWidth = (int) Math.ceil(uncroppedBMap.getWidth() / heightRatio);
                scaledBitMap = Bitmap.createScaledBitmap(uncroppedBMap, scaledUpWidth, gridHeight, true);
            }
        return scaledBitMap;
    }

    /**
     * Split the image to fit the tile size, add them to the HashMap
     */
    private void imageSplitter() {
        Bitmap backgrdBMap = scaledBackground();
        int tileWidth = gridWidth / SlidingTilesBoard.NUM_COLS;
        int tileHeight = gridHeight / SlidingTilesBoard.NUM_ROWS;
        int startX = (backgrdBMap.getWidth() - gridWidth) / 2;
        int startY = (backgrdBMap.getHeight() - gridHeight) / 2;
        Bitmap croppedBackgrd = Bitmap.createBitmap(backgrdBMap, startX, startY, gridWidth, gridHeight);
        for (int i = 0; i < SlidingTilesBoard.NUM_ROWS; i++) {
            for (int j = 0; j < SlidingTilesBoard.NUM_COLS; j++) {
                    int tileId = SlidingTilesBoard.NUM_ROWS * i + j + 1;
                    Bitmap tileBMap = Bitmap.createBitmap(croppedBackgrd, tileWidth * j, tileHeight * i, tileWidth, tileHeight);
                    Drawable tileDrawable = new BitmapDrawable(context.getResources(), tileBMap);

                    backgrdTileMap.put(tileId, tileDrawable);

            }
        }
    }

    /**
     * Return the HashMap.
     *
     * @return the HashMap.
     */
    public HashMap<Integer, Drawable> getbackgrdTileList() {
        return backgrdTileMap;
    }

}
