package org.car.viewer;

import java.beans.IntrospectionException;
import java.util.List;
import org.car.api.Car;
import org.car.api.CentralLookup;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

public class CarChildFactory extends ChildFactory.Detachable<Car> implements LookupListener {

    Lookup.Result<Car> carResult;
    
    @Override
    protected void addNotify() {
        carResult = CentralLookup.getDefault().lookupResult(Car.class);
        carResult.addLookupListener(this);
    }

    @Override
    protected void removeNotify() {
        carResult.removeLookupListener(this);
    }

    @Override
    protected boolean createKeys(List<Car> list) {
        list.addAll(carResult.allInstances());
        return true;
    }

    @Override
    protected Node createNodeForKey(Car key) {
        BeanNode node = null;
        try {
            node = new BeanNode(key);
            node.setDisplayName(key.getName());
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        refresh(true);
    }

}
