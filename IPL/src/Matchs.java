import java.util.*;

public class Matchs {
    private int year;
    private String winningTeam;
    private Toss tossDecision;
    private String tossWinner;
    private Result resultType;
    private String firstTeam;
    private String secondTeam;
    private int matchID;
    private String date;
    private String city;
    private Map<Integer, Innings> numberInningMap;


    public Matchs(int matchID, int season, String city, String date, String team1,
                 String team2, String tossWinner, Toss tossDecision,
                 Result resultType, String winner) {

        this.matchID = matchID;
        this.year = season;
        this.date = date;

        this.firstTeam = team1;
        this.secondTeam = team2;
        this.tossWinner = tossWinner;
        this.tossDecision = tossDecision;
        this.winningTeam = winner;
        this.resultType = resultType;
        this.city = city;
        this.numberInningMap = new HashMap<>();

    }

    public boolean inningsExists(int inningss) {
        return numberInningMap.containsKey(inningss);
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public Map<Integer, Innings> getInningsMap() {
        return numberInningMap;
    }

    public Innings getInning(int innings) {
        return numberInningMap.get(innings);
    }
    public String getFirstTeam() {
        return firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public Toss getTossDecision() {
        return tossDecision;
    }

    public Result getResultType() {
        return resultType;
    }

    public int getYear() {
        return year;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public int getMatchID() {
        return matchID;
    }

    public void addInning(Innings innings) {
        numberInningMap.put(innings.getNumber(), innings);
    }

    public boolean doesNotContainInning(int innings) {
        return !numberInningMap.containsKey(innings);
    }

    public boolean hasBowler(String bowler) {
        for (Innings innings : getInningsMap().values()) {
            if (innings.hasBowler(bowler)) return true;
        }
        return false;
    }

    public int getNumberOfBallsBowledByBowler(String bowler) {
        if (!hasBowler(bowler)) return 0;
        int totalBalls = 0;
        for (Innings innings : getInningsMap().values()) {
            totalBalls += innings.getNumberOfBallsBowledByBowler(bowler);
        }

        return totalBalls;
    }

    public List<String> getBowlersList() {
        Set<String> uniqueBowlers = new HashSet<>();
        for (Innings innings : getInningsMap().values()) {
            uniqueBowlers.addAll(innings.getBowlersList());
        }

        List<String> bowlers = new ArrayList<>(uniqueBowlers);
        return bowlers;
    }

    public int getTotalRunsByBowlerForScore(String bowler, Score type) {

        if (!hasBowler(bowler)) return 0;
        int totalRunsByBowlerOfScore = 0;

        for (Innings innings : getInningsMap().values()) {

            totalRunsByBowlerOfScore += innings.getTotalRunsByBowlerForScore(bowler, type);
        }

        return totalRunsByBowlerOfScore;
    }

    public String getBattingFirstTeam() {
        if (this.tossDecision == Toss.BAT) {
            return tossWinner.equals(firstTeam) ? firstTeam : secondTeam;
        } else {
            return tossWinner.equals(firstTeam) ? secondTeam : firstTeam;
        }
    }

    public String getBowlingFirstTeam() {
        return getBattingFirstTeam().equals(firstTeam) ? secondTeam : firstTeam;
    }

    @Override
    public String toString() {
        return "Match{" +
                "year=" + year +
                ", winningTeam='" + winningTeam + '\'' +
                ", tossDecision=" + tossDecision +
                ", tossWinner='" + tossWinner + '\'' +
                ", resultType=" + resultType +
                ", firstTeam='" + firstTeam + '\'' +
                ", secondTeam='" + secondTeam + '\'' +
                ", matchID=" + matchID +
                ", date=" + date +
                ", city='" + city + '\'' +
                ", numberInningMap=" + numberInningMap +
                '}';
    }
}
