package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Serializable AccountManager class to manage user accounts and their scores
 */
public class AccountManager implements Serializable {
    private Map<String, Account> map;
    private Account currentAccount;

    /**
     * A new AccountManager; a constructor
     */
    public AccountManager() {
        map = new HashMap<>();
        currentAccount = null;
    }

    /**
     * Adds an account to the hash map if account does not exist in it yet.
     * @param account
     * @return whether account already exists in hash map
     */
    public boolean addAccount(Account account) {
        if (map.containsKey(account.getUsername())) {
            return false;
        }
        map.put(account.getUsername(), account);
        return true;
    }

    /**
     * Getter method
     * @param username
     * @return account based on input username
     */
    public Account getAccount(String username) {
        return map.get(username);
    }

    /**
     * Getter method
     * @return current account
     */
    public Account getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Setter method
     * @param currentAccount
     */
    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }



    /**
     * returns a new string array to be displayed by listView in the corresponding scoreboard
     * activity
     * @return top scores in string format
     */
    String[] displayPerGame(String gameID){
        List<ScoreInfo> topScores = new ArrayList<>();
        for (Account account : map.values()) {
            for (ScoreInfo score: account.getScores()) {
                if (score.getGameID().equals(gameID)) {
                    topScores.add(score);
                }
            }
        }

        return getDisplay(topScores, "Top Players");
    }

    /**
     * returns a new string array to be displayed by listView in the corresponding scoreboard
     * activity
     * @return top scores of the user in string format
     */
    String[] displayPerUser(String name ,String gameID){
        List<ScoreInfo> topScores = new ArrayList<>();
        for (ScoreInfo score : this.getAccount(name).getScores()) {
            if (score.getGameID().equals(gameID)) {
                topScores.add(score);
            }
        }
        PerUserScoreboardActivity.setUserSearched(null);
        return getDisplay(topScores, name+ "'s Top Scores");
    }

    /**
     * returns a new string array to be displayed by listView in the corresponding scoreboard
     * activity
     * @return top scores of the searched user in string format
     */
    String[] displayPerSearch(String name ,String gameID) {
        List<ScoreInfo> topScores = new ArrayList<>();
        for (Account account : map.values()) {
            for (ScoreInfo score : account.getScores()) {
                if (score.getGameID().equals(gameID)) {
                    topScores.add(score);
                }
            }
        }

        Collections.sort(topScores);
        String[] display = new String[topScores.size() + 1];
        display[0] = name+ "'s ranking on Leaderboards";

        for(int i1 =1; i1< topScores.size()+1; i1++){
            display[i1] = "";
        }
        int i2 = 1;
        int j = 1;
        for(ScoreInfo info: topScores){
            if(info.getName().equals(name)) {
                display[j] = i2 + ".  " + info.getName() + " : " + info.getScore();
                j++;
            }
            i2++;
        }
        return display;
    }



    /**
     * This function returns the array of strings that include the usernames and scores for the
     * scoreboard. First sort the topScores in reverse order.
     * @param topScores
     * @param title
     * @return array of string that contains display
     */
    private String[] getDisplay(List<ScoreInfo> topScores, String title) {
        Collections.sort(topScores);
        String[] display = new String[topScores.size() + 1];
        display[0] = title;
        int i = 1;


        for (ScoreInfo info : topScores) {
            display[i] = i + ".  " + info.getName() + " : " + info.getScore();
                i++;
        }

        return display;
    }
}
