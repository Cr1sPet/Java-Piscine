import javax.sql.rowset.serial.SerialStruct;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SignatureAnalyzer {

    public static final String CONFIG_PATH = "signatures.txt";

    private Map<String, String[]> configSignaturesMap;
    private int maxSignatureLength = 0;


    private SignatureAnalyzer() throws IOException {
        this.configSignaturesMap =  readConfig();
    }

    public Map<String, String[]> readConfig() throws IOException {
        Map<String, String[]> retMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH));
        String strCurrentLine;
        while ((strCurrentLine = reader.readLine()) != null) {
            String[] input = strCurrentLine.split(",");
            if (input.length != 2) {
                reader.close();
                System.out.println("Bad config");
                System.exit(-1);
            }
            String []value = input[1].trim().split("\\s+");
            maxSignatureLength = maxSignatureLength < value.length ? value.length : maxSignatureLength;
            retMap.put(input[0].trim(), value);
        }
        return retMap;
    }

    public void findFileType(String filePath) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        fis.re
    }

    public void printConfig() {

        System.out.println("MAX LENGTH" + maxSignatureLength);

        for (Map.Entry entry : configSignaturesMap.entrySet()) {
            System.out.print(entry.getKey() + ":");
            String[]value = (String[]) entry.getValue();
            for(int i = 0; i < value.length; i++) {
                System.out.printf("{%s}", value[i]);
            }
            System.out.println();
        }
    }

    private static SignatureAnalyzer signatureAnalyzer = null;

    public static SignatureAnalyzer getInstance() throws IOException{
        if (signatureAnalyzer == null) {
            signatureAnalyzer = new SignatureAnalyzer();
        }
        return signatureAnalyzer;
    }


}
