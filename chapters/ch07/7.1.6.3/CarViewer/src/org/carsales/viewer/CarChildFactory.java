package org.carsales.viewer;

import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.List;
import org.carsales.api.Car;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.nodes.NodeEvent;
import org.openide.nodes.NodeListener;
import org.openide.nodes.NodeMemberEvent;
import org.openide.nodes.NodeReorderEvent;
import org.openide.util.Exceptions;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.Lookups;

public class CarChildFactory extends ChildFactory.Detachable<Car> implements LookupListener, NodeListener {

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
            carNode.addNodeListener(this);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return carNode;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        refresh(true);
    }

    @Override
    public void nodeDestroyed(NodeEvent ev) {
        Car removedCar = ev.getNode().getLookup().lookup(Car.class);
        for (FileObject fo : FileUtil.getConfigFile("Cars").getChildren()) {
            Car car = FileUtil.getConfigObject(fo.getPath(), Car.class);
            if (removedCar == car) {
                try {
                    fo.delete();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        refresh(true);
    }

    @Override
    public void childrenAdded(NodeMemberEvent nme) {
    }

    @Override
    public void childrenRemoved(NodeMemberEvent nme) {
    }

    @Override
    public void childrenReordered(NodeReorderEvent nre) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

}
