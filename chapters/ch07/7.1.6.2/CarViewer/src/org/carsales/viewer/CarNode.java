package org.carsales.viewer;

import java.beans.IntrospectionException;
import java.io.IOException;
import javax.swing.Action;
import org.carsales.api.Car;
import org.openide.actions.DeleteAction;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;

public class CarNode extends BeanNode {

    public CarNode(Car bean) throws IntrospectionException {
        super(bean, Children.LEAF, Lookups.singleton(bean));
        setDisplayName(bean.getBrand());
    }

    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{(SystemAction.get(DeleteAction.class))};
    }

    @Override
    public boolean canDestroy() {
        return true;
    }

    @Override
    public void destroy() throws IOException {
        fireNodeDestroyed();
    }

}
