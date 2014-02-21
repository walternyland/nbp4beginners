package org.carsales.viewer.view;

import java.awt.BorderLayout;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
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
        //1. Add an explorer view, in this case BeanTreeView:
        add(new BeanTreeView(), BorderLayout.CENTER);
        //2. Create a node hierarchy:
        Children carChildren = Children.create(new CarChildFactory(), true);
        Node rootNode = new AbstractNode(carChildren);
        rootNode.setDisplayName("Cars");
        //3. Set the root of the node hierarchy on the ExplorerManager: 
        em.setRootContext(rootNode);
        associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }
}
