package org.carsales.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.NotificationDisplayer;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Edit",
        id = "org.carsales.viewer.NotifyAction"
)
@ActionRegistration(
        displayName = "#CTL_NotifyAction"
)
@ActionReference(path = "Menu/Tools", position = 0)
@Messages("CTL_NotifyAction=Notify")
public final class NotifyAction implements ActionListener {
    @StaticResource
    private static final String ICON = "org/carsales/viewer/car.png";
    @Override
    public void actionPerformed(ActionEvent e) {
        NotificationDisplayer.getDefault().notify(
                "Title1",
                ImageUtilities.loadImageIcon(ICON, false),
                "Details1",
                null);
        NotificationDisplayer.getDefault().notify(
                "Title2",
                ImageUtilities.loadImageIcon(ICON, false),
                "Details2",
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
