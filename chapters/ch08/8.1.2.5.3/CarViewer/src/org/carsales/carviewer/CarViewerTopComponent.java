package org.carsales.carviewer;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

@TopComponent.Description(
        preferredID = "CarViewerTopComponent",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(
        mode = "editor",
        openAtStartup = true)
@NbBundle.Messages({
    "CTL_CarViewer=CarViewer Window",
    "HINT_CarViewer=This is a CarViewer window"
})
public class CarViewerTopComponent extends TopComponent {
    public CarViewerTopComponent() {
        setLayout(new BorderLayout());
        add(new JScrollPane(new RandomizedGraphPinScene().createView()), BorderLayout.CENTER);
    }
}
