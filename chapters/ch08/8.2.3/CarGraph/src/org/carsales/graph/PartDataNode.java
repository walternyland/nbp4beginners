package org.carsales.graph;

import java.awt.Image;
import org.carsales.api.Part;
import org.openide.loaders.DataNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

public class PartDataNode extends DataNode {

    private final Part data;

    public PartDataNode(PartDataObject obj, Part data) {
        super(obj, Children.LEAF, Lookups.singleton(data));
        this.data = data;
    }

    @Override
    public Image getIcon(int i) {
        return data.getSmallImage();
    }

    @Override
    public String getDisplayName() {
        return data.getName();
    }

    @Override
    public String getShortDescription() {
        return data.getWidth() + "/" + data.getHeight();
    }
}
