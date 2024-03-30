package de.zonlykroks.advancedchemistry.util.annotation;

import de.zonlykroks.advancedchemistry.AdvancedChemistry;
import de.zonlykroks.advancedchemistry.util.ReflectionUtil;
import de.zonlykroks.advancedchemistry.util.annotation.anno.ClassInit;
import de.zonlykroks.advancedchemistry.util.annotation.anno.CreativeGroupRegister;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class AnnotationProcessor {

    private static boolean inited = false;

    public static void processAnnotations() {
        if (inited) {
            throw new IllegalStateException("Already inited");
        }

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("de.zonlykroks.advancedchemistry"))
                .setScanners(Scanners.TypesAnnotated));

        reflections.getTypesAnnotatedWith(ClassInit.class).forEach(aClass -> {
            try {
                System.out.println(aClass);
                aClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        reflections.getTypesAnnotatedWith(CreativeGroupRegister.class).forEach(aClass -> {
            AdvancedChemistry.toPlaceInGroup.addAll(ReflectionUtil.collectBlockFieldsFromClass(aClass));
        });

        inited = true;
    }
}
