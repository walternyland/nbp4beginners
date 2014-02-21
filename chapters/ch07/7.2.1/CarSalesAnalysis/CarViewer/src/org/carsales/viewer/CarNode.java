package org.carsales.viewer;

import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;

public class CarNode extends BeanNode {
    public CarNode(Car bean) throws IntrospectionException {
        super(bean, Children.create(new PartChildFactory(bean), true));
        setDisplayName(bean.getBrand());
        setShortDescription("Year: " + bean.getYear());
    }
}
