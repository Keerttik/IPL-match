import java.util.*;

public class MatchStats{

    Map<Integer, Matchs> idMatchMap;
    Map<String, Team> nameTeamMap;
    private final double RUNSINOVER = 6.0;

    public MatchStats(Map<Integer, Matchs> idMatchMap, Map<String, Team> nameTeamMap) {
        this.idMatchMap = idMatchMap;
        this.nameTeamMap = nameTeamMap;
    }

    public void top4Teams(int top, int year) {

        Map<String, Integer> teamScoreMap = new HashMap<>();

        for (int id : idMatchMap.keySet()) {

            Matchs match = idMatchMap.get(id);           //returns values associated with key

            And allConditionsHoldTrue = new And(new HasYear(match, year),new ValidMatch(match), new FieldFirst(match));

            if (allConditionsHoldTrue.isValid()) {
                String team = match.getTossWinner();
                teamScoreMap.put(team, 0);
            }
        } 
      
        Map<String, Integer> validTeamScoreMap = getValidTeamsScoreMap(teamScoreMap, year);
        PriorityQueue<TeamForSort> sortedTeams = getProperTreeStructure(validTeamScoreMap);

      
        int count = sortedTeams.size() < top ? sortedTeams.size() : top;

        System.out.format("\n Top %d teams in the year %d are :%n  \n", count, year);

        while (!sortedTeams.isEmpty()) {

            if (top == 0) return;

            System.out.println(sortedTeams.poll().getName());

            top--;
        }
        System.out.println("\n");
    }

    private Map<String, Integer> getValidTeamsScoreMap(Map<String, Integer> teamScoreMap, int year) {

        for (int id : idMatchMap.keySet()) {

            Matchs match = idMatchMap.get(id);
            HasYear containsYear = new HasYear(match, year);

            if (containsYear.isValid()) {

                String team = match.getWinningTeam();
                //if it is a valid team or not
                if (teamScoreMap.containsKey(team)) {
                    int initalScore = teamScoreMap.get(team);
                    // increment value
                    teamScoreMap.put(team, initalScore + 1);
                }
            }

        }

        return teamScoreMap;
    }

    private PriorityQueue<TeamForSort> getProperTreeStructure(Map<String, Integer> validTeamsScoreMap) {
        PriorityQueue<TeamForSort> sortedQueue = new PriorityQueue<>();

        for (String teamName : validTeamsScoreMap.keySet()) {
            sortedQueue.add(new TeamForSort(teamName, validTeamsScoreMap.get(teamName)));
        }

        return sortedQueue;
    }

    public void printAllTeamScoresInAllYears() {
              
        List<String> teams = getTeamsList();
        List<Integer> years = getYearsList();
        System.out.println("Year    Team             Fours	   Sixes	totalScore");

        for (String team : teams) {

            for (int year : years) {

                printTeamScoreInYear(team, year);

            }
            System.out.println("\n");
        }

    }
    
    private void printTeamScoreInYear(String team, int year) {


        int totalScore = 0;
        int fours = 0;
        int sixes = 0;

        for (Matchs match : getIdMatchMap().values()) {

            And allConditionTrue = new And(new HasYear(match, year), new HasTeam(match, team));

            if (allConditionTrue.isValid()) {

                Innings firstInning; 
                Innings secondInning;

                boolean secondInningExists = match.inningsExists(2); 

                firstInning = match.getInning(1);
                secondInning = secondInningExists ? match.getInning(2) : null;

                if (firstInning.getBowlingTeam().equals(team)) {


                } else if (secondInningExists) {

                    totalScore += secondInning.getRunsOfType(Score.TOTAL_RUNS);
                    sixes += secondInning.getTotalSixes();
                    fours += secondInning.getTotalFours();
                }
            }
        }

       System.out.println(year + " " + team + "         " + fours + "        " + sixes + "       " + totalScore);

    }
//Economy = (Total Runs Given/Overs bowled)

