import mpi.*;
import java.util.*;

public class Main {
    // Simulate a DSM with a shared variable (array of integers)
    private static final int NUM_VARS = 5;
    private static int[] sharedMemory = new int[NUM_VARS]; // Shared memory for variables

    // Map to track which processes are subscribed to each variable
    private static Map<Integer, List<Integer>> subscriptions = new HashMap<>();

    public static void main(String[] args) {
        try {
            // Initialize MPI
            MPI.Init(args);

            // Get the number of processes and the rank of the current process
            int rank = MPI.COMM_WORLD.Rank();
            int size = MPI.COMM_WORLD.Size();

            // Display information about each process
            System.out.println("Process " + rank + " of " + size);

            // Initialize the DSM: All processes subscribe to some variables
            for (int i = 0; i < NUM_VARS; i++) {
                subscribeToVariable(i, rank); // Each process subscribes to all variables
            }

            // Synchronize all processes after they finish subscribing
            MPI.COMM_WORLD.Barrier();

            // Example of writing to shared memory and notifying all subscribed processes
            if (rank == 0) {
                writeToVariable(0, 10); // Process 0 writes to variable 0
                writeToVariable(2, 20); // Process 0 writes to variable 2
            }

            // Wait for all processes to synchronize before continuing
            MPI.COMM_WORLD.Barrier();

            // Display shared memory after write operations
            if (rank == 0) {
                System.out.println("Shared memory after writes: " + Arrays.toString(sharedMemory));
            }

            // Simulate callback mechanism: Notify subscribed processes
            for (int i = 0; i < NUM_VARS; i++) {
                if (subscriptions.containsKey(i)) {
                    notifySubscribers(i);
                }
            }

            // Example of compare-and-exchange operation
            if (rank == 0) {
                System.out.println("Process 0 performs compare-and-exchange on variable 0");
                compareAndExchange(0, 10, 100); // If value is 10, set to 100
            }

            // Wait for all processes to synchronize again before finishing
            MPI.COMM_WORLD.Barrier();

            // Display final shared memory state
            if (rank == 0) {
                System.out.println("Shared memory after compare-and-exchange: " + Arrays.toString(sharedMemory));
            }

            // Clean up MPI
            MPI.Finalize();
        } catch (MPIException e) {
            e.printStackTrace();
        }
    }

    // Write a value to a variable (local or remote)
    private static void writeToVariable(int varIndex, int value) {
        // Write value to shared memory at the specified index
        sharedMemory[varIndex] = value;
        System.out.println("Process " + MPI.COMM_WORLD.Rank() + " wrote " + value + " to variable " + varIndex);
    }

    // Subscribe the given process to a variable
    private static void subscribeToVariable(int varIndex, int processRank) {
        subscriptions.computeIfAbsent(varIndex, k -> new ArrayList<>()).add(processRank);
        System.out.println("Process " + processRank + " subscribed to variable " + varIndex);
    }

    // Notify all subscribers when a variable changes
    private static void notifySubscribers(int varIndex) {
        System.out.println("Variable " + varIndex + " changed, notifying subscribers: " + subscriptions.get(varIndex));
        for (int subscriber : subscriptions.get(varIndex)) {
            System.out.println("Notifying process " + subscriber);
        }
    }

    // Compare and exchange operation: if current value equals oldValue, set it to newValue
    private static void compareAndExchange(int varIndex, int oldValue, int newValue) {
        if (sharedMemory[varIndex] == oldValue) {
            sharedMemory[varIndex] = newValue;
            System.out.println("Process " + MPI.COMM_WORLD.Rank() + " changed variable " + varIndex + " from " + oldValue + " to " + newValue);
        } else {
            System.out.println("Process " + MPI.COMM_WORLD.Rank() + " failed to change variable " + varIndex + " (current value: " + sharedMemory[varIndex] + ")");
        }
    }
}
