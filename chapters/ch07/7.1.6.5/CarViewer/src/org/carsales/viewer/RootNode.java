package org.carsales.viewer;

import javax.swing.Action;
import org.carsales.api.CarList;
import org.openide.actions.ReorderAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

public class RootNode extends AbstractNode {
    public RootNode(CarList model) {
        this(model, new InstanceContent());
    }
    private RootNode(final CarList model, InstanceContent ic) {
        super(Children.create(new CarChildFactory(model), true), new AbstractLookup(ic));
        ic.add(new Index.Support() {
            @Override
            public Node[] getNodes() {
                return getChildren().getNodes(true);
            }
            @Override
            public int getNodesCount() {
                return getNodes().length;
            }
            @Override
            public void reorder(int[] perm) {
                model.reorder(perm);
            }
        });
    }
    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{
            SystemAction.get(ReorderAction.class)
        };
    }
}
