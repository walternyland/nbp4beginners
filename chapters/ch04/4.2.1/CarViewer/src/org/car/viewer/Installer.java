package org.car.viewer;

import java.util.Collection;
import javax.swing.JOptionPane;
import org.car.api.CarValidator;
import org.openide.util.Lookup;
import org.openide.windows.OnShowing;

@OnShowing
public class Installer implements Runnable {

    @Override
    public void run() {
        Collection<? extends CarValidator> allRegisteredCarValidators
                = Lookup.getDefault().lookupAll(CarValidator.class);
        for (CarValidator carValidator : allRegisteredCarValidators) {
            // somehow pass in values for validation:
            if (carValidator.validate("honda")) {
                JOptionPane.showMessageDialog(null, "Validated!");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid!");
            }
        }
    }

}
