package edu.school21.reflection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassesAnalyzer {

    Set<Class> classes;
    Class aClass;
    Object object;
    Field []fields;
    public Set<Class> findAllClassesByPackage(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return reflections.getSubTypesOf(Object.class)
                .stream()
                .collect(Collectors.toSet());
    }

    public ClassesAnalyzer() {}

    public String read() throws IOException {
        String input;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while ((input = reader.readLine()) == null) {

            }
        }
        return input;
    }

    public void printClasses() {
        classes = findAllClassesByPackage("edu.school21.reflection.classes");
        for (Class cl : classes) {
            System.out.println(cl.getSimpleName());
        }
        System.out.println("---------------------");
        System.out.println("Enter class name:");
    }

    public void printFieldsAndMethodsByClassName(String name) {
        System.out.println("---------------------");
        try {
            aClass = Class.forName("edu.school21.reflection.classes." + name);

            fields = aClass.getDeclaredFields();
            System.out.println("fields :");
            for (Field field : fields) {
                System.out.printf("\t%s %s\n", field.getType().getSimpleName(), field.getName());
            }

            Method[]methods = aClass.getDeclaredMethods();
            System.out.println("methods :");
            for (Method method : methods) {
                System.out.printf("\t%s %s(", method.getReturnType(), method.getName() );
                Class[] parameters = method.getParameterTypes();
                if (parameters.length > 0) {
                    System.out.print(parameters[0].getName());
                    for (int i = 1; i < parameters.length; i++) {
                        System.out.print(", " + parameters[i].getName());
                    }
                }
                System.out.println(")");

            }
            System.out.println("---------------------");

        } catch (ClassNotFoundException e) {
            System.out.println("Incorrect class name !");
        }
    }

    public void createObject() {
        try {
            Object obj = aClass.newInstance();


        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println(e);
            System.exit(-1);
        }
    }

    public void start() throws IOException {
        printClasses();
        printFieldsAndMethodsByClassName(read());
        createObject();
    }
}
