package org.carsales.carviewer;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.anchor.Anchor;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.graph.layout.GridGraphLayout;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.layout.SceneLayout;
import org.netbeans.api.visual.router.RouterFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;

class RandomizedGraphScene extends GraphScene<Randomized, String> {

    private final LayerWidget mainLayer;
    private final LayerWidget connectionLayer;

    public RandomizedGraphScene() {
        mainLayer = new LayerWidget(this);
        addChild(mainLayer);
        connectionLayer = new LayerWidget(this);
        addChild(connectionLayer);
        Randomized object1 = new Randomized();
        Randomized object2 = new Randomized();
        Randomized object3 = new Randomized();
        addNode(object1);
        addNode(object2);
        addNode(object3);
        addEdge("Edge 1");
        addEdge("Edge 2");
        setEdgeSource("Edge 1", object1);
        setEdgeTarget("Edge 1", object2);
        setEdgeSource("Edge 2", object3);
        setEdgeTarget("Edge 2", object1);
        GridGraphLayout layout = new GridGraphLayout();
        SceneLayout sceneLayout = LayoutFactory.createSceneGraphLayout(this, layout);
        sceneLayout.invokeLayout();
    }

    @Override
    protected Widget attachNodeWidget(Randomized node) {
        IconNodeWidget widget = new IconNodeWidget(this);
        widget.getLabelWidget().setLabel(String.valueOf(node.getIndex()));
        widget.getActions().addAction(ActionFactory.createMoveAction());
        mainLayer.addChild(widget);
        return (widget);
    }

    @Override
    protected Widget attachEdgeWidget(String edge) {
        ConnectionWidget widget = new ConnectionWidget(this);
        widget.setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED);
        widget.setRouter(RouterFactory.createOrthogonalSearchRouter(
                mainLayer,
                connectionLayer));
        connectionLayer.addChild(widget);
        return widget;
    }

    @Override
    protected void attachEdgeSourceAnchor(
            String edge,
            Randomized oldSourceNode,
            Randomized sourceNode) {
        ConnectionWidget c = (ConnectionWidget) findWidget(edge);
        Widget widget = findWidget(sourceNode);
        Anchor a = AnchorFactory.createRectangularAnchor(widget);
        c.setSourceAnchor(a);
    }

    @Override
    protected void attachEdgeTargetAnchor(
            String edge,
            Randomized oldSourceNode,
            Randomized sourceNode) {
        ConnectionWidget c = (ConnectionWidget) findWidget(edge);
        Widget widget = findWidget(sourceNode);
        Anchor a = AnchorFactory.createRectangularAnchor(widget);
        c.setTargetAnchor(a);
    }

}
