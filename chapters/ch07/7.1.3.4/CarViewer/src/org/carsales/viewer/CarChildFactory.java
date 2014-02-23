package org.carsales.viewer;

import java.awt.Color;
import java.beans.IntrospectionException;
import java.util.Arrays;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

public class CarChildFactory extends ChildFactory<Car> {

    @Override
    protected boolean createKeys(List<Car> list) {
        list.add(new Car("Honda", Color.BLUE, 1971,
                Arrays.asList(new Part("wheel"), new Part("seat"))));
        list.add(new Car("Mercedes", Color.RED, 1988, Arrays.asList(new Part("window"))));
        list.add(new Car("Mazda", Color.YELLOW, 1982,
                Arrays.asList(new Part("roof"), new Part("brake"))));
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
