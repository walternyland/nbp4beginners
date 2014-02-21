package org.carsales.viewer.view;

import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

public class CarChildFactory extends ChildFactory<Car> {

    @Override
    protected boolean createKeys(List<Car> list) {
        list.add(new Car("Honda", 1971));
        list.add(new Car("Mercedes", 1988));
        list.add(new Car("Mazda", 1982));
        return true;
    }

    @Override
    protected Node createNodeForKey(Car key) {
        CarNode node = null;
        try {
            node = new CarNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }
}
