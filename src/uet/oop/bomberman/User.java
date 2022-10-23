package uet.oop.bomberman;

public class User {
    private int rankings;
    private String name;
    private int score;

    public User(int rankings, String name, int score) {
        this.rankings = rankings;
        this.name = name;
        this.score = score;
    }

    public int getRankings() {
        return rankings;
    }

    public void setRankings(int rankings) {
        this.rankings = rankings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
