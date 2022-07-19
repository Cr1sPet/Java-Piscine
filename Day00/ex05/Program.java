package ex05;

import com.sun.prism.impl.paint.PaintUtil;

import java.util.Scanner;

public class Program {

    public static final int MAX_STUDENTS_NUM = 10;
    public static final int MAX_STUDENT_NAME_LENGTH = 10;
    public static final int DAYS_IN_WEEK = 7;
    public static final int MAX_WEEK_LESSON_NUMBER = 10;

    public static final int TIME_START_LESSONS = 1;
    public static final int TIME_END_LESSONS = 6;

    public static final int MONTH_LENGTH = 30;

    public static final String []WEEK_DAYS = {"TU", "WE", "TH", "FR", "SA", "SU", "MO"};

    public static final String[]MONTH_DAYS = {"1", "2", "3", "4", "5",
    "6", "7", "8", "9", "10", "11", "12",
    "13", "14", "15", "16", "17", "18", "19",
    "20", "21", "22", "23", "24", "25", "26",
    "27", "28", "29", "30"};

    public static void exitWithError(String message, Scanner in) {
        System.err.println(message);
        in.close();
        System.exit(-1);
    }

    public static void readNames(String[] studentList, Scanner in) {
        int i;
        for(i = 0; i < MAX_STUDENTS_NUM; i++) {
            if(!in.hasNext()) {
                in.close();
                System.exit(-1);
            }
            String tmp = in.next();
            studentList[i] = tmp;
            if (studentList[i].length() > MAX_STUDENT_NAME_LENGTH) {
                exitWithError("[Error] MAX LENGTH OF STUDENT NAME", in);
            }
            if (studentList[i].equals(".")) {
                studentList[i] = null;
                break;
            }
        }
        if (i == MAX_STUDENTS_NUM) {
            in.close();
            System.err.println("[Error] MAX STUDENTS");
        }
    }