    public void printTopBowlersInAllMatchYears(int top) {

      
        List<Integer> years = getYearsList();
        List<String> Bowlers = getBowlersList();
      
        for (int year : years) {

            Map<String, Integer> BowlerRunsGivenMap = new HashMap<>();
            Map<String, Integer> BowlerBowlsBowledMap = new HashMap<>();
            PriorityQueue<BowlerForSort> economyBowler = new PriorityQueue<>();

            for (String Bowler : Bowlers) {

                for (Matchs match : getIdMatchMap().values()) {
                    updateRunsAndBowlsByBowler(year, BowlerRunsGivenMap, BowlerBowlsBowledMap, Bowler, match);
                }


            }
            for (String name : BowlerRunsGivenMap.keySet()) {

                double BowlerRunsGiven = BowlerRunsGivenMap.get(name);
                double BowlerOversBowled = BowlerBowlsBowledMap.get(name) / RUNSINOVER;
                assert (BowlerOversBowled > 0);

                int economyScore = (int) (BowlerRunsGiven / BowlerOversBowled);

                economyBowler.add(new BowlerForSort(name, economyScore));
            }
            int limit = (top < economyBowler.size() ? top : economyBowler.size());


            System.out.println("YEAR    PLAYER    ECONOMY");


            for (int i = 0; i < limit; i++) {
                BowlerForSort currentBowler = economyBowler.poll();
                System.out.println(year + "    " + currentBowler.getName() + "       " + currentBowler.getScore());
            }
            System.out.println("\n");
        }
       
    }

    private void updateRunsAndBowlsByBowler(
            int year, Map<String, Integer> BowlerRunsGivenMap,
            Map<String, Integer> BowlerBowlsBowledMap,
            String Bowler, 
            Matchs match) {

        if (hasYearAndHasBowlerInMatch(year, Bowler, match)) {

            computeRunsByBowler(BowlerRunsGivenMap, Bowler, match);

            computeBowlsBowledByBowler(BowlerBowlsBowledMap, Bowler, match);
        }
    }

    private boolean hasYearAndHasBowlerInMatch(int year, String Bowler, Matchs match) {
        return match.getYear() == year && match.hasBowler(Bowler);
    }

    private void computeBowlsBowledByBowler(Map<String, Integer> BowlerBowlsBowledMap, String Bowler, Matchs match) {
        if (!BowlerBowlsBowledMap.containsKey(Bowler)) BowlerBowlsBowledMap.put(Bowler, 0);
        int previousNumberOfBowls = BowlerBowlsBowledMap.get(Bowler);
        int numberOfBowlsBowledInMatch = match.getNumberOfBallsBowledByBowler(Bowler);
        BowlerBowlsBowledMap.put(Bowler, previousNumberOfBowls + numberOfBowlsBowledInMatch);
    }

    private void computeRunsByBowler(Map<String, Integer> BowlerRunsGivenMap, String Bowler, Matchs match) {
        if (!BowlerRunsGivenMap.containsKey(Bowler)) BowlerRunsGivenMap.put(Bowler, 0);
        int initialScore = BowlerRunsGivenMap.get(Bowler);
        int matchTotalScoreByBowler = match.getTotalRunsByBowlerForScore(Bowler, Score.TOTAL_RUNS);
        int matchLegByeScoreByBowler = match.getTotalRunsByBowlerForScore(Bowler, Score.LEGBYE_RUNS);
        int matchByeScoreByBowler = match.getTotalRunsByBowlerForScore(Bowler, Score.BYE_RUNS);
        BowlerRunsGivenMap.put(Bowler, initialScore + (matchTotalScoreByBowler - (matchLegByeScoreByBowler + matchByeScoreByBowler)));
    }


    private List<String> getTeamsList() {
        List<String> teams = new ArrayList<>(getNameTeamMap().keySet());
        return teams;
    } 

    public List<String> getBowlersList() {
        Set<String> uniqueBowlers = new HashSet<>();

        for (Matchs match : getIdMatchMap().values()) {
            uniqueBowlers.addAll(match.getBowlersList());
        }
        List<String> Bowlers = new ArrayList<>(uniqueBowlers);
        return Bowlers;
    }


    public List<Integer> getYearsList() {
      
        Set<Integer> yearSet = new TreeSet<>(Collections.reverseOrder());
        for (Matchs match : idMatchMap.values()) {
            yearSet.add(match.getYear());
        }
        List<Integer> years = new ArrayList<>(yearSet);

        return years;
    }


    public Map<Integer, Matchs> getIdMatchMap() {
        return idMatchMap;
    }

    public Map<String, Team> getNameTeamMap() {
        return nameTeamMap;
    }

    //Net Run Rate = (Total Runs Scored / Total Overs Faced) – (Total Runs
    //Conceded /Total Overs Bowled)
   /* 
	public void printTeamHighestRunRate(int year, String team) {

        int netRunRate= 0;
        int totalScore = 0;
        int oversFaced=0;
        
        for (Matchs match : getIdMatchMap().values()) {

          
        }

       System.out.println(team + "         " + netRunRate );

		
	}*/

}
