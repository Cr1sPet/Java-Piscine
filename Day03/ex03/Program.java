import java.util.List;

public class Program {

    public static final String CONFIG_PATH = "files_urls.txt";
    public static final String PARAMETER = "--threadsCount";

    public static int parseArgs(String []args) {

        if (args.length != 1) {
            System.err.println("Invalid args count");
            System.exit(-1);
        }

        String[] inputArray = args[0].split("=");
        if (inputArray.length != 2 || !inputArray[0].equals(PARAMETER)) {
            System.err.println("Error : bad arguments");
            System.exit(-1);
        }
        try {
            return Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            System.err.println("Error : bad arguments");
            System.exit(-1);
        }
        return -1;
    }

    public static void main(String[] args) {

        List<String> list;

        int threadCount = parseArgs(args);

        try {
            ConfigReader configReader = new ConfigReader(CONFIG_PATH);
            list = configReader.read();
            Downloader.parallelFilesDownload(list, threadCount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
