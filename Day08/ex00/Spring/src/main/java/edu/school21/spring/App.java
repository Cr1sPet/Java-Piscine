package edu.school21.spring;

import edu.school21.spring.preprocessor.PreProcessor;
import edu.school21.spring.preprocessor.PreProcessorToUpperImpl;
import edu.school21.spring.printer.Printer;
import edu.school21.spring.printer.PrinterWithPrefixImpl;
import edu.school21.spring.renderer.Renderer;
import edu.school21.spring.renderer.RendererErrImpl;
import edu.school21.spring.renderer.RendererStandardImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void standardWork() {
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererStandardImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix");
        printer.print("Hello");
    }

    public static void main(String[] args )
    {
        standardWork();

        System.out.println("#######################################");
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printer", Printer.class);
        printer.print("Hello");
        System.out.println("#######################################");
        ApplicationContext context1 = new ClassPathXmlApplicationContext("context.xml");
        Printer printer1 = context1.getBean("printer1", Printer.class);
        printer1.print("World");

    }
}
