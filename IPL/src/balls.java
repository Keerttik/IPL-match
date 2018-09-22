



import java.util.EnumMap;

public final class balls {

    private EnumMap<Score, Integer> runTypeScoreMap;
    private String batsman;
    private String bowler;
    private int ballNumber;


    public balls(int number, String batsman, String bowler, EnumMap<Score, Integer> runTypeScoreMap) {
        this.runTypeScoreMap = runTypeScoreMap;
        this.ballNumber = number;
        this.bowler = bowler;
        this.batsman = batsman;
    }


    public String getBatsmanName() {
        return batsman;
    }

    public String getBowlerName() {
        return bowler;
    }

    public int getNumber() {
        return ballNumber;
    }

    public int getRunsOfType(Score type) {

        return runTypeScoreMap.getOrDefault(type, 0);
    }


    @Override
    public String toString() {
        return "Ball{" +
                "runTypeScoreMap=" + runTypeScoreMap +
                ", batsman='" + batsman + '\'' +
                ", bowler='" + bowler + '\'' +
                ", ball number=" + ballNumber +
                '}';
    }
}
