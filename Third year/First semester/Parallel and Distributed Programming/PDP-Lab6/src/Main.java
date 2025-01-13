import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    private static final int THREAD_COUNT = 5;

    private static List<List<Integer>> loadGraphFromFile(String filePath) throws FileNotFoundException {
        List<List<Integer>> graph = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            int numVertices = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < numVertices; i++) {
                graph.add(new ArrayList<>());
            }
            while (scanner.hasNextLine()) {
                String[] edge = scanner.nextLine().split(" ");
                int fromNode = Integer.parseInt(edge[0]);
                int toNode = Integer.parseInt(edge[1]);
                graph.get(fromNode).add(toNode);
            }
        }
        return graph;
    }

    public static void main(String[] args) throws Exception {
        List<List<Integer>> graph = loadGraphFromFile("src/graphs/g2.txt");
        AtomicBoolean cycleFound = new AtomicBoolean(false);
        List<Integer> cyclePath = new ArrayList<>();
        BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(graph.size());
        for (int i = 0; i < graph.size(); i++) {
            taskQueue.add(i);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(() -> {
                while (!cycleFound.get() && !taskQueue.isEmpty()) {
                    Integer node = taskQueue.poll();
                    if (node != null) {
                        new HamiltonianCycleSearchTask(graph, node, cycleFound, cyclePath).run();
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);
        long endTime = System.currentTimeMillis();
        if (cycleFound.get()) {
            System.out.println("Hamiltonian Cycle Found: " + cyclePath);
        } else {
            System.out.println("No Hamiltonian Cycle found");
        }

        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }
}
