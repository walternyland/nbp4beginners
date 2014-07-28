package org.carsales.reportsubproject;
import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;
@ServiceProvider(service = ProjectFactory.class)
public class ReportsSubProjectFactory implements ProjectFactory {
    public static final String PROJECT_FILE = "report.rpt";
    //Specifies when a project is a project, i.e.,
    //if "report.rpt" is present in a folder:
    @Override
    public boolean isProject(FileObject projectDirectory) {
        return projectDirectory.getFileObject(PROJECT_FILE) != null;
    }
    //Specifies when the project will be opened, i.e., if the project exists:
    @Override
    public Project loadProject(FileObject dir, ProjectState state) throws IOException {
        // leave state unused, not needed
        return isProject(dir) ? new ReportsSubProject(dir) : null;
    }
    @Override
    public void saveProject(final Project project) throws IOException, ClassCastException {
        // leave unimplemented, not needed
    }
}
