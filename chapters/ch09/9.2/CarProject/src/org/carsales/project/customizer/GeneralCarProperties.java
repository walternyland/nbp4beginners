package org.carsales.project.customizer;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.netbeans.spi.project.ui.support.ProjectCustomizer.Category;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
public class GeneralCarProperties implements ProjectCustomizer.CompositeCategoryProvider {
    private static final String GENERAL = "General";
    @ProjectCustomizer.CompositeCategoryProvider.Registration(
            projectType = "org-carsales-project", position = 10)
    public static GeneralCarProperties createGeneral() {
        return new GeneralCarProperties();
    }
    @NbBundle.Messages("LBL_Config_General=General")
    @Override
    public Category createCategory(Lookup lkp) {
        return ProjectCustomizer.Category.create(
                GENERAL,
                Bundle.LBL_Config_General(),
                null);
    }
    @Override
    public JComponent createComponent(Category category, Lookup lkp) {
        return new JPanel();
    }
}
