package org.carsales.viewer;

import org.carsales.api.Car;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;

public class CarEditorTopComponent extends TopComponent {
    CarEditorTopComponent(Car bean) {
        setDisplayName(bean.getBrand());
        associateLookup(Lookups.singleton(bean));
    }
}