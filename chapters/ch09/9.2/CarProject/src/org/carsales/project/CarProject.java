package org.carsales.project;
import org.carsales.project.customizer.CarCustomizerProvider;
import java.awt.Image;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.support.LookupProviderSupport;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;
public class CarProject implements Project {
    private final FileObject projectDir;
    private Lookup lkp;
    CarProject(FileObject dir) {
        this.projectDir = dir;
    }
    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }
    @Override
    public Lookup getLookup() {
        if (lkp == null) {
            lkp = Lookups.fixed(new Object[]{
                this,
                new Info(),
                new CarProjectLogicalView(this),
                new CarCustomizerProvider(this)
            });
        }
        return LookupProviderSupport.createCompositeLookup(
                lkp, 
                "Projects/org-carsales-project/Lookup");
    }
    class CarProjectLogicalView implements LogicalViewProvider {
        @StaticResource()
        public static final String CAR_ICON = "org/carsales/project/icon.png";
        private final CarProject project;
        public CarProjectLogicalView(CarProject project) {
            this.project = project;
        }
        @Override
        public Node createLogicalView() {
            try {
                //Obtain the project directory's node:
                FileObject projectDirectory = project.getProjectDirectory();
                DataFolder projectFolder = DataFolder.findFolder(projectDirectory);
                Node nodeOfProjectFolder = projectFolder.getNodeDelegate();
                //Decorate the project directory's node:
                return new ProjectNode(nodeOfProjectFolder, project);
            } catch (DataObjectNotFoundException donfe) {
                Exceptions.printStackTrace(donfe);
                //Fallback-the directory couldn't be created -
                //read-only filesystem or something evil happened
                return new AbstractNode(Children.LEAF);
            }
        }
        private final class ProjectNode extends FilterNode {
            final CarProject project;
            public ProjectNode(Node node, CarProject project)
                    throws DataObjectNotFoundException {
                super(node,
                        NodeFactorySupport.createCompositeChildren(
                                project,
                                "Projects/org-carsales-project/Nodes"),
                        //new FilterNode.Children(node),
                        new ProxyLookup(
                                new Lookup[]{
                                    Lookups.singleton(project),
                                    node.getLookup()
                                }));
                this.project = project;
            }
            @Override
            public Action[] getActions(boolean arg0) {
                return new Action[]{
                    CommonProjectActions.newFileAction(),
                    CommonProjectActions.copyProjectAction(),
                    CommonProjectActions.deleteProjectAction(),
                    CommonProjectActions.customizeProjectAction(),
                    CommonProjectActions.closeProjectAction()
                };
            }
            @Override
            public Image getIcon(int type) {
                return ImageUtilities.loadImage(CAR_ICON);
            }
            @Override
            public Image getOpenedIcon(int type) {
                return getIcon(type);
            }
            @Override
            public String getDisplayName() {
                return project.getProjectDirectory().getName();
            }
        }
        @Override
        public Node findPath(Node root, Object target) {
            //leave unimplemented for now
            return null;
        }
    }
    private final class Info implements ProjectInformation {
        @StaticResource()
        public static final String CAR_ICON = "org/carsales/project/icon.png";
        @Override
        public Icon getIcon() {
            return new ImageIcon(ImageUtilities.loadImage(CAR_ICON));
        }
        @Override
        public String getName() {
            return getProjectDirectory().getName();
        }
        @Override
        public String getDisplayName() {
            return getName();
        }
        @Override
        public void addPropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }
        @Override
        public void removePropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }
        @Override
        public Project getProject() {
            return CarProject.this;
        }
    }
}
