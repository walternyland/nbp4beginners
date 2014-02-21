package org.carsales.api.presenter;

import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.*;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.actions.Presenter;

public final class GenericNodePopupPresenter 
        extends AbstractAction 
        implements Presenter.Popup {
    private final Map map;
static GenericNodePopupPresenter create(Map m) {
    return new GenericNodePopupPresenter(m);
}
    public GenericNodePopupPresenter(Map m) {
        this.map = m;
    }
    @Override
    public JMenuItem getPopupPresenter() {
        String mainMenuDisplayName = map.get("displayName").toString();
        JMenu mainPopupMenu = new JMenu(mainMenuDisplayName);
        FileObject subs = 
                FileUtil.getConfigFile(map.get("pathToSubPopupMenus").toString());
        if (subs != null) {
            for (FileObject oneSub : subs.getChildren()) {
                Action a = FileUtil.getConfigObject(oneSub.getPath(), Action.class);
                mainPopupMenu.add(new JMenuItem(a));
            }
        }
        return mainPopupMenu;
    }
    @Override
    public void actionPerformed(ActionEvent ev) {
        //nothing happens here
        //because the main popup menu
        //is only a container for a sub popup menu
        //and cannot itself perform an action
    }
}