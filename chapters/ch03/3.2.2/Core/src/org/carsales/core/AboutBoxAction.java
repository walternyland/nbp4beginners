package org.carsales.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Help",
        id = "org.carsales.core.AboutBoxAction"
)
@ActionRegistration(
        displayName = "#CTL_AboutBoxAction"
)
@ActionReference(
        path = "Menu/Help", 
        position = 100)
@Messages("CTL_AboutBoxAction=About Box")
public final class AboutBoxAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        DialogDescriptor dd = 
                new DialogDescriptor(
                        new AboutDialog(), 
                        "About Dialog");
        DialogDisplayer.getDefault().createDialog(dd).setVisible(true);
    }

}
