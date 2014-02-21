package org.carsales.analyzer.brand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Edit",
        id = "org.carsales.analyzer.brand.AnalyzeBrandAction"
)
@ActionRegistration(
        displayName = "#CTL_AnalyzeBrandAction"
)
@ActionReference(path = "Menu/File", position = 10)
@Messages("CTL_AnalyzeBrandAction=Analyze Brand")
public final class AnalyzeBrandAction implements ActionListener {

    public AnalyzeBrandAction() {
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
    
}
