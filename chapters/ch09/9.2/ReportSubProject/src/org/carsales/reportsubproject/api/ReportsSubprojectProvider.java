package org.carsales.reportsubproject.api;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import org.carsales.reportsubproject.ReportsSubProject;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.spi.project.SubprojectProvider;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.Utilities;

public class ReportsSubprojectProvider implements SubprojectProvider {

    private final Project p;

    public ReportsSubprojectProvider(Project p) {
        this.p = p;
    }
    
    @Override
    public Set<? extends Project> getSubprojects() {
        return loadProjects(p.getProjectDirectory());
    }

    private Set loadProjects(FileObject dir) {
        Set newProjects = new HashSet();
        FileObject reportsFolder = dir.getFileObject("reports");
        if (reportsFolder != null) {
            for (FileObject childFolder : reportsFolder.getChildren()) {
                try {
                    Project subp = ProjectManager.getDefault().
                            findProject(childFolder);
                    if (subp != null && subp instanceof ReportsSubProject) {
                        newProjects.add((ReportsSubProject) subp);
                    }
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (IllegalArgumentException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        return Collections.unmodifiableSet(newProjects);
    }

    @Override
    public void addChangeListener(ChangeListener cl) {
    }

    @Override
    public void removeChangeListener(ChangeListener cl) {
    }

}
