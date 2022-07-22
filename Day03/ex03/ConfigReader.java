import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigReader {

    File configFile;


    public ConfigReader(String filePath) throws Exception {
        configFile = new File(filePath);
        if (!configFile.exists()) {
            throw new FileNotFoundException();
        }
        if (!configFile.isFile() || !configFile.canRead()) {
            throw new Exception("File dont readable");
        }
    }


    public List<String> read() throws IOException {

        List<String> list = new ArrayList<>();
        String inputLine;
        try (BufferedReader reader = new BufferedReader( new FileReader(configFile))) {
            while ( (inputLine = reader.readLine()) != null ) {
                list.add(inputLine);
            }
        }
        return list;
    }

}
