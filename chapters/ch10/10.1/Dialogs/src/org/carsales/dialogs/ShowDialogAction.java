package org.carsales.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "org.carsales.dialogs.ShowDialogAction"
)
@ActionRegistration(
        displayName = "#CTL_ShowDialogAction"
)
@ActionReference(path = "Menu/Tools", position = 0)
@Messages("CTL_ShowDialogAction=Show Dialog")
public final class ShowDialogAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
//        NotifyDescriptor d = new NotifyDescriptor( 
//                "Message", // message 
//                "Title", // title 
//                NotifyDescriptor.OK_CANCEL_OPTION, // option type 
//                NotifyDescriptor.INFORMATION_MESSAGE, // message type 
//                null, // own buttons as Object[] 
//                null); // initial value 
//        DialogDisplayer.getDefault().notify(d);
        Exception ex = new Exception("An exception has occurred");
        NotifyDescriptor d = new NotifyDescriptor.Exception(ex);
        DialogDisplayer.getDefault().notify(d);
    }

}
