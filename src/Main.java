import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {

    public static class Date {
        private byte year;
        private int yearAddition;
        private byte month;
        private byte day;

        // конструкторы
        public Date(int day, int month, int year) {
            this.yearAddition = year - (year % 100);
            if(year > 100) this.yearAddition = year - (year % 100);

            this.year = (byte)year;
            this.month = (byte)month;
            this.day = (byte)day;
        }

        public Date(String dateStr) {
            String[] date = dateStr.split("\\.");

            this.day = Byte.parseByte(date[0]);
            this.month = Byte.parseByte(date[1]);

            int yearInt = Integer.parseInt(date[2]);

            this.yearAddition = yearInt - (yearInt % 100);
            if(yearInt > 100) this.yearAddition = yearInt - (yearInt % 100);

            this.year = (byte)(yearInt % 100);
        }

        public Date(LocalDate date) {
            this.day = (byte) date.getDayOfMonth();
            this.month = (byte) date.getMonthValue();

            int yearInt = date.getYear();
            this.yearAddition = yearInt - (yearInt % 100);
            if(yearInt > 100) this.yearAddition = yearInt - (yearInt % 100);
            this.year = (byte)(yearInt % 100);

        }

        // методы
        public void setYear(int year) {
            this.yearAddition = year - (year % 100);
            if(year > 100) this.yearAddition = year - (year % 100);
            this.year = (byte)(year % 100);
        }

        public void setMonth(int month) {
            this.month = (byte)month;
        }

        public void setDay(int day) {
            this.day = (byte)day;
        }

        public int getYear() {
            return (int)year + yearAddition;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        public int getAdd() {
            return yearAddition;
        }

        public boolean isLeapYear() {
            int yearInt = year + yearAddition;
            return (yearInt % 4 == 0 && yearInt % 100 != 0) || (yearInt % 400 == 0);
        }

        public Date addDays(int days) {
            LocalDate date = LocalDate.of((int)year + yearAddition, month, day);

            date = date.plusDays(days);
            setDay(date.getDayOfMonth());
            setMonth(date.getMonthValue());
            setYear(date.getYear());
            return this;
        }

        public Date subtractDays(int days) {
            return addDays(-days);
        }

        public int compare(Date other) {
            LocalDate thisDate = LocalDate.of(year + yearAddition, month, day);
            LocalDate otherDate = LocalDate.of(other.year + other.yearAddition, other.month, other.day);
            return thisDate.compareTo(otherDate);
        }
        public int daysBetween(Date other) {
            LocalDate thisDate = LocalDate.of(year + yearAddition, month, day);
            LocalDate otherDate = LocalDate.of(other.year + other.yearAddition, other.month, other.day);
            return (int) ChronoUnit.DAYS.between(thisDate, otherDate);
        }


        public String toString() {
            return String.format("%02d.%02d.%d", day, month, (int)year + yearAddition);
        }

        public boolean equals(Date other) {
            if(day != other.day) return false;
            if(month != other.month) return false;
            if(year != other.year) return false;
            return yearAddition == other.yearAddition;
        }

    }



    public static class Matrix {
        private double[][] matrix = new double[2][2];

        public Matrix(double[][] matrix) {
            this.matrix = matrix;
        }

        public Matrix(double a, double b, double c, double d) {
            this.matrix[0][0] = a;
            this.matrix[0][1] = b;
            this.matrix[1][0] = c;
            this.matrix[1][1] = d;
        }

        public double getDeterminant() {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        public Matrix getInverse() throws Exception {
            double determinant = getDeterminant();
            if (determinant == 0) {
                throw new Exception("Детермінант = 0. Матриця вироджена!");
            }
            double[][] inverseMatrix = new double[2][2];
            inverseMatrix[0][0] = matrix[1][1] / determinant;
            inverseMatrix[0][1] = -matrix[0][1] / determinant;
            inverseMatrix[1][0] = -matrix[1][0] / determinant;
            inverseMatrix[1][1] = matrix[0][0] / determinant;
            return new Matrix(inverseMatrix);
        }

        public Matrix multiply(Matrix other) {
            double[][] resultMatrix = new double[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        resultMatrix[i][j] += matrix[i][k] * other.matrix[k][j];
                    }
                }
            }
            return new Matrix(resultMatrix);
        }

        public Matrix add(Matrix other) {
            double[][] resultMatrix = new double[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    resultMatrix[i][j] = matrix[i][j] + other.matrix[i][j];
                }
            }
            return new Matrix(resultMatrix);
        }

        public Matrix multiply(double scalar) {
            double[][] resultMatrix = new double[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    resultMatrix[i][j] = matrix[i][j] * scalar;
                }
            }
            return new Matrix(resultMatrix);
        }

        public String toString() {
            String out = "";
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    out += matrix[i][j] + " ";
                }
               out += "\n";
            }
            return out;
        }
    }


    public static void task1Test() {
        // Task 1 test
        Date date1 = new Date(21, 2, 2027);
        date1.setYear(2062);

        Date date11 = new Date(21, 2, 2027);
        date11.setYear(2062);

        Date date2 = new Date("21.05.2024");
        date2.setMonth(3);

        Date date3 = new Date(LocalDate.now());
        date3.setDay(1);

        System.out.println(date1.isLeapYear());
        System.out.println(date2.isLeapYear());
        System.out.println(date2.addDays(7));
        System.out.println(date3.subtractDays(14));
        System.out.println(date1.equals(date11));
        System.out.println(date1.equals(date2));
        System.out.println(date2.daysBetween(date1));
    }

    public static void task2Test() throws Exception {
        Matrix A = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix B = new Matrix(5, 6, 7, 8);

        System.out.println("Детермінант матриці A: " + A.getDeterminant());
        System.out.println("Детермінант матриці B: " + B.getDeterminant());
        System.out.println(B.getInverse());

        System.out.println(A.multiply(4));
        System.out.println(A.multiply(B));
        System.out.println(A.add(B));
    }

    public static void task3Test() {
        int[] arr = new int[25];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }


        System.out.println("Початковий массив: " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Відсортований массив: " + Arrays.toString(arr));

    }

    public static void quickSort(int[] arr, int left, int right) {
        if (arr == null || arr.length == 0) {
            return;
        }

        // Точка відліку
        int pivot = arr[left + (right - left) / 2];

        // Ділення масиву на 2 частини
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // Рекурсія лівої та правої частини
        if (left < j) {
            quickSort(arr, left, j);
        }
        if (right > i) {
            quickSort(arr, i, right);
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("Java Lab 2 ");
            Scanner cin = new Scanner(System.in);
        // write your code here
        int choice = 0;
        while (choice != 4) {
            System.out.println("Input task number (1-3). (4) to exit : ");
            choice = cin.nextInt();

            // Task 1 test
            if(choice == 1) {
                task1Test();
            }

            // Task 2 test
            if(choice == 2) {
                task2Test();
            }

            // Task 3 test
            if(choice == 3) {
                task3Test();
            }


            // Exit
            if(choice == 4) {
                break;
            }
        }
    }
}


