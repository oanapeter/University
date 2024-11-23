import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows for Matrix A: ");
        int rowsA = scanner.nextInt();
        System.out.print("Enter the number of columns for Matrix A (and rows for Matrix B): ");
        int colsA = scanner.nextInt();
        System.out.print("Enter the number of columns for Matrix B: ");
        int colsB = scanner.nextInt();

        Matrix a = new Matrix(rowsA, colsA);
        Matrix b = new Matrix(colsA, colsB);
        Matrix result = new Matrix(rowsA, colsB);

        System.out.println("\nMatrix A:");
        a.print();
        System.out.println("\nMatrix B:");
        b.print();

        System.out.print("\nEnter the number of tasks (threads): ");
        int taskNumber = scanner.nextInt();

        while (true) {
            System.out.println("\nChoose the method of computation:");
            System.out.println("1. Row-by-row");
            System.out.println("2. Column-by-column");
            System.out.println("3. Every k-th element");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 4) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

            System.out.println("Choose execution type:");
            System.out.println("1. Threads");
            System.out.println("2. Thread Pool");
            System.out.print("Enter your choice: ");
            int executionType = scanner.nextInt();

            long startTime = System.nanoTime();
            switch (choice) {
                case 1:
                    System.out.println("\nRow-by-Row:");
                    if (executionType == 1) {
                        runRowByRowWithThreads(a, b, result, taskNumber);
                    } else {
                        runRowByRowWithThreadPool(a, b, result, taskNumber);
                    }
                    break;
                case 2:
                    System.out.println("\nColumn-by-Column:");
                    if (executionType == 1) {
                        runColumnByColumnWithThreads(a, b, result, taskNumber);
                    } else {
                        runColumnByColumnWithThreadPool(a, b, result, taskNumber);
                    }
                    break;
                case 3:
                    System.out.println("\nEvery k-th Element:");
                    if (executionType == 1) {
                        runKthElementWithThreads(a, b, result, taskNumber);
                    } else {
                        runKthElementWithThreadPool(a, b, result, taskNumber);
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            long endTime = System.nanoTime();
            double elapsedTime = (endTime - startTime) / 1_000_000.0;
            System.out.printf("Elapsed time: %.2f ms\n", elapsedTime);
        }

        scanner.close();
    }

    public static void runRowByRowWithThreads(Matrix a, Matrix b, Matrix result, int taskNumber) throws InterruptedException {
        int totalElements = result.getRows() * result.getCols();
        List<Thread> threads = new ArrayList<>();

        int elementsPerTask = totalElements / taskNumber;
        int start = 0;

        for (int i = 0; i < taskNumber; i++) {
            int end = (i == taskNumber - 1) ? totalElements : start + elementsPerTask;
            threads.add(new Thread(new RowTask(result, a, b, start, end)));
            start = end;
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\nResultant Matrix:");
        result.print();
    }

    public static void runRowByRowWithThreadPool(Matrix a, Matrix b, Matrix result, int taskNumber) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(taskNumber);
        int totalElements = result.getRows() * result.getCols();
        int elementsPerTask = totalElements / taskNumber;
        int start = 0;

        for (int i = 0; i < taskNumber; i++) {
            int end = (i == taskNumber - 1) ? totalElements : start + elementsPerTask;
            executor.execute(new RowTask(result, a, b, start, end));
            start = end;
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("\nResultant Matrix:");
        result.print();
    }

    public static void runColumnByColumnWithThreads(Matrix a, Matrix b, Matrix result, int taskNumber) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        int totalElements = result.getRows() * result.getCols();
        int elementsPerTask = totalElements / taskNumber;
        int start = 0;

        for (int i = 0; i < taskNumber; i++) {
            int end = (i == taskNumber - 1) ? totalElements : start + elementsPerTask;
            threads.add(new Thread(new ColumnTask(result, a, b, start, end)));
            start = end;
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\nResultant Matrix:");
        result.print();
    }

    public static void runColumnByColumnWithThreadPool(Matrix a, Matrix b, Matrix result, int taskNumber) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(taskNumber);
        int totalElements = result.getRows() * result.getCols();
        int elementsPerTask = totalElements / taskNumber;
        int start = 0;

        for (int i = 0; i < taskNumber; i++) {
            int end = (i == taskNumber - 1) ? totalElements : start + elementsPerTask;
            executor.execute(new ColumnTask(result, a, b, start, end));
            start = end;
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("\nResultant Matrix:");
        result.print();
    }

    public static void runKthElementWithThreads(Matrix a, Matrix b, Matrix result, int taskNumber) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < taskNumber; i++) {
            threads.add(new Thread(new KthTask(result, a, b, i, taskNumber)));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\nResultant Matrix:");
        result.print();
    }

    public static void runKthElementWithThreadPool(Matrix a, Matrix b, Matrix result, int taskNumber) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(taskNumber);
        for (int i = 0; i < taskNumber; i++) {
            executor.execute(new KthTask(result, a, b, i, taskNumber));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("\nResultant Matrix:");
        result.print();
    }
}
