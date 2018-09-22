public class HasYear implements Validity{

    private int year;
    private Matchs match;


    public HasYear(Matchs match, int year) {
        this.year = year;
        this.match = match;

    }

    @Override
    public boolean isValid() {
        return match.getYear() == year;
    }


}
