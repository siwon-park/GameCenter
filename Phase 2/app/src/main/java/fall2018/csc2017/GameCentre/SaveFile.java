package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to manage multiple game board managers
 */
public class SaveFile implements Serializable {
    /**
     * Map of board managers with Game name as the key.
     */
    private Map<String, BoardManager> map;

    public SaveFile() {
        map = new HashMap<>();
    }

    /**
     * Add the game save to the map
     * @param boardManager boardManager to save
     */
    public void addSave(BoardManager boardManager) {
        map.put(boardManager.getGameName(), boardManager);
    }

    /**
     * Get boardManager for the game in the save file.
     * @param GameName Name of the game
     * @return boardManager return the boardManager of the Game from the save file.
     */
    public BoardManager getBM(String GameName) {
        return map.get(GameName);
    }

}
