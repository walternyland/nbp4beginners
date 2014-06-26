package org.carsales.droptarget2;
import java.awt.datatransfer.Transferable;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.List;
import javax.swing.Action;
import org.carsales.api.Car;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.Utilities;
import org.openide.util.lookup.Lookups;
public class PartNode extends BeanNode {
    Car bean;
    public PartNode(Car bean) throws IntrospectionException {
        super(bean, Children.LEAF, Lookups.singleton(bean));
        this.bean = bean;
        setDisplayName(bean.getBrand());
    }
    @Override
    public Action[] getActions(boolean context) {
        List<? extends Action> carActions = Utilities.actionsForPath("PopupMenu/Cars");
        return carActions.toArray(new Action[carActions.size()]);
    }
    @Override
    public Transferable drag() throws IOException {
        return bean;
    }
}
