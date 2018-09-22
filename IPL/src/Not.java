

import java.util.List;

public class Not implements Validity{

    List<Validity> vList;

    public Not(List<Validity> val) {
        this.vList = val;
    }

    @Override
    public boolean isValid() {

        for (Validity v : vList) {
            if (v.isValid()) return false;
        }
        return true;
    }
}
