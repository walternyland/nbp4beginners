package org.carsales.viewer.types;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.carsales.viewer.view.Car;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.StatusDisplayer;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Analyzers",
        id = "org.carsales.viewer.types.SimpleAnalyzerAction"
)
@ActionRegistration(
        displayName = "#CTL_SimpleAnalyzerAction"
)
@ActionReference(
        path = "SubPopupMenu/Cars",
        position = 10
)
@Messages("CTL_SimpleAnalyzerAction=Simple")
public final class SimpleAnalyzerAction implements ActionListener {
    private final Car car;
    public SimpleAnalyzerAction(Car car) {
        this.car = car;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        StatusDisplayer.getDefault().setStatusText(
                "Begin simple analysis of " + car.getBrand() + "...");
    }
}
