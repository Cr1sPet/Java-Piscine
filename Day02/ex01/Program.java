import java.io.*;
import java.util.*;

enum OpenMode {
    READ,
    WRITE
}

public class Program {

    public static Double calculateSimilarity(Vector<Integer> vector1, Vector<Integer> vector2) {
        Double squareSumA;
        Double squareSumB;
        Double numerator;
        Double sum;

        numerator = 0.0;
        for (int i = 0; i < vector1.size(); i++) {
            numerator += vector1.get(i) * vector2.get(i);
        }

        sum = 0.0;
        for (Integer val : vector1) {
            sum += val * val;
        }
        squareSumA = Math.sqrt(sum);
        sum = 0.0;
        for (Integer val : vector2) {
            sum += val * val;
        }
        squareSumB = Math.sqrt(sum);
        if (squareSumA == 0.0 || squareSumB == 0.0) {
            return 0.0;
        }
        Double ret = numerator / (squareSumA * squareSumB);
        return ret;
    }

    public static List<String> readFileToDictionary(Set<String> dictionary, String filePath) throws IOException {
        List<String> retList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String input;
            while ((input = reader.readLine()) != null ) {
                String inputs[] = input.split("\\s+");
                for(int i = 0; i < inputs.length; i++) {
                    dictionary.add(inputs[i]);
                    retList.add(inputs[i]);
                }
            }
            return retList;
        }

    }


    public static void checkPath(String path, OpenMode mode) throws IOException {
        File file = new File(path);
        switch (mode) {
            case READ:
                if (!file.canRead()) {
                    System.err.println("Invalid file : " + path);
                    System.exit(-1);
                }
                break;
            case WRITE:
                if (!file.exists()) {
                    file.createNewFile();
                }
                if (!file.canWrite()) {
                    System.err.println("Invalid file : " + path);
                    System.exit(-1);
                }

        }

    }

    public static Vector<Integer> fillVector(List<String> textList, Set<String> dictionary) {
        Vector<Integer> vector = new Vector<>(dictionary.size());
        Integer counter;
        for (String ds : dictionary) {
            counter = 0;
            for (String ls : textList) {
                if (ds.equals(ls)) {
                    counter++;
                }
            }
            vector.add(counter);
        }
        return vector;
    }

    public static void saveDictionary(Set<String> dictionary) throws IOException {

        try (BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter("dictionary.txt"))
        ) {
            int i = 0;
            for (String a : dictionary) {
                bufferedWriter.write(a);
                if (i != dictionary.size() - 1) {
                    bufferedWriter.write(", ");
                }
                i++;
            }
        }

    }


    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Invalid arguments number");
            System.exit(-1);
        }

        try {
            checkPath(args[0], OpenMode.READ);
            checkPath(args[1], OpenMode.READ);
            checkPath("dictionary.txt", OpenMode.WRITE);
        } catch (IOException e) {
            System.out.println(e);
        }



        Set<String> dictionary = new TreeSet<>();
        List<String> text1List;
        List<String> text2List;
        try {
            text1List = readFileToDictionary(dictionary, args[0]);
            text2List = readFileToDictionary(dictionary, args[1]);
            Vector<Integer> vector1 = fillVector(text1List, dictionary);
            Vector<Integer> vector2 = fillVector(text2List, dictionary);
            System.out.printf("Similarity : %.2f ", calculateSimilarity(vector1, vector2));
            saveDictionary(dictionary);
        } catch (IOException e) {
            System.out.println("Error in file handling : " + e);
            System.exit(-1);
        }

    }
}
