package org.simple.listener;

import java.awt.EventQueue;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.openide.modules.OnStart;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.openide.util.WeakListeners;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

@OnStart
public class Installer implements Runnable, LookupListener {
    private Lookup.Result<Object> result = null;
    private final OutputWriter writer;
    private final InputOutput io = IOProvider.getDefault().getIO("Selected Objects", true);
    private final DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    public Installer() {
        result = Utilities.actionsGlobalContext().lookupResult(Object.class);
        result.addLookupListener(WeakListeners.create(LookupListener.class, this, result));
        writer = io.getOut();
    }
    @Override
    public void run() {
        EventQueue.invokeLater(this);
    }
    @Override
    public void resultChanged(LookupEvent e) {
        for (Object o : result.allInstances()) {
            String formatted = formatter.format(System.currentTimeMillis());
            writer.println(formatted + ": " + o.getClass().getSimpleName());
        }
    }
}
