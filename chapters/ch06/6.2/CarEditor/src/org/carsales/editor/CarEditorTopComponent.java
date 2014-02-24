package org.carsales.editor;
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
    }
}
