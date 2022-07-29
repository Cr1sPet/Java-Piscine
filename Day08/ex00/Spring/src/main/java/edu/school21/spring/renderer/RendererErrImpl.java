package edu.school21.spring.renderer;

import edu.school21.spring.preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer{

    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }



    @Override
    public void doRender(String str) {
        System.err.println(preProcessor.process(str));
    }


}
