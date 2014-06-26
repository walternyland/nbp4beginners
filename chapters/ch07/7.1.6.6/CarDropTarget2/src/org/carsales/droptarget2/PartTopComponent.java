package org.carsales.droptarget2;

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
        preferredID = "PartTopComponent",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(
        mode = "properties",
        openAtStartup = true)
@NbBundle.Messages({
    "CTL_CarViewer=PartTopComponent Window",
    "HINT_CarViewer=This is a PartTopComponent window"
})
public class PartTopComponent extends TopComponent implements ExplorerManager.Provider {

    private final ExplorerManager em = new ExplorerManager();

    public PartTopComponent() {
        setName(Bundle.CTL_CarViewer());
        setToolTipText(Bundle.HINT_CarViewer());
        setLayout(new BorderLayout());
        add(new BeanTreeView(), BorderLayout.CENTER);
        Children dropChildren = Children.create(new PartChildFactory(), true);
        Node rootNode;
        rootNode = new AbstractNode(dropChildren);
        rootNode.setDisplayName("Parts");
        em.setRootContext(rootNode);
        associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }
    
}
