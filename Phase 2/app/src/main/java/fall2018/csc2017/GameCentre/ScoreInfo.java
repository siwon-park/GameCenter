package fall2018.csc2017.GameCentre;

/**
 * A class that contains the score and username to be displayed in scoreboard
 * Implements Comparable so that it can be sorted
 */
class ScoreInfo implements Comparable<ScoreInfo> {
    int score;
    String name;
    int gameID;

    ScoreInfo(int score, String name, int gameID) {
        this.score = score;
        this.name = name;
        this.gameID = gameID;
    }

    int getScore() {
        return score;
    }

    String getName() {
        return name;
    }

    int getGameID(){return gameID;}

    // need sort in reverse order.
    public int compareTo(ScoreInfo compareScoreInfo) {
        return compareScoreInfo.getScore() - this.getScore();
    }
}