import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class HamiltonianCycleSearchTask implements Runnable {

    private final List<List<Integer>> graph;
    private final int startingNode;
    private final AtomicBoolean cycleFoundFlag;
    private final List<Integer> resultCyclePath;
    private final int[] currentPath;
    private final boolean[] visitedNodes;

    public HamiltonianCycleSearchTask(List<List<Integer>> graph, int startingNode, AtomicBoolean cycleFoundFlag, List<Integer> resultCyclePath) {
        this.graph = graph;
        this.startingNode = startingNode;
        this.cycleFoundFlag = cycleFoundFlag;
        this.resultCyclePath = resultCyclePath;
        this.currentPath = new int[graph.size()];
        this.visitedNodes = new boolean[graph.size()];
    }

    private void exploreNode(int currentNode, int pathIndex) {
        // Stop if a Hamiltonian cycle is already found
        if (cycleFoundFlag.get()) return;

        // Add current node to the path and mark it as visited
        currentPath[pathIndex] = currentNode;
        visitedNodes[currentNode] = true;

        // Check if we found a complete Hamiltonian cycle
        if (pathIndex == graph.size() - 1) {
            if (graph.get(currentNode).contains(startingNode)) {
                saveFoundCycle(pathIndex);
            }
        } else {
            // Explore each unvisited neighbor of the current node
            for (Integer neighbor : graph.get(currentNode)) {
                if (!visitedNodes[neighbor] && !cycleFoundFlag.get()) {
                    exploreNode(neighbor, pathIndex + 1);
                }
            }
        }

        // Backtrack if no cycle is found along this path
        visitedNodes[currentNode] = false;
    }

    private void saveFoundCycle(int pathIndex) {
        if (cycleFoundFlag.compareAndSet(false, true)) {
            synchronized (resultCyclePath) {
                resultCyclePath.clear();
                for (int i = 0; i <= pathIndex; i++) {
                    resultCyclePath.add(currentPath[i]);
                }
                resultCyclePath.add(startingNode);
            }
        }
    }

    @Override
    public void run() {
        exploreNode(startingNode, 0);
    }
}
