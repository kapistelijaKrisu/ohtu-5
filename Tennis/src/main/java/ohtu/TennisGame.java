package ohtu;

import java.util.HashMap;
import java.util.Map;

public class TennisGame {

    private final int treshold = 4;
    private final Player challenger;
    private final Player defender;
    private Map<Integer, String> scoreToString;
    private Map<Integer, String> advantageMap;

    public TennisGame(String player1Name, String player2Name) {
        challenger = new Player(player1Name);
        defender = new Player(player2Name);
        initScoreToStringMap();
        initAdvantageMap();
    }
//init score mappers

    private void initScoreToStringMap() {
        scoreToString = new HashMap<>();
        scoreToString.put(0, "Love");
        scoreToString.put(1, "Fifteen");
        scoreToString.put(2, "Thirty");
        scoreToString.put(3, "Forty");
    }

    private void initAdvantageMap() {
        advantageMap = new HashMap<>();
        advantageMap.put(-1, "Advantage player2");
        advantageMap.put(1, "Advantage player1");
        advantageMap.put(-2, "Win for player2");
        advantageMap.put(2, "Win for player1");
    }
    //publics

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName)) {
            challenger.score();
        } else {
            defender.score();
        }
    }

    public String getScore() {
        if (challenger.draw(defender)) {
            return resultWhenEven(challenger.getScore());
        } else if (challenger.getScore() >= treshold || defender.getScore() >= treshold) {
            return resultWhenUnderFour(challenger.getScore(), defender.getScore());
        } else {
            return resultWhenOverFour(challenger.getScore(), defender.getScore());
        }
    }
    //privates
    //score by scenario

    private String resultWhenEven(int m_score1) {
        if (m_score1 >= treshold) {
            return "Deuce";
        } else {
            return scoreToString.get(m_score1) + "-All";
        }
    }

    private String resultWhenOverFour(int m_score1, int m_score2) {
        return scoreToString.get(m_score1) + "-" + scoreToString.get(m_score2);
    }

    private String resultWhenUnderFour(int m_score1, int m_score2) {
        int difference = formatDifferenceForAdvantageMap(m_score1 - m_score2);
        return advantageMap.get(difference);
    }
    //format

    private int formatDifferenceForAdvantageMap(int difference) {
        difference = Math.max(-2, difference);
        difference = Math.min(2, difference);
        return difference;
    }
}
