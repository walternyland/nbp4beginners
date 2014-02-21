package org.carsales.viewer;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.Set;
import javax.swing.Action;
import org.carsales.api.Car;
import org.openide.actions.DeleteAction;
import org.openide.actions.OpenAction;
import org.openide.actions.RenameAction;
import org.openide.cookies.OpenCookie;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

public class CarNode extends BeanNode {

    public CarNode(Car bean) throws IntrospectionException {
        this(bean, new InstanceContent());
    }

    private CarNode(final Car bean, InstanceContent ic) throws IntrospectionException {
        super(bean, Children.LEAF, new ProxyLookup(
                new AbstractLookup(ic), 
                Lookups.singleton(bean)));
        setDisplayName(bean.getBrand());
        ic.add(new OpenCookie() {
            @Override
            public void open() {
                TopComponent tc = findTopComponent(bean);
                if (tc == null) {
                    tc = new CarEditorTopComponent(bean);
                    tc.open();
                }
                tc.requestActive();
            }
        });
    }

    private TopComponent findTopComponent(Car car) {
        Set<TopComponent> openTopComponents = 
                WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent tc : openTopComponents) {
            if (tc.getLookup().lookup(Car.class) == car && 
                    tc.getLookup().lookup(OpenCookie.class) == null) {
                return tc;
            }
        }
        return null;
    }

    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{
            SystemAction.get(OpenAction.class),
            SystemAction.get(RenameAction.class),
            SystemAction.get(DeleteAction.class)
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
