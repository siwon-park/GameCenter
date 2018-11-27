package fall2018.csc2017.GameCentre.SlidingTiles;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import fall2018.csc2017.GameCentre.AccountManager;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.ImageAdapter;
import fall2018.csc2017.GameCentre.LoadAndSave;
import fall2018.csc2017.GameCentre.R;

/**
 * The activity in which user is prompted to choose a background
 */
public class ImageActivity extends AppCompatActivity {

    /**
     * The request constant to verify response from gallery
     */
    public static final int IMAGE_GALLERY_REQUEST = 20;

    /**
     * The background image imported by user
     */
    private ImageView imgPicture;

    /**
     * The board manager.
     */
    private BoardManager boardManager;


    /**
     * The account manager.
     */
    private AccountManager accountManager;

    /**
     * References to our images.
     */
    private Integer[] backgroundImg = {
            R.drawable.toronto,
            R.drawable.cat, R.drawable.dog,
            R.drawable.meme,
    };

    /**
     * The gridview.
     */
    private GridView gridView;

    /**
     * Number of columns to separate images.
     */
    private int numColumn = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        addSkipButtonListener();
        addUseMyOwnButtonListener();
        imgPicture = (ImageView) findViewById(R.id.imgPicture);

        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
        if (accountManager == null) {
            accountManager = new AccountManager();
            LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        }
        loadCurrentBoardManager();


        gridView = findViewById(R.id.backgroundSelect);
        gridView.setNumColumns(numColumn);

        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
//                SlidingTilesBoard.TILE_BACKGROUND = Integer.toString(backgroundImg[position]);
                SlidingTilesBoard.BACKGROUND_BMAP = BitmapFactory.decodeResource(getResources(), backgroundImg[position]);

                switchToGame();
            }
        });
    }

    /**
     * Activate the skip button.
     */
    private void addSkipButtonListener() {
        Button startButton = findViewById(R.id.SkipButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlidingTilesBoard.BACKGROUND_BMAP = null;
                switchToGame();
            }
        });
    }

    /**
     * Activate the use my own button
     * @author discospiff from GitHub (https://github.com/discospiff/PlantPlaces15s305/blob/master/app/src/main/java/nw15s305/plantplaces/com/plantplaces15s305/ColorCaptureActivity.java)
     */
    private void addUseMyOwnButtonListener() {
        Button useMyOwnButton = findViewById(R.id.AddButton);
        useMyOwnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureDirectoryPath = pictureDirectory.getPath();
                Uri data = Uri.parse(pictureDirectoryPath);

                photoPickerIntent.setDataAndType(data, "image/*");

                startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
            }

        });
    }

    /**
     * Switch to the ComplexityActivity view to choose a complexity to play the game.
     */
    private void switchToGame() {
            if (boardManager == null) {
                boardManager = new BoardManager(BoardManager.SLIDING_TILES_GAME);
            }
            saveCurrentBoardManager();
            Intent tmp = new Intent(this, GameActivity.class);
            startActivity(tmp);
    }

    /**
     * Save the current BoardManager.
     */
    private void saveCurrentBoardManager() {
        LoadAndSave.saveToFile(accountManager.getCurrentAccount().getCurrentGameFileName(),
                boardManager, this);
    }

    /**
     * Load the current BoardManager.
     */
    private void loadCurrentBoardManager() {
        boardManager = (BoardManager) LoadAndSave.loadFromFile(
                accountManager.getCurrentAccount().getCurrentGameFileName(), this);
    }

    /**
     * @author discospiff from GitHub (https://github.com/discospiff/PlantPlaces15s305/blob/master/app/src/main/java/nw15s305/plantplaces/com/plantplaces15s305/ColorCaptureActivity.java)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            if (requestCode == IMAGE_GALLERY_REQUEST) {

                // the address of the image on the SD Card.
                Uri imageUri = data.getData();
                InputStream inputStream;

                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    Bitmap image = BitmapFactory.decodeStream(inputStream);

                    SlidingTilesBoard.BACKGROUND_BMAP = image;
                    switchToGame();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}