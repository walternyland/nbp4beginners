package org.carsales.core.annotations;

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
@SupportedAnnotationTypes("org.carsales.core.annotations.AboutBox")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class AboutBoxProcessor extends LayerGeneratingProcessor {

    @Override
    protected boolean handleProcess(Set<? extends TypeElement> set, RoundEnvironment env) throws LayerGenerationException {
        Elements elements = processingEnv.getElementUtils();
        for (Element e : env.getElementsAnnotatedWith(AboutBox.class)) {
            TypeElement clazz = (TypeElement) e;
            AboutBox aboutBox = clazz.getAnnotation(AboutBox.class);
            String teName = elements.getBinaryName(clazz).toString();
            File f = layer(e).file("AboutBox/" + teName.replace('.', '-') + ".instance").intvalue("position", aboutBox.position()).bundlevalue("displayName", aboutBox.displayName());
            f.write();
        }
        return true;
    }
    
}
