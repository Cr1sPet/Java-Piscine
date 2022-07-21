import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignatureAnalyzer {

    public static final String CONFIG_PATH = "signatures.txt";

    private Map<String, String> configSignaturesMap;
    private List<String> signaturesList = new ArrayList<>();
    private int maxSignatureLength = 0;


    private SignatureAnalyzer() throws IOException {
        this.configSignaturesMap =  readConfig();
    }

    public Map<String, String> readConfig() throws IOException {
        Map<String, String> retMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH));
        String strCurrentLine;
        while ((strCurrentLine = reader.readLine()) != null) {
            String[] input = strCurrentLine.split(",");
            if (input.length != 2) {
                reader.close();
                System.out.println("Bad config");
                System.exit(-1);
            }
            String key = input[1].replaceAll("\\s+", "");
            String value = input[0].trim();
            if (value.length() == 0 || key.length() == 0) {
                reader.close();
                System.out.println("Bad config");
                System.exit(-1);
            }
            maxSignatureLength = maxSignatureLength < key.length() / 2 ? key.length() / 2 : maxSignatureLength;
            retMap.put(key, value);
        }
        return retMap;
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public String findTypeBySignature(String signature) {

        for(Map.Entry entry : configSignaturesMap.entrySet()) {
            String configSignature = (String) entry.getKey();
            if (signature.length() <  configSignature.length()) {
                return "UNDEFINED";
            }
            if (configSignature.equals(signature.substring(0, configSignature.length()))) {
                return (String) entry.getValue();
            }
        }

        return "UNDEFINED";
    }

    public void findFileType(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] signatures = new byte [maxSignatureLength];
        fis.read(signatures);
        String stringSignature = bytesToHex(signatures);
        System.out.println("SIGNATURE :" + stringSignature);
        signaturesList.add(findTypeBySignature(stringSignature));
        fis.close();
    }

    public void printConfig() {

//        System.out.println("MAX LENGTH" + maxSignatureLength);
//
//        for (Map.Entry entry : configSignaturesMap.entrySet()) {
//            System.out.print(entry.getKey() + ":");
//            String value = (String) entry.getValue();
//            System.out.printf("{%s}", value);
//            System.out.println();
//        }
//
//        System.out.println("TYPES");

        for (String signature : signaturesList) {
            System.out.println(signature);
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
