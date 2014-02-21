package org.carsales.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.carsales.api.NodePopupMenu;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

@ActionID(
        category = "Edit",
        id = "org.carsales.viewer.AnalyzerAction"
)
@ActionRegistration(
        displayName = "#CTL_AnalyzerAction"
)
@NodePopupMenu(
        path = "Cars",
        pathToSubPopupMenus = "SubPopupMenu/Cars",
        displayName = "Analyzers")
@NbBundle.Messages("CTL_AnalyzerAction=Analyzer")
public final class AnalyzersAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
