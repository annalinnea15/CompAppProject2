package CompAppProject2;
import java.util.Comparator;

public class DistanceComparator implements Comparator<DistComp> {
    @Override
    public int compare(DistComp a, DistComp b) {
       return a.distTL < b.distTL ? -1 : a.distTL == b.distTL ? 0 : 1;
    }
}
