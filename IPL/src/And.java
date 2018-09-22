public class And implements Validity{

    private Validity[] vList;

    public And(Validity... vList) {
        this.vList = vList;
    }
    @Override
    public boolean isValid() {

        for (Validity v: vList) {
            if (!v.isValid()) return false;
        }
       return true;
    }
}