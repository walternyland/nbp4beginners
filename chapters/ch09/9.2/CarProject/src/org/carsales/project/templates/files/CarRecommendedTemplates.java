package org.carsales.project.templates.files;
import org.netbeans.spi.project.ProjectServiceProvider;
import org.netbeans.spi.project.ui.RecommendedTemplates;
@ProjectServiceProvider(
        service = RecommendedTemplates.class,
        projectType = "org-carsales-project")
public class CarRecommendedTemplates implements RecommendedTemplates {
    private static final String[] RECOMMENDED_NAMES = new String[]{
        "simple-files"
    };
    @Override
    public String[] getRecommendedTypes() {
        return RECOMMENDED_NAMES;
    }
}
