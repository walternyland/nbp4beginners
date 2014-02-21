package org.carsales.viewer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.IOColorLines;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

@ActionID(
        category = "Edit",
        id = "org.carsales.viewer.OutputAction"
)
@ActionRegistration(
        displayName = "#CTL_OutputAction"
)
@ActionReference(path = "Menu/Tools", position = 0)
@Messages("CTL_OutputAction=Print Output")
public final class OutputAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
//        InputOutput io = IOProvider.getDefault().getIO("Task", true);
//        io.select();
//        OutputWriter writer = io.getOut();
//        writer.println("Info message");
//        writer.close();
        try {
            InputOutput io = IOProvider.getDefault().getIO("Colorful Output", null);
            IOColorLines.println(io, "Hello...", Color.GREEN);
            IOColorLines.println(io, "...world", Color.RED);
        } catch (IOException ex) {
        }
    }

}
