package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * Manages loading from and saving to file
 */
public class LoadAndSave {
    /**
     * The file name for the account manager
     */
    public static final String ACCOUNT_MANAGER_FILENAME = "account_manager_file.ser";

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     * @param activity
     */
    public static Object loadFromFile(String fileName, AppCompatActivity activity) {

        try {
            InputStream inputStream = activity.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                Object result = input.readObject();
                inputStream.close();
                return result;
            }
        } catch (FileNotFoundException e) {
            Log.e("LoadAndSave loadFromFile activity " + activity.toString(), "File not found: " + e.toString());
            return null;
        } catch (IOException e) {
            Log.e("LoadAndSave loadFromFile activity " + activity.toString(), "Can not read file: " + e.toString());
            return null;
        } catch (ClassNotFoundException e) {
            Log.e("LoadAndSave loadFromFile activity " + activity.toString(), "File contained unexpected data type: " + e.toString());
            return null;
        } catch (Exception e) {
            Log.e("LoadAndSave loadFromFile activity " + activity.toString(), "Got an exception: " + e.toString());
        }
        return null;
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public static void saveToFile(String fileName, Object obj, AppCompatActivity activity) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    activity.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(obj);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
