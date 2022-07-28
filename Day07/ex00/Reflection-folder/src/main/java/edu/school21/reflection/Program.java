package edu.school21.reflection;

import org.reflections.Reflections;

import java.io.IOException;

public class Program
{
    public static void main( String[] args ) throws Exception {
        System.out.println("Classes:");

        ClassesAnalyzer classesAnalyzer = new ClassesAnalyzer();
        try {
            classesAnalyzer.start();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-1);
        }
    }
}
