package org.carsales.viewer;
import java.beans.IntrospectionException;
import org.carsales.api.Car;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;
public class CarNode extends BeanNode {
    public CarNode(Car bean) throws IntrospectionException {
        super(bean, Children.create(new PartChildFactory(bean), true), Lookups.singleton(bean));
        setDisplayName(bean.getBrand());
    }
}
