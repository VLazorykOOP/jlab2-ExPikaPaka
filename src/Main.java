import java.time.LocalDate;
import java.util.*;
import MyDate.Date;
import MyMatrix.Matrix;
public class Main {

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



        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date1.before(date2));
        System.out.println(date1.after(date2));

    }

    public static void task2Test() {
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


    public static void main(String[] args) {
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


