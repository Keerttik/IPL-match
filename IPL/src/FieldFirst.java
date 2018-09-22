

public class FieldFirst implements Validity {

    private Matchs match;

    public FieldFirst(Matchs match) {
        this.match = match;
    }

    @Override
    public boolean isValid() {
        return match.getTossDecision() ==  Toss.FIELD;

    }
}
