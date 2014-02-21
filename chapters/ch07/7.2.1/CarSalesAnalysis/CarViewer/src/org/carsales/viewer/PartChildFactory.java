package org.carsales.viewer;

import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

public class PartChildFactory extends ChildFactory<Part> {

    private final Car car;

    public PartChildFactory(Car car) {
        this.car = car;
    }

    @Override
    protected boolean createKeys(List<Part> list) {
        list.addAll(car.getParts());
        return true;
    }

    @Override
    protected Node createNodeForKey(Part key) {
        PartNode node = null;
        try {
            node = new PartNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }
}
