package task5;

import java.util.Comparator;

public class DeviceComparator
        implements Comparator<Device> {

    @Override
    public int compare(
            Device d1,
            Device d2) {

        return Double.compare(
                d1.getPrice(),
                d2.getPrice()
        );
    }
}