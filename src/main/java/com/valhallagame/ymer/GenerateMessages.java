package com.valhallagame.ymer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
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

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GenerateMessages {
	public static void generateSeviceMessages() throws IOException {
		Reflections reflections = new Reflections(new ConfigurationBuilder()
				.setUrls(ClasspathHelper.forPackage("com.valhallagame"))
				.setScanners(new SubTypesScanner(false))
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
						"com.valhallagame.characterserviceclient.message")));

		Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);

		for (Class<? extends Object> c : allClasses) {
			System.out.println("Class Name: " + c.getSimpleName());
			System.out.println("Packgage Name: " + c.getPackage().getName());

			List<FieldSpec> fields = new ArrayList<>();
			for (Field f : c.getDeclaredFields()) {
				System.out.println("Field Name: " + f.getName());
				if (f.getName().equals("username")) {
					continue;
				}

				TypeName typeName;

				if (f.getGenericType() instanceof ParameterizedType) {
					ParameterizedType parameterizedType = (ParameterizedType) f.getGenericType();
					typeName = ParameterizedTypeName.get(f.getType(), parameterizedType.getActualTypeArguments());
				} else {
					typeName = TypeName.get(f.getType());
				}

				fields.add(FieldSpec.builder(typeName, f.getName())
						.addAnnotations(Arrays.stream(f.getDeclaredAnnotations())
								.map(a -> AnnotationSpec.builder(a.annotationType()).build())
								.collect(Collectors.toList()))
						.build());

			}

			if (fields.isEmpty()) {
				continue;
			}

			TypeSpec serviceMessage = TypeSpec.classBuilder(c.getSimpleName())
					.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
					.addFields(fields)
					.addAnnotation(Data.class)
					.addAnnotation(NoArgsConstructor.class)
					.addAnnotation(AllArgsConstructor.class)
					.build();

			String serviceName = c.getPackage().getName().split("\\.")[2].replace("serviceclient", "");

			JavaFile javaFile = JavaFile.builder("com.valhallagame.ymer.message." + serviceName, serviceMessage).build();

			javaFile.writeTo(new File("src/main/java"));
		}
	}
}
