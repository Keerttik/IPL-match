
import java.util.*;

public class Overs {

    private Map<Integer, balls> numberBallMap;
    private int overNumber;

    public Overs(int number) {
        this.overNumber = number;
        numberBallMap = new HashMap<>();
    }

    public void addBall(balls ball) {

        numberBallMap.put(ball.getNumber(), ball);
    }

    public boolean hasBowler(String bowler) {

        return getBowlersList().contains(bowler);
    }

    public int getRunsOfType(Score runType) {
        int total = 0;

        for (balls ball : numberBallMap.values()) {

            total += ball.getRunsOfType(runType);
        }
        return total;
    }

    public List<String> getBatsMenList() {
        List<String> batsmanList = new ArrayList<>();

        for (int ballNumber : numberBallMap.keySet()) {
            balls ball = numberBallMap.get(ballNumber);
            String batsman = ball.getBatsmanName();

            if (!batsmanList.contains(batsman)) {
                batsmanList.add(batsman);
            }
        }
        return batsmanList;
    }


    public List<String> getBowlersList() {

        Set<String> bowlers = new HashSet<>();
        for (balls ball : numberBallMap.values()) {
            bowlers.add(ball.getBowlerName());
        }

        List<String> bowlerList = new ArrayList<>(bowlers);
        return bowlerList;
    }

    public int getTotalNumberOfBallsBowledByBowler(String bowler) {
        if (!hasBowler(bowler)) return 0;

        int totalBalls = 0;
        for (balls ball : numberBallMap.values()) {
            if (ball.getBowlerName().equals(bowler)) {
                totalBalls++;
            }
        }
        return totalBalls;
    }

    public int getTotalRunsByBowlerForRunType(String bowler, Score type) {
        if (!hasBowler(bowler)) return 0;

        int totalRunsOfRunType = 0;
        for (balls ball : numberBallMap.values()) {
            totalRunsOfRunType += ball.getRunsOfType(type);
        }

        return totalRunsOfRunType;
    }




    public int getTotalSixes() {
        int totalSixes = 0;
        for (balls ball : numberBallMap.values()) {
            if (ball.getRunsOfType(Score.BATSMAN_RUNS) == 6) totalSixes++;
        }
        return totalSixes;
    }

    public int getTotalFours() {
        int totalFours = 0;
        for (balls ball : numberBallMap.values()) {
            if (ball.getRunsOfType(Score.BATSMAN_RUNS) == 4) totalFours++;
        }
        return totalFours;
    }


    @Override
    public String toString() {
        return "Over{" +
                "numberBallMap=" + numberBallMap +
                ", over number=" + overNumber +
                '}';
    }

    public int getNumber() {
        return overNumber;
    }
}
