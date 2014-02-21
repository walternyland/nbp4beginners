package org.carsales.viewer;

import java.beans.IntrospectionException;
import java.util.Arrays;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

public class CarChildFactory extends ChildFactory<Car> {

    @Override
    protected boolean createKeys(List<Car> list) {
        list.add(new Car("Honda"));
        list.add(new Car("Mercedes"));
        list.add(new Car("Mazda"));
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
