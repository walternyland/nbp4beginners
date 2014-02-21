package org.carsales.viewer;

import java.beans.IntrospectionException;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.carsales.api.Car;
import org.carsales.api.CarList;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

public class CarChildFactory
        extends ChildFactory.Detachable<Car> implements ChangeListener {

    private final CarList model;

    CarChildFactory(CarList model) {
        this.model = model;
    }

    @Override
    protected boolean createKeys(List<Car> list) {
        list.addAll(model.list());
        return true;
    }

    @Override
    protected Node createNodeForKey(Car key) {
        CarNode node = null;
        try {
            node = new CarNode(key);
        } catch (IntrospectionException ex) {
        }
        return node;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        refresh(true);
    }

    @Override
    protected void addNotify() {
        model.addChangeListener(this);
    }

    @Override
    protected void removeNotify() {
        model.removeChangeListener(this);
    }
    
}
