package org.carsales.graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import org.carsales.api.Part;
import org.netbeans.api.visual.action.AcceptProvider;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.action.TwoStateHoverProvider;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.awt.Actions;
import org.openide.nodes.Node;
import org.openide.nodes.NodeTransfer;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;

@TopComponent.Description(
        preferredID = "CarEditorTopComponent",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(
        mode = "editor",
        openAtStartup = true)
public class CarEditorTopComponent extends TopComponent {

    private final Scene scene;
    private final LayerWidget mainLayer;
    private final LayerWidget connectionLayer;

    public CarEditorTopComponent() {
        setLayout(new BorderLayout());
        scene = new Scene();
        scene.addChild(mainLayer = new LayerWidget(scene));
        scene.addChild(connectionLayer = new LayerWidget(scene));
        scene.getActions().addAction(ActionFactory.createAcceptAction(new PartAcceptProvider(scene, mainLayer)));
        scene.getActions().addAction(
                ActionFactory.createPopupMenuAction(new PopupMenuProvider() {
                    @Override
                    public JPopupMenu getPopupMenu(Widget widget, Point point) {
                        JPopupMenu jpm = new JPopupMenu();
                        Action exportImageAction = Actions.forID(
                                "Tools",
                                "org.carsales.car.graph.ExportImageAction");
                        jpm.add(new JMenuItem(exportImageAction));
                        return jpm;
                    }
                }));
//        mainLayer.setLayout(new CarLayout());
        add(new JScrollPane(scene.createView()));
        associateLookup(Lookups.fixed(CarPaletteFactory.getCarPalette(), scene));
    }

    private class PartAcceptProvider implements AcceptProvider {

        private final Scene scene;
        private final LayerWidget mainLayer;

        public PartAcceptProvider(Scene scene, LayerWidget mainLayer) {
            this.scene = scene;
            this.mainLayer = mainLayer;
        }

        @Override
        public ConnectorState isAcceptable(Widget widget, Point point, Transferable t) {
            Node node = NodeTransfer.node(t, NodeTransfer.DND_COPY_OR_MOVE);
            if (node.getLookup().lookup(Part.class) != null) {
                return ConnectorState.ACCEPT;
            } else {
                return ConnectorState.REJECT;
            }
        }

        @Override
        public void accept(Widget widget, Point point, Transferable t) {
            Node node = NodeTransfer.node(t, NodeTransfer.DND_COPY_OR_MOVE);
            Part part = node.getLookup().lookup(Part.class);
            mainLayer.addChild(new PartWidget(scene, part, point));
        }
    }

    private class PartWidget extends LabelWidget {

        public PartWidget(Scene scene, Part part, Point point) {
            super(scene);
            int width = Integer.parseInt(part.getWidth());
            int height = Integer.parseInt(part.getHeight());
            setLabel(part.getName());
            setBorder(new LineBorder(Color.BLACK, 2));
            setPreferredLocation(point);
            setPreferredSize(new Dimension(width, height));
            getActions().addAction(ActionFactory.createResizeAction());
            WidgetAction hoverAction
                    = ActionFactory.createHoverAction(
                            new TwoStateHoverProvider() {
                                @Override
                                public void unsetHovering(Widget w) {
                                    w.setBorder(BorderFactory.createLineBorder(1));
                                }

                                @Override
                                public void setHovering(Widget w) {
                                    w.setBorder(
                                            BorderFactory.createResizeBorder(
                                                    8, Color.BLACK, false));
                                }
                            });
            getActions().addAction(hoverAction);
            scene.getActions().addAction(hoverAction);
            getActions().addAction(
                    ActionFactory.createExtendedConnectAction(
                            connectionLayer,
                            new PartConnectProvider()));
            getActions().addAction(
                    ActionFactory.createAlignWithMoveAction(
                            mainLayer,
                            connectionLayer,
                            ActionFactory.createDefaultAlignWithMoveDecorator()));
            getActions().addAction(
                    ActionFactory.createPopupMenuAction(new PopupMenuProvider() {
                        @Override
                        public JPopupMenu getPopupMenu(Widget widget, Point point) {
                            JPopupMenu jpm = new JPopupMenu();
                            jpm.add(new AbstractAction("Delete") {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    mainLayer.removeChild(PartWidget.this);
                                }
                            });
                            return jpm;
                        }
                    }));

        }

        private class PartConnectProvider implements ConnectProvider {

            @Override
            public boolean isSourceWidget(Widget source) {
                return source != null && source instanceof PartWidget;
            }

            @Override
            public ConnectorState isTargetWidget(Widget src, Widget trg) {
                return src != trg && trg instanceof PartWidget
                        ? ConnectorState.ACCEPT : ConnectorState.REJECT;
            }

            @Override
            public boolean hasCustomTargetWidgetResolver(Scene arg0) {
                return false;
            }

            @Override
            public Widget resolveTargetWidget(Scene arg0, Point arg1) {
                return null;
            }

            @Override
            public void createConnection(Widget source, Widget target) {
                ConnectionWidget conn = new ConnectionWidget(scene);
                conn.setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED);
                conn.setTargetAnchor(AnchorFactory.createRectangularAnchor(target));
                conn.setSourceAnchor(AnchorFactory.createRectangularAnchor(source));
                connectionLayer.addChild(conn);
            }
        }

    }
}
