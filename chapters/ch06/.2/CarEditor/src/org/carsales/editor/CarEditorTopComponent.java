package org.carsales.editor;

import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.actions.Savable;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.StatusDisplayer;
import org.openide.util.NbBundle;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;

@TopComponent.Description(
        preferredID = "CarEditorTopComponent",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(
        mode = "editor",
        openAtStartup = true)
@NbBundle.Messages({
    "CTL_CarViewer=CarEditor Window",
    "HINT_CarViewer=This is a CarEditor window"
})
public class CarEditorTopComponent extends TopComponent {

    private InstanceContent ic = new InstanceContent();

    public CarEditorTopComponent() {
        setName(Bundle.CTL_CarViewer());
        setToolTipText(Bundle.HINT_CarViewer());
        setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField("<empty>");
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                modify();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                modify();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                modify();
            }
        });
        add(nameLabel);
        add(nameField);
        associateLookup(new AbstractLookup(ic));
    }

private Savable nameSavable = new NameSavable();
private void modify() {
    if (getLookup().lookup(NameSavable.class) == null) {
        ic.add(nameSavable);
    }
}

    class NameSavable implements Savable {

        @Override
        public void save() throws IOException {
            NotifyDescriptor.Confirmation msg
                    = new NotifyDescriptor.Confirmation(
                            "Are you sure you want to save?",
                            "Confirmation");
            Object notify = DialogDisplayer.getDefault().notify(msg);
            if (NotifyDescriptor.YES_OPTION == notify) {
                ic.remove(nameSavable);
                StatusDisplayer.getDefault().setStatusText("Car saved!", 1);
            }
        }
    }
}
