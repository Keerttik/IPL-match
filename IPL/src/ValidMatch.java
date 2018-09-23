public class ValidMatch implements Validity {

    private Matchs match;

    public ValidMatch(Matchs match) {
        this.match = match;
    }

    @Override
    public boolean isValid() {
        return match.getResultType() != Result.NORESULT;
    }
}
