package org.carsales.viewer;

import java.awt.Image;
import org.netbeans.core.spi.multiview.MultiViewDescription;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

@Messages("LBL_CarMultiView2=Visual 2")
class CarMultiView2Description implements MultiViewDescription {
    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;
    }
    @Override
    public String getDisplayName() {
        return Bundle.LBL_CarMultiView2();
    }
    @Override
    public Image getIcon() {
        return null;
    }
    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    @Override
    public String preferredID() {
        return Bundle.LBL_CarMultiView2();
    }
    @Override
    public MultiViewElement createElement() {
        return new CarMultiView1(Utilities.actionsGlobalContext());
    }
}