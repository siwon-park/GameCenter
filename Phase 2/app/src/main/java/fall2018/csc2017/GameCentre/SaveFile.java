package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SaveFile implements Serializable {
    private Map<String, BoardManager> map;

    public SaveFile() {
        map = new HashMap<>();
    }

    public void addSave(BoardManager boardManager) {
        map.put(boardManager.getGameName(), boardManager);
    }

    public BoardManager getBM(String GameName) {
        return map.get(GameName);
    }

}
