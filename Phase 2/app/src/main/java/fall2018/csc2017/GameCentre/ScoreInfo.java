package fall2018.csc2017.GameCentre;

/**
 * A class that contains the score and username to be displayed in scoreboard
 * Implements Comparable so that it can be sorted
 */
class ScoreInfo implements Comparable<ScoreInfo> {
    int score;
    String name;

    ScoreInfo(int score, String name) {
        this.score = score;
        this.name = name;
    }

    int getScore() {
        return score;
    }

    String getName() {
        return name;
    }

    // need sort in reverse order.
    public int compareTo(ScoreInfo compareScoreInfo) {
        return compareScoreInfo.getScore() - this.getScore();
    }
}