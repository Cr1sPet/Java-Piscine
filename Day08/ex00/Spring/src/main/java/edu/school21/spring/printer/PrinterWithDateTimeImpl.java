package edu.school21.spring.printer;

import edu.school21.spring.renderer.Renderer;
import edu.school21.spring.renderer.RendererErrImpl;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements   Printer {


    Renderer rendererErr;


    public PrinterWithDateTimeImpl(RendererErrImpl rendererErr) {
        this.rendererErr = rendererErr;
    }

    @Override
    public void print(String str) {
        rendererErr.doRender(LocalDateTime.now() + " " +  str);
    }
}
