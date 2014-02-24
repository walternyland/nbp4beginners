package org.carsales.api;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import org.netbeans.api.settings.ConvertAsJavaBean;
@ConvertAsJavaBean()
public class Car implements Transferable {
    String brand;
    public static final DataFlavor DATA_FLAVOR = new DataFlavor(Car.class, "car");
    private final PropertyChangeSupport propertyChangeSupport;
    public Car() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String newValue) {
        String oldValue = this.brand;
        this.brand = newValue;
        propertyChangeSupport.firePropertyChange("brand", oldValue, newValue);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DATA_FLAVOR};
    }
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor == DATA_FLAVOR;
    }
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor == DATA_FLAVOR) {
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
