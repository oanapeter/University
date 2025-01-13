package Algorithms;

import Model.Polynomial;
import Utils.Task;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelClassic {
    private static final int NR_THREADS = 4; // Number of threads in the thread pool

    /**
     * Multiplies two polynomials using the classic method, executed in parallel.
     *
     * @param p1 the first polynomial
     * @param p2 the second polynomial
     * @return the product of p1 and p2 as a Polynomial object
     * @throws InterruptedException if the thread execution is interrupted
     */
    public static Polynomial multiply(Polynomial p1, Polynomial p2) throws InterruptedException {
        // Calculate the size of the resulting polynomial's coefficient list
        int sizeOfResultCoefficientList = p1.getDegree() + p2.getDegree() + 1;

        // Initialize the result's coefficient list with zeros
        List<Integer> coefficients = IntStream.range(0, sizeOfResultCoefficientList)
                .mapToObj(i -> 0)
                .collect(Collectors.toList());
        Polynomial result = new Polynomial(coefficients);

        // Create a thread pool with a fixed number of threads
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(NR_THREADS);

        // Determine the number of tasks based on the number of threads
        int nrOfTasks = sizeOfResultCoefficientList / NR_THREADS;
        if (nrOfTasks == 0) { // Ensure at least one task is created
            nrOfTasks = 1;
        }

        int end;
        // Split the work into tasks and assign each task to the executor
        for (int i = 0; i < sizeOfResultCoefficientList; i += nrOfTasks) {
            end = i + nrOfTasks; // Define the range of indices for this task
            Task task = new Task(i, end, p1, p2, result); // Create a task for the range
            executor.execute(task); // Submit the task to the executor
        }

        // Shutdown the executor to prevent additional task submissions
        executor.shutdown();
        // Wait for all tasks to complete execution
        executor.awaitTermination(50, TimeUnit.SECONDS);

        // Return the resulting polynomial after all tasks have updated it
        return result;
    }
}
