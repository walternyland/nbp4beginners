package org.carsales.viewer;

import java.awt.Color;
import java.util.List;

public class Car {

    String brand;
    int year;
    Color color;
    List<Part> parts;

    public Car(String brand, Color color, int year, List<Part> parts) {
        this.brand = brand;
        this.color = color;
        this.year = year;
        this.parts = parts;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
}
