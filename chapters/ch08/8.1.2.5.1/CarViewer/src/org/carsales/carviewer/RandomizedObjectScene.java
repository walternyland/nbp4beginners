package org.carsales.carviewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.border.LineBorder;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import org.openide.awt.StatusDisplayer;

public class RandomizedObjectScene extends ObjectScene {

    public RandomizedObjectScene() {
        LayerWidget layerWidget = new LayerWidget(this);
        for (int i = 0; i < 3; i++) {
            Randomized r = new Randomized();
            RandomWidget numberWidget = new RandomWidget(r.getX(), r.getY());
            layerWidget.addChild(numberWidget);
            addObject(r, numberWidget);
        }
        addChild(layerWidget);
    }

    class RandomWidget extends LabelWidget {

        public RandomWidget(int x, int y) {
            super(RandomizedObjectScene.this);
            setPreferredLocation(new Point(x, y));
            setPreferredSize(new Dimension(x, y));
            setBorder(new LineBorder(Color.BLACK));
            getActions().addAction(ActionFactory.createMoveAction(
                    ActionFactory.createSnapToGridMoveStrategy(10, 10),
                    new RandomWidgetMoveProvider()));

        }
    }

    private class RandomWidgetMoveProvider implements MoveProvider {

        @Override
        public void movementStarted(Widget widget) {
        }

        @Override
        public void movementFinished(Widget widget) {
            Randomized object = (Randomized) findObject(widget);
            String data = "You moved index: " + object.getIndex();
            StatusDisplayer.getDefault().setStatusText(data);
        }

        @Override
        public Point getOriginalLocation(Widget widget) {
            return widget.getPreferredLocation();
        }

        @Override
        public void setNewLocation(Widget widget, Point location) {
            widget.setPreferredLocation(location);
        }
    }

}
