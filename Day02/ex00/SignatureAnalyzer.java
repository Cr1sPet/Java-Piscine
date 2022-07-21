import javax.sql.rowset.serial.SerialStruct;
import java.util.HashMap;
import java.util.Map;

public class SignatureAnalyzer {

    private Map<String, String> configInfo;

    public SignatureAnalyzer(Map<String, String> configInfo) {
        this.configInfo = configInfo;
    }

    public void printConfig() {
        for (Map.Entry entry : configInfo.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }


}
