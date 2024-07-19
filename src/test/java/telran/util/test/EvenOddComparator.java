package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer arg0, Integer arg1) {
        boolean o1Even = arg0 % 2 == 0;
        boolean o2Even = arg1 % 2 == 0;
        return o1Even ? (o2Even ? arg0.compareTo(arg1) : -1) : (o2Even ? 1 : arg1.compareTo(arg0));
    }

}
