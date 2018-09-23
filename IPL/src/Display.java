import java.util.Scanner;

public class Display{

    private Scanner scanner;
    
	private MatchStats MatchStats;

    public Display(MatchStats ms) {
        this.MatchStats = ms;
        this.scanner = new Scanner(System.in);
    }

    public void run() {

        while (true) {
          
        	System.out.println("\n\n ========FRAGMA DATA========= \n \n \n");
        	System.out.println("Enter Your Choice : \n " ) ;
            System.out.println( "1. Top 4 teams which elected to field first after winning "
            		+ "toss in the year 2016 and 2017.\n");            
            System.out.println("2. Total number of fours, sixes, total score with respect to team and year.  \n ");
            System.out.println("3. Top 10 best economy rate bowler with respect to year who bowled "
            		+ "at least 10 overs\n");
            System.out.println("4. The team name which has Highest Net Run Rate with respect to year.\n");

            System.out.println("5. Exit");
 
            String entry = scanner.nextLine();

           if (entry.equals("1")) {
                MatchStats.top4Teams(4, 2016);			//top 4 teams for year 2016
                MatchStats.top4Teams(4, 2017);			//top 4 teams for year 2017
                System.out.println();
 
            } else if (entry.equals("2")) {
                System.out.println("\n=========\n");
                MatchStats.printAllTeamScoresInAllYears();

            } else if (entry.equals("3")) {
            	MatchStats.printTopBowlersInAllMatchYears(10);
                System.out.println();

            } else if (entry.equals("4")) {
            	//MatchStats.printTeamHighestRunRate();
                System.out.println();
            }
           
            else if (entry.equals("5")) {
            	System.exit(0); 
               }
        }

    }
}
