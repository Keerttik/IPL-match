

public class iplMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnterMatch em = new EnterMatch("matches.csv");

        EnterDeliveries ed= new EnterDeliveries("deliveries.csv", em.getIdMatchMap());


        MatchStats ms = new MatchStats(em.getIdMatchMap() , em.getNameTeamMap());

        Display d = new Display(ms);

        d.run();
 
	}

}
