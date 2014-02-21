package org.carsales.carviewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import javax.swing.border.LineBorder;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;

public class RandomizedScene extends Scene {

    private final Random generator = new Random();
    private static int count = 0;

    public RandomizedScene() {
        LayerWidget layerWidget = new LayerWidget(this);
        for (int i = 0; i < 3; i++) {
            layerWidget.addChild(new RandomWidget());
        }
        addChild(layerWidget);
    }

    class RandomWidget extends LabelWidget {

        public RandomWidget() {
            super(RandomizedScene.this);
            int x = generator.nextInt(600);
            int y = generator.nextInt(300);
            setLabel("Index: " + count++);
            setPreferredLocation(new Point(x, y));
            setPreferredSize(new Dimension(x, y));
            setBorder(new LineBorder(Color.BLACK));
            getActions().addAction(ActionFactory.createMoveAction());
        }
    }
    
}
