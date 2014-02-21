package org.carsales.graph;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.netbeans.api.visual.widget.Scene;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "org.carsales.car.graph.ExportImageAction")
@ActionRegistration(
        displayName = "#CTL_ExportImageAction"
)
@Messages("CTL_ExportImageAction=Export Image")
public final class ExportImageAction implements ActionListener {

    private final Scene sc;

    public ExportImageAction(Scene context) {
        this.sc = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        BufferedImage img = new BufferedImage(
                sc.getView().getWidth(),
                sc.getView().getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = img.createGraphics();
        sc.paint(graphics);
        graphics.dispose();
        File f = new FileChooserBuilder("libraries-dir").
                setTitle("Export Image").
                setApproveText("Save").
                setFileFilter(
                        new FileNameExtensionFilter(
                                "Portable Network Graphics (.png)",
                                "png")).
                showOpenDialog();
        if (f != null) {
            if (!f.getName().toLowerCase().endsWith(".png")) {
                f = new File(f.getParentFile(), f.getName() + ".png");
            }
            try {
                ImageIO.write(img, "png", f);
            } catch (IOException e) {
            }
        }
    }
}
