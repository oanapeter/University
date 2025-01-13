import mpi.*;
import java.util.*;
import java.util.concurrent.*;

public class DSM {
    // Map to store variable values
    private Map<String, Integer> variables;
    // Map to store subscribers for each variable
    private Map<String, List<Integer>> subscriptions;

    public DSM(List<String> variableNames) {
        variables = new HashMap<>();
        subscriptions = new HashMap<>();

        // Initialize variables and subscription lists
        for (String var : variableNames) {
            variables.put(var, 0);  // Default value for variables is 0
            subscriptions.put(var, new ArrayList<>());
        }
    }

    // Write a value to a variable
    public synchronized void write(String var, int value, int rank) {
        if (variables.containsKey(var)) {
            variables.put(var, value);
            // Notify all subscribers (using MPI to communicate)
            notifySubscribers(var, rank);
        }
    }

    // Subscribe a process to a variable (by rank)
    public synchronized void subscribe(String var, int rank) {
        if (subscriptions.containsKey(var)) {
            subscriptions.get(var).add(rank);
        }
    }

    // Callback to notify all processes about a variable change using MPI
    private synchronized void notifySubscribers(String var, int senderRank) {
        List<Integer> subs = subscriptions.get(var);
        for (int rank : subs) {
            if (rank != senderRank) {
                // Send the updated variable value to other processes
                try {
                    int value = variables.get(var);
                    MPI.COMM_WORLD.Send(new int[] {value}, 0, 1, MPI.INT, rank, 0);
                } catch (MPIException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Compare and exchange operation
    public synchronized boolean compareAndExchange(String var, int expected, int newValue, int rank) {
        if (variables.containsKey(var) && variables.get(var) == expected) {
            variables.put(var, newValue);
            notifySubscribers(var, rank);
            return true;
        }
        return false;
    }

    // Get the value of a variable
    public synchronized int get(String var) {
        return variables.getOrDefault(var, -1);
    }
}
