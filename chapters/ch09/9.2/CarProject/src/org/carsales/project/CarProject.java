package org.carsales.project;

import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

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
                // register your features here
            });
        }
        return lkp;
    }
    
}
