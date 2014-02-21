package org.carsales.viewer;

import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;

public class PartNode extends BeanNode {
    public PartNode(Part bean) throws IntrospectionException {
        super(bean, Children.LEAF);
        setDisplayName(bean.getName());
    }
}