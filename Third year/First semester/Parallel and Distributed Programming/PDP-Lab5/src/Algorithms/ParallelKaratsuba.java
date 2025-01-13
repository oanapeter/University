package Algorithms;

import Model.Polynomial;
import Utils.PolynomialUtils;

import java.util.concurrent.*;

public class ParallelKaratsuba {
    private static final int NR_THREADS = 4; // Number of threads in the thread pool
    private static final int MAX_DEPTH = 4; // Maximum recursion depth for parallel execution

    /**
     * Multiplies two polynomials using the parallel Karatsuba algorithm.
     *
     * @param p1           the first polynomial
     * @param p2           the second polynomial
     * @param currentDepth the current depth of recursion
     * @return the product of p1 and p2
     * @throws InterruptedException if the thread execution is interrupted
     * @throws ExecutionException   if a task fails during execution
     */
    public static Polynomial multiply(Polynomial p1, Polynomial p2, int currentDepth)
            throws InterruptedException, ExecutionException {
        // If the maximum recursion depth is reached, switch to sequential Karatsuba
        if (currentDepth > MAX_DEPTH) {
            return SequentialKaratsuba.multiply(p1, p2);
        }

        // If either polynomial is too small, use sequential Karatsuba
        if (p1.getDegree() < 2 || p2.getDegree() < 2) {
            return SequentialKaratsuba.multiply(p1, p2);
        }

        // Divide the polynomials into lower and higher halves
        int len = Math.max(p1.getDegree(), p2.getDegree()) / 2;
        Polynomial lowP1 = new Polynomial(p1.getCoefficients().subList(0, len)); // Lower half of p1
        Polynomial highP1 = new Polynomial(p1.getCoefficients().subList(len, p1.getCoefficients().size())); // Upper half of p1
        Polynomial lowP2 = new Polynomial(p2.getCoefficients().subList(0, len)); // Lower half of p2
        Polynomial highP2 = new Polynomial(p2.getCoefficients().subList(len, p2.getCoefficients().size())); // Upper half of p2

        // Create a thread pool for parallel tasks
        ExecutorService executor = Executors.newFixedThreadPool(NR_THREADS);

        // Execute Karatsuba's three main recursive multiplications in parallel
        Future<Polynomial> f1 = executor.submit(() -> multiply(lowP1, lowP2, currentDepth + 1)); // z1 = lowP1 * lowP2
        Future<Polynomial> f2 = executor.submit(() ->
                multiply(PolynomialUtils.add(lowP1, highP1), PolynomialUtils.add(lowP2, highP2), currentDepth + 1)); // z2 = (lowP1 + highP1) * (lowP2 + highP2)
        Future<Polynomial> f3 = executor.submit(() -> multiply(highP1, highP2, currentDepth + 1)); // z3 = highP1 * highP2

        // Shut down the executor to avoid additional task submissions
        executor.shutdown();

        // Retrieve the results of the three recursive multiplications
        Polynomial z1 = f1.get();
        Polynomial z2 = f2.get();
        Polynomial z3 = f3.get();

        // Ensure all threads have completed within the specified time
        executor.awaitTermination(6, TimeUnit.SECONDS);

        // Compute the final result using Karatsuba's formula
        Polynomial r1 = PolynomialUtils.addWithZeros(z3, 2 * len); // z3 shifted left by 2 * len
        Polynomial r2 = PolynomialUtils.addWithZeros(
                PolynomialUtils.subtract(PolynomialUtils.subtract(z2, z3), z1), len); // (z2 - z3 - z1) shifted left by len
        return PolynomialUtils.add(PolynomialUtils.add(r1, r2), z1); // Final result: r1 + r2 + z1
    }
}
