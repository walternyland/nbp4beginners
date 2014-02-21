package org.carsales.carviewer;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.vmd.VMDGraphScene;
import org.netbeans.api.visual.vmd.VMDNodeWidget;
import org.netbeans.api.visual.vmd.VMDPinWidget;
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
        RandomizedVMDGraphScene scene = new RandomizedVMDGraphScene();
        add(new JScrollPane(scene.createView()), BorderLayout.CENTER); //VMDNodeWidgetL
//        VMDNodeWidget nodeWidget = new VMDNodeWidget(scene);
//        nodeWidget.getActions().addAction(ActionFactory.createMoveAction());
//        nodeWidget.setNodeName("node");
//        scene.addChild(nodeWidget);
//        VMDPinWidget pinWidget = new VMDPinWidget(scene);
//        pinWidget.setPinName("pin");
//        nodeWidget.addChild(pinWidget);
//        nodeWidget.setCheckClipping(true);
    }
}
