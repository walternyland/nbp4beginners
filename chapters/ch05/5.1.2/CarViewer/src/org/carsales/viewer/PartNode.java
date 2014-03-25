package org.carsales.viewer;
import java.beans.IntrospectionException;
import org.carsales.api.Part;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;
public class PartNode extends BeanNode {
    public PartNode(Part bean) throws IntrospectionException {
        super(bean, Children.LEAF, Lookups.singleton(bean));
        setDisplayName(bean.getName());
    }
}
