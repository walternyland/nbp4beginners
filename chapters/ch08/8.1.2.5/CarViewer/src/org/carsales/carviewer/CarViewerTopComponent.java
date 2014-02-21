package org.carsales.carviewer;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
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
        RandomizedScene scene = new RandomizedScene();
//        LayerWidget mainLayer = new LayerWidget(scene);
//        LabelWidget helloLabelWidget = new LabelWidget(scene, "hello");
//        helloLabelWidget.getActions().addAction(ActionFactory.createMoveAction());
//        mainLayer.addChild(helloLabelWidget);
//        scene.addChild(mainLayer);
        add(new JScrollPane(scene.createView()), BorderLayout.CENTER);
    }
}
