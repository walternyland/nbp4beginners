package org.carsales.project.lookup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
@ActionID(category = "File", id = "org.carsales.project.TestAction")
@ActionRegistration(displayName = "#CTL_TestAction")
@ActionReference(path = "Menu/File", position = 0)
@Messages("CTL_TestAction=Test")
public final class TestAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "===> running action");
        for (Project p : OpenProjects.getDefault().getOpenProjects()) {
            Service s = p.getLookup().lookup(Service.class);
            if (s != null) {
                JOptionPane.showMessageDialog(null, "===> got a service: " + s.m());
            } else {
                JOptionPane.showMessageDialog(null, "===> nothing for " + p);
            }
        }
    }
}
