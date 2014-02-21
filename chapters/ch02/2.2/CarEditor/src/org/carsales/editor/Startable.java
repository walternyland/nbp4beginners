package org.carsales.editor;

import org.carsales.viewer.CarService;
import org.openide.modules.OnStart;

@OnStart
public class Startable implements Runnable {

    @Override
    public void run() {
        new CarService().process("Honda");
    }
    
}
