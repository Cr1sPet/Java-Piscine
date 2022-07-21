
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Program {

    public static final String CONFIG_PATH = "signatures.txt";

    public static Map<String, String> readConfig() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        Map<String, String> retMap = new HashMap<>();

        FileInputStream fis = new FileInputStream(CONFIG_PATH);

        int i = 0;
        while (i != -1) {
            String key = null;
            String value = null;

            i = fis.read();
            while (i != -1) {
                stringBuilder.append((char)i);
                i = fis.read();
                if ((char)i == ',') {
                    key = stringBuilder.toString().trim();
                    stringBuilder.setLength(0);
                    break;
                }
            }
            if (key == null) {
                fis.close();
                System.out.println("Bad config");
                System.exit(-1);
            }
            i = fis.read();
            while (i != -1) {
                stringBuilder.append((char)i);
                i = fis.read();
                if ((char)i == '\n' || i == -1) {
                    value = stringBuilder.toString().trim();
                    stringBuilder.setLength(0);
                    break;
                }
            }
            if (value == null) {
                fis.close();
                System.out.println("Bad config");
                System.exit(-1);
            }
            retMap.put(key, value);
        }
        return retMap;
    }

    public static void main(String[] args) {
        System.out.println("hello");

        Map<String, String> map = new HashMap<>();

        try {
            map = readConfig();
            SignatureAnalyzer signatureAnalyzer = new SignatureAnalyzer(map);
            signatureAnalyzer.printConfig();
        } catch (FileNotFoundException e) {
            System.out.println("Error in open file :" + e);
        } catch (IOException e) {
            System.out.println("Error in input from file :" + e);
        }

    }
}
