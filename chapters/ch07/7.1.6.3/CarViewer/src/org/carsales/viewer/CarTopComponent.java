package org.carsales.viewer;

import java.awt.BorderLayout;
import java.beans.IntrospectionException;
import javax.swing.ActionMap;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
//import org.openide.explorer.view.IconView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
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
        add(new BeanTreeView(), BorderLayout.CENTER);
        Children carChildren = Children.create(new CarChildFactory(), true);
        Node rootNode;
        try {
            rootNode = new RootNode(carChildren);
            rootNode.setDisplayName("Cars");
            em.setRootContext(rootNode);
            ActionMap map = getActionMap();
            map.put("delete", ExplorerUtils.actionDelete(em, true));
            associateLookup(ExplorerUtils.createLookup(em, map));
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }

}
