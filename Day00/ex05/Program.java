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

//    public static void fillMonthSchedule(String[][]classesList, Scanner in) {
//        String []monthDays
//    }


    public static void printHeader( String[][]classesList, String[][] attendanceList, int attListSize) {
        for (int i = 0; i < 30; i++) {

            for (int j = 0; classesList[i % 7][j] != null && j < MAX_WEEK_LESSON_NUMBER; j++) {
                System.out.printf("%4s:00 %s  %s|", classesList[i % 7][j],
                        WEEK_DAYS[i % 7], MONTH_DAYS[i]);
            }

        }
        for (int i = 0; attendanceList[i][0] != null && i < attListSize; i++) {
            System.out.println();
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

    public static boolean isPresent(String[] studentList, String student) {
        for (int i = 0; studentList[i] != null && i < MAX_STUDENTS_NUM; i++) {
            if (studentList[i].equals(student)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumber ( String input ) {

        char[] arr = input.toCharArray();

        if (arr[0] == '0') {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            if (!(arr[i] >= '0' && arr[i] <= '9'))
                return false;
        }

        return true;
    }

    public static String[][] readAttendance(String[] studentsList, int studentsSize, Scanner in) {
        String[][]ret = new String[MAX_STUDENTS_NUM * studentsSize][4];
        for (int i = 0; i < MAX_STUDENTS_NUM * studentsSize; i++) {
            checkNext(in);
            String name = in.next();
            if (name.equals(".")) {
                break;
            }
            if (!isPresent(studentsList, name)) {
                exitWithError("[ERROR] INCORRECT STUDENT NAME", in);
            }
            ret[i][0] = name;
            checkNext(in);
            String time = in.next();
            int timeInt =  time.toCharArray()[0] - '0';

            if (timeInt > TIME_END_LESSONS || timeInt < TIME_START_LESSONS) {
                exitWithError("[ERROR] INVALID TIME INPUT", in);
            }
            ret[i][1] = time;

            String date = in.next();

            if (!isNumber(date)) {
                exitWithError("[ERROR] INVALID DATE INPUT", in);
            }
            ret[i][2] = date;
            checkNext(in);
            String here;
            here = in.next();
            if (!("NOT_HERE".equals(here) || "HERE".equals(here))) {
                exitWithError("[ERROR] INVALID HERE INPUT", in);
            }
            ret[i][3] = here;
        }
        return  ret;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String []studentsList = new String[MAX_STUDENTS_NUM];
        int realStudentsListSize;
        int i;
        for (i = 0; studentsList[i] != null && i < MAX_STUDENTS_NUM; i++);
        realStudentsListSize = i;
        readNames(studentsList, in);


        String [][]classesList = new String[DAYS_IN_WEEK][MAX_WEEK_LESSON_NUMBER];

        readClasses(classesList, in);

        String[][] attendanceList = readAttendance(studentsList, realStudentsListSize, in);


        sortClassesByTime(classesList);

        printHeader(classesList, attendanceList, realStudentsListSize * MAX_STUDENTS_NUM);



//        System.out.println("###########################");
//        for (int i = 0; i < MAX_STUDENTS_NUM && studentsList[i] != null; i++) {
//            System.out.println(studentsList[i]);
//        }
//        System.out.println("###########################");
//        for (int i = 0; i < DAYS_IN_WEEK; i++) {
//            System.out.print(i + " = ");
//            for (int j  = 0; j < MAX_WEEK_LESSON_NUMBER && classesList[i][j] != null; j++) {
//                System.out.print(classesList[i][j] + " : ");
//            }
//            System.out.println();
//        }
//
    }

}
