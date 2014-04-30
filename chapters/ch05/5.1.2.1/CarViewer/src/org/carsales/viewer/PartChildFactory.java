package org.carsales.viewer;
import java.beans.IntrospectionException;
import java.util.List;
import org.carsales.api.Car;
import org.carsales.api.Part;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
public class PartChildFactory extends ChildFactory<Part> {
    private final Car bean;
    PartChildFactory(Car bean) {
        this.bean = bean;
    }
    @Override
    protected boolean createKeys(List<Part> list) {
        list.addAll(bean.getParts());
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
