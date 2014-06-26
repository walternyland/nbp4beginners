package org.carsales.viewer;

import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.carsales.api.Car;
import org.netbeans.core.api.multiview.MultiViewHandler;
import org.netbeans.core.api.multiview.MultiViews;
import org.openide.actions.DeleteAction;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

public class CarNode extends BeanNode implements Serializable {

    public CarNode(Car bean) throws IntrospectionException {
        super(bean, Children.LEAF, Lookups.singleton(bean));
        setDisplayName(bean.getBrand());
    }

    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{
            SystemAction.get(DeleteAction.class)
        };
    }

    @Override
    public Action getPreferredAction() {
        return new AbstractAction("Edit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car car = getLookup().lookup(Car.class);
                TopComponent tc = findTopComponent(car);
                if (tc == null) {
                    tc = MultiViews.createMultiView("application/x-carnode", CarNode.this);
                    //Add another one, on the fly, without registering it:
                    MultiViewHandler mvh = MultiViews.findMultiViewHandler(tc);
                    mvh.addMultiViewDescription(new CarMultiView2Description(), 20);
                    tc.open();
                }
                tc.requestActive();
            }
        };
    }

    private TopComponent findTopComponent(Car car) {
        Set<TopComponent> openTopComponents
                = WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent tc : openTopComponents) {
            if (!tc.getName().equals("CarViewerTopComponent")) {
                if (tc.getLookup().lookup(Car.class) == car) {
                    return tc;
                }
            }
        }
        return null;
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
