package org.carsales.viewer;
import java.beans.IntrospectionException;
import java.util.Arrays;
import java.util.List;
import org.carsales.api.Car;
import org.carsales.api.Part;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
public class CarChildFactory extends ChildFactory<Car> {
    @Override
    protected boolean createKeys(List<Car> list) {
        //Make a connection to your database or other data source here,
        //for demo purposes, we simulate three Car objects retrieved from the data source
        list.add(new Car("Honda", Arrays.asList(new Part("wheel"), new Part("tyre"))));
        list.add(new Car("Mercedes", Arrays.asList(new Part("window"))));
        list.add(new Car("Mazda", Arrays.asList(new Part("bumper"))));
        return true;
    }
    @Override
    protected Node createNodeForKey(Car key) {
        CarNode node = null;
        try {
            node = new CarNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }
}
