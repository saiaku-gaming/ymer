package com.valhallagame.ymer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.valhallagame.common.AllwaysExposedInYmer;
import com.valhallagame.common.ExposedNameInYmer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GenerateMessages {

	private static final Logger logger = LoggerFactory.getLogger(GenerateMessages.class);

	public static void generateSeviceMessages() throws IOException {
		Reflections reflections = new Reflections(new ConfigurationBuilder()
				.setUrls(ClasspathHelper.forPackage("com.valhallagame")).setScanners(new SubTypesScanner(false))
				.filterInputsBy(new FilterBuilder().includePackage("com.valhallagame.wardrobeserviceclient.message",
						"com.valhallagame.traitserviceclient.message",
						"com.valhallagame.statisticsserviceclient.message",
						"com.valhallagame.personserviceclient.message", "com.valhallagame.partyserviceclient.message",
						"com.valhallagame.notificationserviceclient.message",
						"com.valhallagame.instanceserviceclient.message",
						"com.valhallagame.friendserviceclient.message", "com.valhallagame.featserviceclient.message",
						"com.valhallagame.chatserviceclient.message",
						"com.valhallagame.characterserviceclient.message",
						"com.valhallagame.actionbarserviceclient.message")));

		Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);

		for (Class<? extends Object> c : allClasses) {
			logger.debug("Class Name: {}", c.getSimpleName());
			logger.debug("Packgage Name: {}", c.getPackage().getName());

			List<FieldSpec> fields = new ArrayList<>();
			for (Field f : c.getDeclaredFields()) {
				if (f.getName().equals("username") && f.getAnnotation(AllwaysExposedInYmer.class) == null) {
					continue;
				}

				String fieldName;
				ExposedNameInYmer exposed = f.getAnnotation(ExposedNameInYmer.class);
				if (exposed == null) {
					fieldName = f.getName();
				} else {
					fieldName = exposed.value();
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
						}).map(a -> AnnotationSpec.builder(a.annotationType()).build()).collect(Collectors.toList()))
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

			File javaFolder = new File("src/main/java");

			boolean differ = checkIfFilesDiffer(javaFolder, javaFile);
			if (differ) {
				logger.info("Files differ! Writing to {}", javaFile);
				javaFile.writeTo(javaFolder);
			}
		}
	}

	private static boolean checkIfFilesDiffer(File javaFolder, JavaFile javaFile) {
		Path directory = javaFolder.toPath();

		Path outputDirectory = directory;
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
