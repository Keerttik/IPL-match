
import java.util.*;

public class Innings {

    private Map<Integer, Overs> numberOversMap;
    private int inningNumber;
    private String battingTeam;
    private String bowlingTeam;

    public Innings(int number, String battingTeam, String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
        this.battingTeam = battingTeam;
        this.inningNumber = number;
        numberOversMap = new HashMap<>();
    }

    public Map<Integer, Overs> getNumberOversMap() {
        return numberOversMap;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }


    public boolean doesNotContainOver(int over) {
        return !numberOversMap.containsKey(over);
    }


    public Overs getOver(int over) {
        return numberOversMap.get(over);
    }

    @Override
    public String toString() {
        return "Inning{" +
                "numberOversMap=" + numberOversMap +
                ", inning number=" + inningNumber +
                ", battingTeam='" + battingTeam + '\'' +
                ", bowlingTeam='" + bowlingTeam + '\'' +
                '}';
    }

    public void addOver(Overs over) {

        numberOversMap.put(over.getNumber(), over);
    }

    public boolean hasBowler(String bowler) {

        for (Overs over : numberOversMap.values()) {
            if (over.hasBowler(bowler)) return true;
        }
        return false;
    }

    public List<String> getBowlersList() {
        Set<String> uniqueBowlers = new HashSet<>();

        for (Overs over : numberOversMap.values()) {
            uniqueBowlers.addAll(over.getBowlersList());
        }
        List<String> bowlers = new ArrayList<>(uniqueBowlers);
        return bowlers;
    }


    public int getRunsOfType(Score type) {
        int runs = 0;
        for (Overs over : numberOversMap.values()) {

            runs += over.getRunsOfType(type);
        }
        return runs;
    }


    public int getTotalRunsByBowlerForRunType(String bowler, Score type) {
        if (!hasBowler(bowler)) return 0;
        int totalRunsOfBowlerOfRunType = 0;

        for (Overs over : getNumberOversMap().values()) {

            totalRunsOfBowlerOfRunType += over.getTotalRunsByBowlerForRunType(bowler, type);
        }

        return totalRunsOfBowlerOfRunType;
    }

    public int getNumberOfBallsBowledByBowler(String bowler) {
        if (!hasBowler(bowler)) return 0;
        int totalNumberOfBallsBowled = 0;
        for (Overs over : numberOversMap.values()) {
            if (over.hasBowler(bowler)) {
                totalNumberOfBallsBowled += over.getTotalNumberOfBallsBowledByBowler(bowler);
            }
        }
        return totalNumberOfBallsBowled;
    }


    public int getTotalFours() {
        //assumption , if the BatsmanRuns is 4, then it is supposed that it's a 4 by a stroke , not by running between wickets
        int fours = 0;
        for (Overs over : numberOversMap.values()) {

            fours += over.getTotalFours();
        }
        return fours;
    }


    public int getTotalSixes() {
        //assumption , if the BatsmanRuns is 6, then it is supposed that it's a 6 by a stroke , not by running between wickets
        int sixes = 0;
        for (Overs over : numberOversMap.values()) {

            sixes += over.getTotalSixes();
        }
        return sixes;
    }

    public int getNumber() {
        return inningNumber;
    }


}
