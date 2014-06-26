package org.carsales.droptarget2;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.carsales.api.Car;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.datatransfer.PasteType;
import org.openide.util.lookup.Lookups;

public class PartChildFactory extends ChildFactory<String> {

    ArrayList<String> parts = new ArrayList<String>();

    public PartChildFactory() {
        parts.add("Wheel");
        parts.add("Roof");
        parts.add("Window");
    }

    @Override
    protected boolean createKeys(List<String> list) {
        for (String name : parts) {
            list.add(name);
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(String name) {
        Node node = new AbstractNode(Children.LEAF, Lookups.singleton(name)) {
            @Override
            public PasteType getDropType(Transferable t, int arg1, int arg2) {
                try {
                    final Car car = (Car) t.getTransferData(Car.DATA_FLAVOR);
                    return new PasteType() {
                        @Override
                        public Transferable paste() throws IOException {
                            String theDraggedCar = car.getBrand();
                            String theNodeOnWhichTheDropOccurred = getLookup().lookup(String.class);
                            int i = parts.indexOf(theNodeOnWhichTheDropOccurred);
                            parts.add(i + 1, theDraggedCar);
                            refresh(true);
                            String message = "You dropped "
                                    + theDraggedCar + " on "
                                    + theNodeOnWhichTheDropOccurred + "!";
                            StatusDisplayer.getDefault().setStatusText(message);
                            return null;
                        }
                    };
                } catch (UnsupportedFlavorException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
                return null;
            }
        };
        node.setDisplayName(name);
        return node;
    }

}
