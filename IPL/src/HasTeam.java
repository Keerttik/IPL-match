public class HasTeam implements Validity{
    private String team;
    private Matchs match;


    public HasTeam(Matchs match, String team) {
        this.team = team;
        this.match = match;

    }

    @Override
    public boolean isValid() {
        return match.getFirstTeam().equals(team) || match.getSecondTeam().equals(team);
    }
}
