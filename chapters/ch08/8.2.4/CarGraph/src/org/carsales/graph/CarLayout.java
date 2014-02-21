package org.carsales.graph;

import java.awt.Rectangle;
import java.util.List;
import org.netbeans.api.visual.layout.Layout;
import org.netbeans.api.visual.widget.Widget;

class CarLayout implements Layout {
    @Override
    public void layout(Widget widget) {
        for (Widget child : widget.getChildren()) {
            child.resolveBounds(null, null);
        }
    }
    @Override
    public boolean requiresJustification(Widget widget) {
        return true;
    }
    @Override
    public void justify(Widget widget) {
        Rectangle bounds = widget.getClientArea();
        List<Widget> children = widget.getChildren();
        children.get(0).resolveBounds(
                null,
                new Rectangle(
                        bounds.x,
                        bounds.y,
                        bounds.width / 2,
                        bounds.height));
        children.get(1).resolveBounds(null,
                new Rectangle(
                        bounds.x + bounds.width / 2,
                        bounds.y,
                        bounds.width - bounds.width / 2,
                        bounds.height));
    }
}
