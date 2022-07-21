import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Menu {

    public Menu() {

    }

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        SignatureAnalyzer signatureAnalyzer = SignatureAnalyzer.getInstance();


        String input;
        while ((input = reader.readLine()) != null && !"42".equals(input)) {
            signatureAnalyzer.findFileType(input);
        }

    }

}
