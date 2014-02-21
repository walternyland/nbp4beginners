package org.carsales.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

public class AboutDialog extends JPanel {

    public AboutDialog() {
        setLayout(new BorderLayout());
        setSize(new Dimension(400, 400));
        JTabbedPane jtp = new JTabbedPane();
        FileObject aboutBoxPanels[] = 
                FileUtil.getConfigFile("AboutBox").getChildren();
        List<FileObject> orderedaboutBoxPanels = 
                FileUtil.getOrder(Arrays.asList(aboutBoxPanels), true);
        for (FileObject aboutBoxFO : orderedaboutBoxPanels) {
            JPanel panel = 
                FileUtil.getConfigObject(aboutBoxFO.getPath(), JPanel.class);
            String title = (String) aboutBoxFO.getAttribute("displayName");
            jtp.addTab(title, panel);
        }
        add(jtp, BorderLayout.CENTER);
    }

}
