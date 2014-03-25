package org.carsales.project.templates.files;
import org.netbeans.spi.project.ProjectServiceProvider;
import org.netbeans.spi.project.ui.PrivilegedTemplates;
@ProjectServiceProvider(
        service = PrivilegedTemplates.class,
        projectType = "org-carsales-project")
public class CarPrivilegedTemplates implements PrivilegedTemplates {
    private static final String[] PRIVILEGED_NAMES = new String[]{
        "Templates/Other/file",
        "Templates/Licenses/license-apache20.txt",
        "Templates/Other/Folder",
    };
    @Override
    public String[] getPrivilegedTemplates() {
        return PRIVILEGED_NAMES;
    }
}
