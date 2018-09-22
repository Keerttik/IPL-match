

public class BowlerForSort implements Comparable<BowlerForSort> {
    private double score;
    private String name;

    public BowlerForSort(String name, double score) {
        this.score = score;
        this.name = name;
    }

    @Override
    public int compareTo(BowlerForSort o) {
        return (int) (this.score - o.score);
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
