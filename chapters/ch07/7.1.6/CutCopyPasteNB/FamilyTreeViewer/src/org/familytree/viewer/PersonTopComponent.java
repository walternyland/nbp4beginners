/**
 *
 * @author chris.esposito@gmail.com
 */
package org.familytree.viewer;

import java.awt.BorderLayout;
import java.beans.IntrospectionException;
import javax.swing.ActionMap;
import javax.swing.text.DefaultEditorKit;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/*
 * Sample top component - has two subtrees - one with men's names, the other with women. 
 * rename, copy / paste / cut / delete / move up&down, drag &drop support between trees. 
 * The default for drag and drop is to copy nodes, not move them.
 */
@ConvertAsProperties(
        dtd = "-//org.familytree.viewer//Person//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "PersonTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "org.familytree.viewer.PersonTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
public class PersonTopComponent
        extends TopComponent implements ExplorerManager.Provider, LookupListener {

    /**
     * @return the selectedNode
     */
    public static PersonNode getSelectedNode() {
        return selectedNode;
    }

    private final ExplorerManager em = new ExplorerManager();
    static private PersonNode selectedNode = null;

    public PersonTopComponent() throws IntrospectionException {
        initComponents();
        setName("Person Window");

        setLayout(new BorderLayout());
        add(new BeanTreeView(), BorderLayout.CENTER);

        // create the top person hierarchy
        Person croot1 = new Person("Adam");
        Person t = new Person("Tom");
        Person d = new Person("Dick");
        Person h = new Person("Harry");
        croot1.getKids().add(t);
        croot1.getKids().add(d);
        croot1.getKids().add(h);
        PersonNode root1 = new PersonNode(croot1, Children.create(new PersonFactory(croot1.getKids()), true));
        root1.setDisplayName("Men");

        // create the bottom person hierarchy
        Person croot2 = new Person("Eve");
        Person m = new Person("Mary");
        Person s = new Person("Sue");
        Person j = new Person("Jane");
        croot2.getKids().add(m);
        croot2.getKids().add(s);
        croot2.getKids().add(j);
        PersonNode root2 = new PersonNode(croot2, Children.create(new PersonFactory(croot2.getKids()), true));
        root2.setDisplayName("Women");

        AbstractNode root = new AbstractNode(new Children.Array());
        root.setDisplayName("Family Tree");
        root.getChildren().add(new Node[]{
            root1, root2
        });
        em.setRootContext(root);

        // add some actions
        ActionMap map = getActionMap();
        map.put(DefaultEditorKit.copyAction, ExplorerUtils.actionCopy(em));
        map.put(DefaultEditorKit.cutAction, ExplorerUtils.actionCut(em));
        map.put(DefaultEditorKit.pasteAction, ExplorerUtils.actionPaste(em));
        map.put("delete", ExplorerUtils.actionDelete(em, true));
        associateLookup(ExplorerUtils.createLookup(em, map));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private Lookup.Result<PersonNode> custNodeResult;

    /**
     * add node selection listener when component is opened
     */
    @Override
    public void componentOpened() {
        custNodeResult = Utilities.actionsGlobalContext().lookupResult(PersonNode.class);
        custNodeResult.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        custNodeResult.removeLookupListener(this);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }

    /**
     * track node selection.
     */
    @Override
    public void resultChanged(LookupEvent ev) {
        for (PersonNode cn : custNodeResult.allInstances()) {
            selectedNode = cn;
        }
    }

}
