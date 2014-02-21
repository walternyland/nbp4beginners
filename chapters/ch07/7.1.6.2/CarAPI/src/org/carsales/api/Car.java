package org.carsales.api;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.netbeans.api.settings.ConvertAsJavaBean;

@ConvertAsJavaBean()
public class Car {
    String brand;
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
}
