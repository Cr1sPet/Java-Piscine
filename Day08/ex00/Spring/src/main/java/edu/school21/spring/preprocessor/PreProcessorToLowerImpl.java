package edu.school21.spring.preprocessor;

import java.util.Locale;

public class PreProcessorToLowerImpl implements  PreProcessor{

    public PreProcessorToLowerImpl() {

    }


    @Override
    public String process(String str) {
        return str.toLowerCase(Locale.ROOT);
    }
}
