import java.util.ArrayList;
import java.util.List;

public class Or implements Validity{

    List<Validity> criteriaList;

    public Or(ArrayList<Validity> criteria) {

        criteriaList = new ArrayList<>();
        criteriaList.addAll(criteria);

    }

    @Override
    public boolean isValid() {
        for (Validity v: criteriaList) {
            if (v.isValid()) return true;
        }
        return false;
    }
}
