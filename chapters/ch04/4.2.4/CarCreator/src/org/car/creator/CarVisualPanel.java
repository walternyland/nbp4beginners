package org.car.creator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.openide.util.NbBundle.Messages;

public class CarVisualPanel extends JPanel {

    private final JTextField nameField;

    @Messages({
        "nameLabel=Name:",
    })
    public CarVisualPanel() {
        add(new JLabel(Bundle.nameLabel()));
        add(nameField = new JTextField(30));
    }

    public String getCarName() {
        return nameField.getText();
    }

    @Messages({
        "wizardStepLabel=Name:",
    })
    @Override
    public String getName() {
        return "Define Name";
    }

}
