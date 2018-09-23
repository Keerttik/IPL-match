import java.util.*;

public class EnterMatch {
	

	    private Map<Integer, Matchs> idMatchMap;    
	    private Map<String, Team> nameTeamMap;


	    public EnterMatch(String fileName) {
	        
	        idMatchMap = new HashMap<>();
	        nameTeamMap = new HashMap<>();

	        FileParser fileParser = new FileParser(fileName);
	        List<List<String>> matchEntries = fileParser.getEntriesList();


	        for (List<String> matchEntryList : matchEntries) {

	            ArrayList<String> matchEntry = new ArrayList<>(matchEntryList);

	            int match_id = Integer.parseInt(matchEntry.get(0));

	            int season = Integer.parseInt(matchEntry.get(1));
	            String city = matchEntry.get(2);
	            String date = matchEntry.get(3);
	            String team1 = matchEntry.get(4);
	            String team2 = matchEntry.get(5);
	            String tossWinner = matchEntry.get(6);
	            Toss tossDecision = processTossDecision(matchEntry.get(7));
	            Result matchResult = processMatchResult(matchEntry.get(8));
	            String winner = matchEntry.get(9); // optional


	            Matchs match = new Matchs(match_id, season, city, date, team1, team2, tossWinner, tossDecision, matchResult, winner);
                idMatchMap.put(match_id, match);
	            addTeam(team1, match_id);
	            addTeam(team2, match_id);
	        }


	    }


	    public Map<Integer, Matchs> getIdMatchMap() {
	        return idMatchMap;
	    }

	    public Map<String, Team> getNameTeamMap() {
	        return nameTeamMap;
	    }

	    private void addTeam(String teamName, int match_id) {
	        if (!nameTeamMap.containsKey(teamName)) {
	            Team tempTeam = new Team(teamName);
	            nameTeamMap.put(teamName, tempTeam);
	        }
	        Team team = nameTeamMap.get(teamName);
	        team.addMatchID(match_id);
	    }

	    private Toss processTossDecision(String tossDecision) {
	        tossDecision = tossDecision.trim();

	        if (tossDecision.equals("field")) {
	            return Toss.FIELD;
	        } else return Toss.BAT;

	    }

	    private Result processMatchResult(String matchResult) {
	        matchResult = matchResult.trim();
	        if (matchResult.equals("tie")) return Result.TIE;
	        else if (matchResult.equals("normal")) return Result.NORMAL;
	        return Result.NORESULT;
	    }
	}


