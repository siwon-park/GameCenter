package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import fall2018.csc2017.GameCentre.BoardManager;

public class SaveFile implements Serializable {
    private Map<Integer, BoardManager> map;

    public SaveFile() {
        map = new HashMap<>();
    }

    public void addSave(BoardManager boardManager) {
        map.put(boardManager.getGameID(), boardManager);
    }

    public BoardManager getBM(int GameID) {
        return map.get(GameID);
    }

}
