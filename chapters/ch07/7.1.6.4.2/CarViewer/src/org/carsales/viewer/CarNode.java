package org.carsales.viewer;

import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.carsales.api.Car;
import org.netbeans.core.api.multiview.MultiViews;
import org.openide.actions.DeleteAction;
import org.openide.actions.RenameAction;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;

public class CarNode extends BeanNode implements Serializable {

    public CarNode(Car bean) throws IntrospectionException {
        super(bean, Children.LEAF, Lookups.singleton(bean));
        setDisplayName(bean.getBrand());
    }

    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{
            SystemAction.get(RenameAction.class),
            SystemAction.get(DeleteAction.class)
        };
    }
    
    @Override
    public Action getPreferredAction() {
        return new AbstractAction("Edit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = MultiViews.createMultiView("application/x-carnode", CarNode.this);
                tc.open();
                tc.requestActive();
            }
        };
    }

    @Override
    public String getName() {
        Car c = getLookup().lookup(Car.class);
        if (null != c.getBrand()) {
            return c.getBrand();
        }
        return super.getDisplayName();
    }

    @Override
    public void setName(String newDisplayName) {
        Car c = getLookup().lookup(Car.class);
        String oldDisplayName = c.getBrand();
        c.setBrand(newDisplayName);
        fireNameChange(oldDisplayName, newDisplayName);
    }

    @Override
    public boolean canRename() {
        return true;
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
