package edu.school21.spring.preprocessor;

import java.util.Locale;

public class PreProcessorToUpperImpl implements  PreProcessor{

    public PreProcessorToUpperImpl() {

    }


    @Override
    public String process(String str) {
        return str.toUpperCase(Locale.ROOT);
    }

}
