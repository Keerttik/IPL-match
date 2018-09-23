import java.util.*;

	public class EnterDeliveries {
	

	    private Map<Integer, Matchs> idMatchMap;


	    public EnterDeliveries(String fileName, Map<Integer, Matchs> idMatchMap) {
	        this.idMatchMap = idMatchMap;

	        FileParser fileParser = new FileParser(fileName);


	        for (List<String> delivery : fileParser.getEntriesList()) {

	            ArrayList<String> arr = new ArrayList<>(delivery);

	            int matchID = Integer.parseInt(arr.get(0));
	            int inning = Integer.parseInt(arr.get(1));
	            String battingTeam = arr.get(2).trim();
	            String bowlingTeam = arr.get(3).trim();
	            int over = Integer.parseInt(arr.get(4));
	            int ball = Integer.parseInt(arr.get(5));
	            String batsman = arr.get(6).trim();
	            String bowler = arr.get(7).trim();

	            int[] runTypesArrayForDelivery = getRunTypesArrayForDelivery(arr);


	            processInning(matchID, inning, battingTeam, bowlingTeam, over, ball, 
	            		batsman, bowler, runTypesArrayForDelivery);

	        }

	    }

	    private int[] getRunTypesArrayForDelivery(ArrayList<String> arr) {


	        int runTypesArray[] = new int[8];

	        runTypesArray[0] = Integer.parseInt(arr.get(8));
	        runTypesArray[1] = Integer.parseInt(arr.get(9));
	        runTypesArray[2] = Integer.parseInt(arr.get(10));
	        runTypesArray[3] = Integer.parseInt(arr.get(11));
	        runTypesArray[4] = Integer.parseInt(arr.get(12));
	        runTypesArray[5] = Integer.parseInt(arr.get(13));
	        runTypesArray[6] = Integer.parseInt(arr.get(14));
	        runTypesArray[7] = Integer.parseInt(arr.get(15));

	        return runTypesArray;
	    }

	    public Map<Integer, Matchs> getIdMatchMap() {
	        return idMatchMap;
	    }

	    private void processInning(
	            int matchID,
	            int inning,
	            String battingTeam,
	            String bowlingTeam,
	            int over,
	            int ball,
	            String batsman,
	            String bowler,
	            int[] runs) {

	        Matchs match = idMatchMap.get(matchID);

	        if (match.doesNotContainInning(inning)) addNewInning(match, inning, battingTeam, bowlingTeam);

	        Innings presentInning = match.getInning(inning);

	        processOver(presentInning, over, ball, batsman, bowler, runs);

	    }

	    private void addNewInning(Matchs match, int inning, String battingTeam, String bowlingTeam) {
	        Innings inning1 = new Innings(inning, battingTeam, bowlingTeam);
	        match.addInning(inning1);
	    }

	    private void processOver(
	            Innings inning,
	            int over,
	            int ball,
	            String batsman,
	            String Bowler,
	            int[] runs) {


	        if (inning.doesNotContainOver(over))
	        	addNewOver(over, inning);

	        Overs myOver = inning.getOver(over);

	        processBall(myOver, ball, batsman, Bowler, runs);
	    }

	    private void addNewOver(int over, Innings inning) {
	        Overs over1 = new Overs(over);
	        inning.addOver(over1);
	    }

	    private void processBall(
	            Overs over,
	            int ball,
	            String batsman,
	            String bowler,
	            int[] runs) {

	        assert (runs.length == 9);

	        EnumMap<Score, Integer> runTypeMap = getRunTypeEnumMapForOneBall(runs);
	        balls myBall = new balls(ball, batsman, bowler, runTypeMap);

	        over.addBall(myBall);

	    }

	    private EnumMap<Score, Integer> getRunTypeEnumMapForOneBall(int[] runs) {

	        EnumMap<Score, Integer> runTypeMap = populateRunTypeEnumMapForOneBall(runs);

	        return runTypeMap;
	    }

	    private EnumMap<Score, Integer> populateRunTypeEnumMapForOneBall(int[] runs) {
	        EnumMap<Score, Integer> runTypeMap = new EnumMap<>(Score.class);

	        for (int runType = 0; runType < 8; runType++) {

	            int runValue = runs[runType];

	            Score[] typeOfRums = Score.values();

	            if (runValue != 0) {
	                runTypeMap.put(typeOfRums[runType], runValue);
	            }
	        }
	        return runTypeMap;
	    }
	}


