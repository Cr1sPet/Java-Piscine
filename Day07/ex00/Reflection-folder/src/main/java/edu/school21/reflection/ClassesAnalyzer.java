package edu.school21.reflection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassesAnalyzer {

    Set<Class> classes;
    Class aClass;
    Object obj;
    Field []fields;
    Method []methods;
    BufferedReader reader;
    public Set<Class> findAllClassesByPackage(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return reflections.getSubTypesOf(Object.class)
                .stream()
                .collect(Collectors.toSet());
    }

    public ClassesAnalyzer() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String read() throws IOException {
        String input;
        while ((input = reader.readLine()) == null) {

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

    public void printFields() {
        System.out.println("fields :");
        for (Field field : fields) {
            System.out.printf("\t%s %s\n", field.getType().getSimpleName(), field.getName());
        }
    }

    public void printFieldsAndMethodsByClassName(String name) {
        System.out.println("---------------------");
        try {
            aClass = Class.forName("edu.school21.reflection.classes." + name);

            fields = aClass.getDeclaredFields();
            printFields();
            methods = aClass.getDeclaredMethods();
            System.out.println("methods :");
            for (Method method : methods) {
                System.out.printf("\t%s %s(", method.getReturnType().getSimpleName(), method.getName() );
                Class[] parameters = method.getParameterTypes();
                if (parameters.length > 0) {
                    System.out.print(parameters[0].getSimpleName());
                    for (int i = 1; i < parameters.length; i++) {
                        System.out.print(", " + parameters[i].getSimpleName());
                    }
                }
                System.out.println(")");

            }
            System.out.println("---------------------");

        } catch (ClassNotFoundException e) {
            System.out.println("Incorrect class name !");
        }
    }


    public void parseDataType(Field field, String input) throws IllegalAccessException, IOException {
        switch (field.getType().getSimpleName()) {
            case "String" :
                field.set(obj, input);
                break;
            case "Integer" :
                field.set(obj, Integer.parseInt(input));
                break;
            case "Double" :
                field.set(obj, Double.parseDouble(input));
                break;
            case "Boolean" :
                field.set(obj, Boolean.parseBoolean(input));
                break;
            case "Long" :
                field.set(obj, Long.parseLong(input));
                break;
            default:
                reader.close();
                System.err.println("Incorrect data type");
                System.exit(-1);
        }
    }

    public Object getObject(String prototype, String input) throws IllegalAccessException, IOException {
        switch (prototype) {
            case "String" :
                return input;
            case "Integer" :
                return Integer.parseInt(input);
            case "Double" :
                return Double.parseDouble(input);
            case "Boolean" :
                return Boolean.parseBoolean(input);
            case "Long" :
                return Long.parseLong(input);
            default:
                reader.close();
                System.err.println("Incorrect data type");
                System.exit(-1);
        }
        return "";
    }


    public void createObject() throws IOException {
        String input;
        try {
            obj = aClass.getDeclaredConstructor().newInstance();
            for (Field field : fields) {
                System.out.println(field.getName());
                input = read();

                field.setAccessible(true);
                parseDataType(field, input);
            }
            System.out.println("Object created: " + obj);

        } catch (Exception e) {
            reader.close();
            System.err.println(e);
            System.exit(-1);
        }
    }

    public void updateObject() throws IOException, IllegalAccessException {
        System.out.println("Enter name of the field to changing:");
        String input;
        input = read();
        boolean ok = true;
        for (Field field : fields) {
            String temp = field.getName();
            System.out.println(temp);
            if (temp.equals(input)) {
                System.out.println("Enter " + field.getType().getSimpleName() + " value:");
                input = read();
                parseDataType(field, input);
                ok = false;
            }
        }
        if (ok == true) {
            reader.close();
            System.err.println("Incorrect input");
            System.exit(-1);
        }
        System.out.println("Updated created: " + obj);
    }

    public void callMethod() throws IOException, InvocationTargetException, IllegalAccessException {
        System.out.println("Enter name of the method to call:");
        String input = read();
        String []splittedInput = input.split("\\(");
        String funcName = splittedInput[0];
        String[]paramsArr = splittedInput[1].substring(splittedInput[1].indexOf("\\(") + 1, splittedInput[1].indexOf(")")).split(",");
        Class[] parameters;
        boolean ok = true;
        for (Method method : methods) {
            if (method.getName().equals(funcName)) {
                parameters = method.getParameterTypes();
                if (parameters.length == 0 && paramsArr.length == 1 && paramsArr[0].equals("")) {
                    System.out.println(method.invoke(obj));
                    ok = false;
                } else {
                    parameters = method.getParameterTypes();
                    if (parameters.length != paramsArr.length || paramsArr.length == 1 && paramsArr[0].equals("")) {
                        continue;
                    } else {
                        for (int i = 0; i < parameters.length; i++) {
                            if (!parameters[i].getSimpleName().equals(paramsArr[i])) {
                                continue;
                            }
                        }
                        Object [] pars = new Object[parameters.length];
                        for (int i = 0; i < parameters.length; i++) {
                            System.out.println("Enter " + parameters[i].getSimpleName() + " value");
                            input = read();
                            pars[i] = getObject(parameters[i].getSimpleName(), input);
                        }
                        if (method.getReturnType().getSimpleName().equals("void")) {
                            method.invoke(obj, pars);
                        } else {
                            System.out.println(method.invoke(obj, pars));
                        }
                        ok = false;
                        break;
                    }
                }
            }
        }
        if (ok == true) {
            reader.close();
            System.err.println("Incorrect input");
            System.exit(-1);
        }

    }


    public void start() throws Exception {
        printClasses();
        printFieldsAndMethodsByClassName(read());
        createObject();
        callMethod();
    }

}
