package org.carsales.carviewer;

import java.util.Random;

public class Randomized {

    private final Random generator = new Random();
    private static int count = 0;
    private final int index;
    private final int x;
    private final int y;

    public Randomized() {
        index = count++;
        this.x = generator.nextInt(600);
        this.y = generator.nextInt(300);
    }

    public int getIndex() {
        return index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
