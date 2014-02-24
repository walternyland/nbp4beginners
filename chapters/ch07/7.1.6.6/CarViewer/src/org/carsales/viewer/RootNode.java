package org.carsales.viewer;
import java.beans.IntrospectionException;
import java.io.IOException;
import javax.swing.Action;
import org.carsales.api.Car;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.actions.NewAction;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.InstanceDataObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Exceptions;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;
public class RootNode extends AbstractNode {
    public RootNode(Children carChildren) throws IntrospectionException {
        super(carChildren);
        if (FileUtil.getConfigFile("Cars") == null) {
            try {
                FileUtil.getConfigRoot().createFolder("Cars");
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{SystemAction.get(NewAction.class)};
    }
    @Override
    public NewType[] getNewTypes() {
        return new NewType[]{new NewType() {
            @Override
            public String getName() {
                return "Car";
            }
            @Override
            public void create() throws IOException {
                NotifyDescriptor.InputLine msg
                        = new NotifyDescriptor.InputLine(
                                "Car Brand:",
                                "Create New Car");
                DialogDisplayer.getDefault().notify(msg);
                Car bean = new Car();
                bean.setBrand(msg.getInputText());
                bean.setBrand(msg.getInputText());
                InstanceDataObject.create(
                        DataFolder.findFolder(FileUtil.getConfigFile("Cars")),
                        bean.getBrand(),
                        bean,
                        null,
                        true
                );
            }
        }};
    }
}
