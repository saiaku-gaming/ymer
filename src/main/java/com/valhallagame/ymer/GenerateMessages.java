package com.valhallagame.ymer;

import com.squareup.javapoet.*;
import com.valhallagame.common.AllwaysExposedInYmer;
import com.valhallagame.common.ExposedNameInYmer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateMessages {

    private static final Logger logger = LoggerFactory.getLogger(GenerateMessages.class);

    public static void generateSeviceMessages() throws IOException {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.valhallagame")).setScanners(new SubTypesScanner(false))
                .filterInputsBy(new FilterBuilder().includePackage(
                        "com.valhallagame.wardrobeserviceclient.message",
                        "com.valhallagame.traitserviceclient.message",
                        "com.valhallagame.statisticsserviceclient.message",
                        "com.valhallagame.personserviceclient.message",
                        "com.valhallagame.partyserviceclient.message",
                        "com.valhallagame.notificationserviceclient.message",
                        "com.valhallagame.instanceserviceclient.message",
                        "com.valhallagame.friendserviceclient.message",
                        "com.valhallagame.featserviceclient.message",
                        "com.valhallagame.chatserviceclient.message",
                        "com.valhallagame.characterserviceclient.message",
                        "com.valhallagame.actionbarserviceclient.message",
                        "com.valhallagame.currencyserviceclient.message",
                        "com.valhallagame.recipeserviceclient.message")
                )
        );

        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);

        for (Class<?> c : allClasses) {
            logger.debug("Class Name: {}", c.getSimpleName());
            logger.debug("Packgage Name: {}", c.getPackage().getName());

            List<FieldSpec> fields = new ArrayList<>();
            for (Field f : c.getDeclaredFields()) {
                if (f.getName().equals("username") && f.getAnnotation(AllwaysExposedInYmer.class) == null) {
                    continue;
                }

                String fieldName;

                // Intellij breaks f.getAnnotation(Classname.class) with some strange Proxy objects so we need to make some strange moves instead

                // First we match on simple name as both the real deal and the proxy has the same simple name
                Optional<Annotation> annotationOptional = Stream.of(f.getAnnotations())
                        .filter(annotation -> annotation.annotationType().getSimpleName().equals(ExposedNameInYmer.class.getSimpleName()))
                        .findAny();
                if (!annotationOptional.isPresent()) {
                    fieldName = f.getName();
                } else {
                    // If there is a simple name and the annotation is the real thing (aka ExposedNameInYmer.class) then we can just casts and use it
                    if (annotationOptional.get() instanceof ExposedNameInYmer) {
                        ExposedNameInYmer exposed = (ExposedNameInYmer) annotationOptional.get();
                        fieldName = exposed.value();
                    } else {
                        // However if it is a proxy object the cast fails, but we can still
                        // call the value method on the object and get our replacement name.
                        // Strange as hell but it works
                        Annotation a = annotationOptional.get();
                        try {
                            Method valueMethod = a.getClass().getMethod("value");
                            Object invoke = valueMethod.invoke(a);
                            fieldName = (String) invoke;
                        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                            logger.error("Some strange proxy stuff going on with {}, {}", a, a.getClass());
                            fieldName = f.getName();
                        }
                    }
                }

                TypeName typeName;

                if (f.getGenericType() instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) f.getGenericType();
                    typeName = ParameterizedTypeName.get(f.getType(), parameterizedType.getActualTypeArguments());
                } else {
                    typeName = TypeName.get(f.getType());
                }

                fields.add(FieldSpec.builder(typeName, fieldName)
                        .addAnnotations(Arrays.stream(f.getDeclaredAnnotations()).filter(a -> {
                            boolean exposedName = a.annotationType().isAssignableFrom(ExposedNameInYmer.class);
                            boolean alwaysExpoed = a.annotationType().isAssignableFrom(AllwaysExposedInYmer.class);
                            return !exposedName && !alwaysExpoed;
                        }).map(AnnotationSpec::get).collect(Collectors.toList()))
                        .build());

            }

            if (fields.isEmpty()) {
                continue;
            }

            TypeSpec serviceMessage = TypeSpec.classBuilder(c.getSimpleName())
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL).addFields(fields).addAnnotation(Data.class)
                    .addAnnotation(NoArgsConstructor.class).addAnnotation(AllArgsConstructor.class).build();

            String serviceName = c.getPackage().getName().split("\\.")[2].replace("serviceclient", "");

            JavaFile javaFile = JavaFile.builder("com.valhallagame.ymer.message." + serviceName, serviceMessage)
                    .skipJavaLangImports(true).build();

            String thisPath = GenerateMessages.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            // thisPath will be something like /V:/valhalla-proj/backend/ymer/target/classes/
            String root = thisPath.split("target/classes/")[0];

            assert (root.contains("ymer"));

            File javaFolder = new File(root + "src/main/java");

            boolean differ = checkIfFilesDiffer(javaFolder, javaFile);
            if (differ) {
                logger.info("Files differ! Writing {} to {}", javaFolder, javaFile);
                javaFile.writeTo(javaFolder);
            }
        }
    }

    private static boolean checkIfFilesDiffer(File javaFolder, JavaFile javaFile) {

        Path outputDirectory = javaFolder.toPath();
        if (!javaFile.packageName.isEmpty()) {
            for (String packageComponent : javaFile.packageName.split("\\.")) {
                outputDirectory = outputDirectory.resolve(packageComponent);
            }
        }
        Path outputPath = outputDirectory.resolve(javaFile.typeSpec.name + ".java");
        if (!outputPath.toFile().exists()) {
            return true;
        } else {
            StringBuilder out = new StringBuilder();
            try {
                javaFile.writeTo(out);
            } catch (IOException e) {
                logger.error("Problem with javaFile writer ", e);
                return true;
            }
            String newFile = out.toString();
            try {
                String oldFile = new String(Files.readAllBytes(outputPath));
                return !oldFile.equals(newFile);
            } catch (IOException e) {
                logger.error("Problem reading old file", e);
                return true;
            }
        }
    }
}
