package org.carsales.viewer;

import java.awt.BorderLayout;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.OutlineView;
//import org.openide.explorer.view.IconView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

@TopComponent.Description(
        preferredID = "CarViewerTopComponent",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(
        mode = "explorer",
        openAtStartup = true)
@NbBundle.Messages({
    "CTL_CarViewer=CarViewer Window",
    "HINT_CarViewer=This is a CarViewer window"
})
public class CarTopComponent extends TopComponent implements ExplorerManager.Provider {

    private final ExplorerManager em = new ExplorerManager();

    public CarTopComponent() {
        setName(Bundle.CTL_CarViewer());
        setToolTipText(Bundle.HINT_CarViewer());
        setLayout(new BorderLayout());
        //1. Add an explorer view, in this case OutlineView:
        OutlineView ov = new OutlineView("Cars");
        ov.getOutline().setRootVisible(false);
        ov.setPropertyColumns(
                "year", "Year",
                "color", "Color");
        add(ov, BorderLayout.CENTER);
        //2. Create a node hierarchy:
        Children carChildren = Children.create(new CarChildFactory(), true);
        Node rootNode = new AbstractNode(carChildren);
        rootNode.setDisplayName("Cars");
        //3. Set the root of the node hierarchy on the ExplorerManager: 
        em.setRootContext(rootNode);
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }
    
}
