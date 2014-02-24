package org.carsales.viewer;
import java.awt.FlowLayout;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;
import javax.swing.JLabel;
import org.carsales.api.Car;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
@TopComponent.Description(
        preferredID = "CarEditorTopComponent",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(
        mode = "editor",
        openAtStartup = true)
@NbBundle.Messages({
    "CTL_CarEditor=CarEditor Window",
    "HINT_CarEditor=This is a CarEditor window"
})
public class CarEditorTopComponent extends TopComponent {
    public CarEditorTopComponent() {
        setName(Bundle.CTL_CarEditor());
        setToolTipText(Bundle.HINT_CarEditor());
        setLayout(new FlowLayout(FlowLayout.LEFT));
        final JLabel droppedBrandLabel = new JLabel();
        add(droppedBrandLabel);
        DropTarget dt = new DropTarget(this, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                if (dtde.isDataFlavorSupported(Car.DATA_FLAVOR)) {
                    try {
                        Object transData = dtde.getTransferable().getTransferData(Car.DATA_FLAVOR);
                        if (transData instanceof Car) {
                            dtde.acceptDrop(DnDConstants.ACTION_COPY);
                            Car c = (Car) dtde.getTransferable().getTransferData(Car.DATA_FLAVOR);
                            droppedBrandLabel.setText(c.getBrand());
                        }
                    } catch (UnsupportedFlavorException ufe) {
                        dtde.rejectDrop();
                        dtde.dropComplete(true);
                    } catch (IOException ioe) {
                        dtde.rejectDrop();
                        dtde.dropComplete(false);
                    }
                } else {
                    dtde.rejectDrop();
                    dtde.dropComplete(false);
                }
            }
        });
        dt.setDefaultActions(DnDConstants.ACTION_COPY);
        dt.setActive(true);
    }
}
