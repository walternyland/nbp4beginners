package org.carsales.api;

import java.awt.Image;
import java.util.Properties;
import org.openide.util.ImageUtilities;

public class Part {
    private final Properties props;
    private Image icon16;
    public static final String PROP_NAME = "name";
    public static final String PROP_WIDTH = "width";
    public static final String PROP_HEIGHT = "height";
    public static final String PROP_ICON16 = "icon16";
    public Part(Properties props) {
        this.props = props;
        loadIcons();
    }
    public String getName() {
        return props.getProperty(PROP_NAME);
    }
    public String getWidth() {
        return props.getProperty(PROP_WIDTH);
    }
    public String getHeight() {
        return props.getProperty(PROP_HEIGHT);
    }
    public Image getSmallImage() {
        return icon16;
    }
    private void loadIcons() {
        String iconId = props.getProperty(PROP_ICON16);
        icon16 = ImageUtilities.loadImage(iconId);
    }
}
