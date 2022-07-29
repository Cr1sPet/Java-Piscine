package edu.school21.spring.printer;

import edu.school21.spring.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer{
    String prefix;

    Renderer renderer;
    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public PrinterWithPrefixImpl() {
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String str) {
        renderer.doRender(prefix + " " + str);
    }
}
