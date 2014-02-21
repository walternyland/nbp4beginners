package org.carsales.api;

import java.util.Set;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import org.openide.filesystems.annotations.LayerBuilder.File;
import org.openide.filesystems.annotations.LayerGeneratingProcessor;
import org.openide.filesystems.annotations.LayerGenerationException;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = Processor.class)
@SupportedAnnotationTypes("org.carsales.api.NodePopupMenu")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class NodePopupMenuProcessor extends LayerGeneratingProcessor {
    @Override
    protected boolean handleProcess(
            Set<? extends TypeElement> set, 
            RoundEnvironment env) throws LayerGenerationException {
        Elements elements = processingEnv.getElementUtils();
        for (Element e : env.getElementsAnnotatedWith(NodePopupMenu.class)) {
            TypeElement clazz = (TypeElement) e;
            NodePopupMenu mpm = clazz.getAnnotation(NodePopupMenu.class);
            String teName = elements.getBinaryName(clazz).toString();
            File f = layer(e).file(
                    "PopupMenu/" + mpm.path() + "/" +teName.replace('.', '-') + ".instance").
                    bundlevalue("displayName", mpm.displayName()).
                    stringvalue("pathToSubPopupMenus", mpm.pathToSubPopupMenus()).
                    methodvalue(
                            "instanceCreate", 
                            "org.carsales.api.presenter.GenericNodePopupPresenter", 
                            "create").
                    intvalue("position", mpm.position());
            f.write();
        }
        return true;
    }
 
}
