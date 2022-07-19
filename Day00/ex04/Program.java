
import java.util.Scanner;

public class Program {

    public static final int UNICODE_LENGTH = 65536;

    public static int findUsefulSize(int[] unicodeMapping) {
        int counter = 0;
        for (int i = 0; i < UNICODE_LENGTH; i++) {
            if (unicodeMapping[i] != 0) {
                counter++;
            }
        }
        return counter;
    }

    public static int[][] getUsefulMap(int []unicodeMapping, int size) {
        int [][] usefulMap = new int[size][2];
        int j = 0;

        for (int i = 0; i < UNICODE_LENGTH; i++) {
            if (unicodeMapping[i] > 0) {
                usefulMap[j][0] = i;
                usefulMap[j][1] = unicodeMapping[i];
                j++;
            }
        }
        return usefulMap;
    }

    public static int findMin(int [][]map, int mapSize) {
        int max = map[0][1];
        int ret = 0;

        for (int i = 1; i < mapSize; i++) {
            int temp = map[i][1];
            if (temp > max) {
                max = map[i][1];
                ret = i;
            }
        }
        return  ret;
    }


    public static int[][] filterMap(int [][]usefulMap, int usefulMapSize) {
        int [][]map = new int[10][2];
        int tempMin;

        for (int i = 0; i < 10; i++) {
            tempMin = findMin(usefulMap, usefulMapSize);
            map[i][0] = usefulMap[tempMin][0];
            map[i][1] = usefulMap[tempMin][1];
            usefulMap[tempMin][1] = 0;
        }
        return map;
    }

    public static void getMap(String inputString) {
        char []input = inputString.toCharArray();
        int []unicodeMapping = new int[UNICODE_LENGTH];

        for (int i = 0; i < inputString.length(); i++) {
            unicodeMapping[(int) input[i]]++;
        }

        int usefulMapSize = findUsefulSize(unicodeMapping);
        int usefulMap[][] = getUsefulMap(unicodeMapping, usefulMapSize);

        int size = usefulMapSize > 10 ? 10 : usefulMapSize;

        printResult(filterMap(usefulMap, usefulMapSize), size);
    }


    public static void printResult(int[][]map, int size) {
        int [][]result = new int[12][size];
        int sizes[] = new int[size];
        int max = map[0][1];

        sizes[0] = max;
        map[0][1] = 10;
        for (int i = 1; i < size; i++) {
            sizes[i] = map[i][1];
            map[i][1] = (int) ((map[i][1] / (double)max) * 10);
        }

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < 11; i++) {
                if (i ==  10 - map[j][1]) {
                    result[i][j] = sizes[j];
                }
                else if (i > 10 - map[j][1]) {
                    result[i][j] = '#';
                }
            }
            result[11][j] = map[j][0];
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < size; j++) {
                if (i ==  10 - map[j][1]) {
                    System.out.printf("%2d", result[i][j]);
                } else {
                    System.out.printf("%2c", (char) result[i][j]);
                }
                System.out.print( " " );

            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputString;
        if (in.hasNextLine()) {
            inputString = in.nextLine();
            if (inputString.length() == 0) {
                return;
            }
            getMap(inputString);
        }
    }
}
