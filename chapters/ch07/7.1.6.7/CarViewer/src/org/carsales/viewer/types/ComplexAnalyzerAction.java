package org.carsales.viewer.types;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.carsales.viewer.view.Car;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.StatusDisplayer;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Analyzers",
        id = "org.carsales.viewer.types.ComplexAnalyzerAction"
)
@ActionRegistration(
        displayName = "#CTL_ComplexAnalyzerAction"
)
@ActionReference(
        path = "SubPopupMenu/Cars",
        position = 20
)
@Messages("CTL_ComplexAnalyzerAction=Complex")
public final class ComplexAnalyzerAction implements ActionListener {
    
    private final Car car;

    public ComplexAnalyzerAction(Car car) {
        this.car = car;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StatusDisplayer.getDefault().setStatusText("Begin complex analysis of " + car.getBrand() + "...");
    }
    
}
