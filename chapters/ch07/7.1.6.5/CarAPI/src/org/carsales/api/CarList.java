package org.carsales.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.openide.util.ChangeSupport;
public class CarList {
    private final List<Car> cars;
    private final ChangeSupport cs = new ChangeSupport(this);
    public CarList(List<Car> Cars) {
        this.cars = new ArrayList<Car>(Cars);
    }
    public List<? extends Car> list() {
        return cars;
    }
     public void add(Car c) {
        cars.add(c);
        cs.fireChange();
    }

    public void remove(Car c) {
        cars.remove(c);
        cs.fireChange();
    }
    public void reorder(int[] perm) {
        Car[] reordered = new Car[cars.size()];
        for (int i = 0; i < perm.length; i++) {
            int j = perm[i];
            Car c = cars.get(i);
            reordered[j] = c;
        }
        cars.clear();
        cars.addAll(Arrays.asList(reordered));
        cs.fireChange();
    }
    public void addChangeListener(ChangeListener l) {
        cs.addChangeListener(l);
    }
    public void removeChangeListener(ChangeListener l) {
        cs.removeChangeListener(l);
    }
}
