package org.car.viewer;

import java.awt.BorderLayout;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

@TopComponent.Description(
        preferredID = "ViewerTopComponent",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(
        mode = "explorer",
        openAtStartup = true)
@ActionID(
        category = "Window",
        id = "org.car.viewer.ViewerTopComponent")
@ActionReference(
        path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_ViewerAction",
        preferredID = "ViewerTopComponent"
)
@Messages({
    "CTL_ViewerAction=Viewer",
    "CTL_ViewerTopComponent=Viewer Window"
})
public final class ViewerTopComponent extends TopComponent implements ExplorerManager.Provider {
    private final ExplorerManager em = new ExplorerManager();
    public ViewerTopComponent() {
        setDisplayName(Bundle.CTL_ViewerTopComponent());
        setLayout(new BorderLayout());
        BeanTreeView btv = new BeanTreeView();
        btv.setRootVisible(false);
        add(btv, BorderLayout.CENTER);
        Children kids = Children.create(new CarChildFactory(), true);
        AbstractNode rootNode = new AbstractNode(kids);
        em.setRootContext(rootNode);
        associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
    }
    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }
}