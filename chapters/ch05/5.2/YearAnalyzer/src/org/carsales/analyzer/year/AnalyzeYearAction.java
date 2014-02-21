package org.carsales.analyzer.year;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Edit",
        id = "org.carsales.analyzer.brand.AnalyzeYearAction"
)
@ActionRegistration(
        displayName = "#CTL_AnalyzeYearAction"
)
@ActionReference(path = "Menu/File", position = 20)
@Messages("CTL_AnalyzeYearAction=Analyze Year")
public final class AnalyzeYearAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
    
}
