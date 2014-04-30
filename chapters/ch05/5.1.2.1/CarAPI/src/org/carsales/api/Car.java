package org.carsales.api;
import java.util.ArrayList;
import java.util.List;
public class Car {
    String brand;
    List<Part> parts = new ArrayList<Part>();
    public Car(String brand, List<Part> parts) {
        this.brand = brand;
        this.parts = parts;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public List<Part> getParts() {
        return parts;
    }
    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
