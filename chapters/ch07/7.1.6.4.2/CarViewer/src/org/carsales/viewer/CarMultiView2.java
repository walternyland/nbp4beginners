package org.carsales.viewer;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import org.carsales.api.Car;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.openide.awt.UndoRedo;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@MultiViewElement.Registration(
        displayName = "#LBL_CarMultiView2",
        mimeType = "application/x-carnode",
        persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
        preferredID = "CarMultiView2",
        position = 10)
@Messages("LBL_CarMultiView2=Visual 2")
public class CarMultiView2 extends JPanel implements MultiViewElement {
    Car car;
    public CarMultiView2(Lookup lookup) {
        this.car = lookup.lookup(Car.class);
    }
    @Override
    public JComponent getVisualRepresentation() {
        return this;
    }
    @Override
    public JComponent getToolbarRepresentation() {
        JToolBar jtb = new JToolBar();
        jtb.setFloatable(false);
        return jtb;
    }
    @Override
    public Action[] getActions() {
        return null;
    }
    @Override
    public Lookup getLookup() {
        return Lookup.EMPTY;
    }
    @Override public void componentOpened() {}
    @Override public void componentClosed() {}
    @Override public void componentShowing() {}
    @Override public void componentHidden() {}
    @Override public void componentActivated() {}
    @Override public void componentDeactivated() {}
    @Override
    public UndoRedo getUndoRedo() {
        return UndoRedo.NONE;
    }
    @Override
    public void setMultiViewCallback(MultiViewElementCallback mvec) {
        mvec.getTopComponent().setDisplayName(car.getBrand());
    }
    @Override
    public CloseOperationState canCloseElement() {
        return CloseOperationState.STATE_OK;
    }
}