    public static int calculateDayOfWeekIndex(String weekDay, Scanner in) {
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            if (WEEK_DAYS[i].equals(weekDay)) {
                return i;
            }
        }
        exitWithError("[Error] INCORRECT WEEKDAY", in);
        return 0;
    }

    public static void setClassTime(String [][]classesList, String time, int weekDayIndex, Scanner in) {
        if (time.length() != 1) {
            exitWithError("[ERROR] INVALID TIME INPUT", in);
        }

        int timeInt =  time.toCharArray()[0] - '0';

        if (timeInt > TIME_END_LESSONS || timeInt < TIME_START_LESSONS) {
            exitWithError("[ERROR] INVALID TIME INPUT", in);
        }

        int i;
        for (i = 0; i < MAX_WEEK_LESSON_NUMBER && classesList[weekDayIndex][i] != null; i++);

        classesList[weekDayIndex][i] = time;

    }

    public static void readClasses(String[][] classesList, Scanner scanner) {
        int i;
        String time;
        int weekDayIndex;
        String weekDay;
        for(i = 0; i < MAX_WEEK_LESSON_NUMBER; i++) {
            checkNext(scanner);
            time = scanner.next();
            if (time.equals(".")) {
                break;
            }
            checkNext(scanner);
            weekDay = scanner.next();
            weekDayIndex = calculateDayOfWeekIndex(weekDay, scanner);
            setClassTime(classesList, time, weekDayIndex, scanner);

        }
        if (i == MAX_WEEK_LESSON_NUMBER) {
            scanner.close();
            System.err.println("[Error] MAX CLASSES PER WEEK");
        }
    }

    public static void checkNext(Scanner scanner) {
        if(!scanner.hasNext())
        {
            scanner.close();
            System.exit(-1);
        }
    }

    public static void checkNextInt(Scanner scanner) {
        if(!scanner.hasNextInt())
        {
            scanner.close();
            System.exit(-1);
        }
    }



    public static void sortStringArray(String []arr) {
        int k;
        for (k = 0; arr[k] != null && k < arr.length; k++);

        int arrSize = k;

        for (int i = 0; i < arrSize - 1; ++i) {
            boolean swapped = false;
            for (int j = 0; j < (arrSize - i - 1); ++j) {
                char [] charArr1 = arr[j].toCharArray();
                char [] charArr2 = arr[j + 1].toCharArray();
                if (charArr1[0] > charArr2[0]) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void sortClassesByTime(String[][] classesList) {
        for (int j = 0; j < DAYS_IN_WEEK; j++) {
            sortStringArray(classesList[j]);
        }

    }

    public static int getIndexName(String[] studentList, String student) {
        for (int i = 0; studentList[i] != null && i < MAX_STUDENTS_NUM; i++) {
            if (studentList[i].equals(student)) {
                return i;
            }
        }
        return -1;
    }


    public static String[][][] readAttendance(String[] studentsList, int studentsSize, Scanner in) {
        String[][][]ret = new String[studentsSize][MONTH_LENGTH][TIME_END_LESSONS - TIME_START_LESSONS];
        for (int i = 0; i < MAX_STUDENTS_NUM * studentsSize; i++) {
            checkNext(in);
            String name = in.next();
            if (name.equals(".")) {
                break;
            }
            int ind = getIndexName(studentsList, name);
            if (ind == -1) {
                exitWithError("[ERROR] INCORRECT STUDENT NAME", in);
            }
            checkNextInt(in);
            int time = in.nextInt();
            checkNextInt(in);
            int date = in.nextInt();
            checkNext(in);
            String here;
            here = in.next();
            if (!("NOT_HERE".equals(here) || "HERE".equals(here))) {
                exitWithError("[ERROR] INVALID HERE INPUT", in);
            }
            ret[ind][date - 1][time - 1] = here.equals("HERE") ? "1" : "-1";
//            System.out.printf("IND :%d TIME - 1 : %d, DATE - 1 : %d : VAL :%d", ind, (time - 1), (date - 1), ret[ind][date  - 1][time -1] );
        }
        return  ret;
    }

    public static int countAttendance(String[] att) {
        int ret = 0;
        for (int i = 0; i < TIME_END_LESSONS - TIME_START_LESSONS; i++) {
            if (att[i] != null) {
                ret++;
            }
        }
        return ret;
    }

    public static void printHeader( String[][]classesList, String[][][] attendanceList, String []names,  int studentsLength) {
        System.out.printf("%10s", "");
        for (int i = 0; i < MONTH_LENGTH; i++) {
            for (int j = 0; classesList[i % 7][j] != null && j < MAX_WEEK_LESSON_NUMBER; j++) {
                System.out.printf("%s:00 %s %2s|", classesList[i % 7][j],
                        WEEK_DAYS[i % 7], MONTH_DAYS[i]);
            }
        }
        System.out.println();
        for (int i = 0; i < studentsLength; i++) {
            System.out.printf("%9s|", names[i]);
            for (int j = 0; j < MONTH_LENGTH; j++) {
                if (classesList[j % 7][0] != null ) {
                    if (countAttendance(attendanceList[i][j]) != 0) {
                        for (int k = 0; k < TIME_END_LESSONS - TIME_START_LESSONS; k++) {
                            if (attendanceList[i][j][k] != null) {
                                System.out.printf("%10s|", attendanceList[i][j][k]);
                            }
                        }
                    } else {
                            System.out.printf("%10s|", "");
                        }
                }
            }
            System.out.println();
        }

    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String []studentsList = new String[MAX_STUDENTS_NUM];
        readNames(studentsList, in);
        String [][]classesList = new String[DAYS_IN_WEEK][MAX_WEEK_LESSON_NUMBER];
        readClasses(classesList, in);
        int i;
        for (i = 0; studentsList[i] != null && i < MAX_STUDENTS_NUM; i++);
        int realStudentsListSize = i;
//        readNames(studentsList, in);
        String[][][] attendanceList = readAttendance(studentsList, realStudentsListSize, in);
        sortClassesByTime(classesList);
        printHeader(classesList, attendanceList, studentsList, realStudentsListSize);

    }

}
