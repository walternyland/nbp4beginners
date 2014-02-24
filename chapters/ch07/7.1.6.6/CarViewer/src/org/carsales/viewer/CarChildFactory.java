package org.carsales.viewer;

import java.beans.IntrospectionException;
import java.util.List;
import org.carsales.api.Car;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.Lookups;

public class CarChildFactory extends ChildFactory.Detachable<Car> implements LookupListener {

    Result<Car> res;

    @Override
    protected void addNotify() {
        res = Lookups.forPath("Cars").lookupResult(Car.class);
        res.addLookupListener(this);
    }

    @Override
    protected void removeNotify() {
        res.removeLookupListener(this);
    }

    @Override
    protected boolean createKeys(List<Car> list) {
        list.addAll(res.allInstances());
        return true;
    }

    @Override
    protected Node createNodeForKey(Car key) {
        CarNode carNode = null;
        try {
            carNode = new CarNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return carNode;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        refresh(true);
    }
    
}
