package org.carsales.carviewer;

import java.awt.Color;
import java.awt.Point;
import javax.swing.border.LineBorder;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.vmd.VMDGraphScene;
import org.netbeans.api.visual.vmd.VMDNodeWidget;
import org.netbeans.api.visual.vmd.VMDPinWidget;

class RandomizedVMDGraphScene extends VMDGraphScene {
    public RandomizedVMDGraphScene() {
        for (int i = 0; i < 3; i++) {
            Randomized object = new Randomized();
            addChild(getVMDNodeWidget(object));
        }
    }
    private VMDNodeWidget getVMDNodeWidget(Randomized object) {
        VMDNodeWidget vmdnw = new VMDNodeWidget(this);
        int x = object.getX();
        int y = object.getY();
        String msg = "Index: " + object.getIndex();
        vmdnw.setNodeName(msg);
        vmdnw.setPreferredLocation(new Point(x, y));
        vmdnw.setBorder(new LineBorder(Color.BLACK));
        vmdnw.getActions().addAction(ActionFactory.createMoveAction());
        for (int i = 0; i < 4; i++) {
            vmdnw.addChild(getVMDPinWidget(String.valueOf(i)));
            vmdnw.setCheckClipping(true);
        }
        return vmdnw;
    }
    private VMDPinWidget getVMDPinWidget(String msg) {
        VMDPinWidget widget = new VMDPinWidget(this);
        widget.setPinName(msg);
        return widget;
    }
}
