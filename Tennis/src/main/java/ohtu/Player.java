
package ohtu;

public class Player {
    private final String name;
    private int score;

    public Player(String name) {
    score =0;
    this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
    
    public void score() {
        score++;
    }
    
    public boolean draw(Player other) {
        return this.score == other.getScore();
    }
    
    
    
}
