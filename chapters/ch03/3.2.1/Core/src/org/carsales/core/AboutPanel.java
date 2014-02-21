package org.carsales.core;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

public class AboutPanel extends JPanel {

    public AboutPanel() {
        setLayout(new BorderLayout());
        JTabbedPane jtp = new JTabbedPane();
        FileObject aboutBoxPanels[] = FileUtil.getConfigFile("AboutBox").getChildren();
        List<FileObject> orderedaboutBoxPanels
                = FileUtil.getOrder(Arrays.asList(aboutBoxPanels), true);
        for (FileObject aboutBoxFO : orderedaboutBoxPanels) {
            JPanel panel = FileUtil.getConfigObject(aboutBoxFO.getPath(), JPanel.class);
            String title = (String) aboutBoxFO.getAttribute("displayName");
            jtp.addTab(title, panel);
        }
        add(jtp, BorderLayout.CENTER);
    }

}
