import mpi.MPI;

public class Main {
    static final int POLYNOMIAL_SIZE = 10000;
    static final String IMPLEMENTATION = "karatsuba";

    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int numProcesses = MPI.COMM_WORLD.Size();

        if (rank == 0) {
            System.out.println("Master process: \n");
            Polynomial polynomial1 = new Polynomial(POLYNOMIAL_SIZE);
            polynomial1.generateRandomCoefficients();
            Polynomial polynomial2 = new Polynomial(POLYNOMIAL_SIZE);
            polynomial2.generateRandomCoefficients();

//            System.out.println("Polynomial 1: " + polynomial1);
//            System.out.println("---------------------------------------------------------------------------");
//            System.out.println();
//            System.out.println();
//            System.out.println("Polynomial 2: " + polynomial2);

            master(polynomial1, polynomial2, numProcesses);
        } else {
            if (IMPLEMENTATION.equalsIgnoreCase("regular")) {
                regularWorker(rank);
            } else if (IMPLEMENTATION.equalsIgnoreCase("karatsuba")) {
                karatsubaWorker(rank);
            }
        }
        MPI.Finalize();
    }

    public static void master(Polynomial polynomial1, Polynomial polynomial2, int totalProcesses) {
        long startTime = System.nanoTime();
        int chunkSize = polynomial1.getSize() / (totalProcesses - 1);
        int startIndex;
        int endIndex = 0;
        for (int i = 1; i < totalProcesses; i++) {
            startIndex = endIndex;
            endIndex = startIndex + chunkSize;
            if (i == totalProcesses - 1) {
                endIndex = polynomial1.getSize();
            }
            MPI.COMM_WORLD.Send(new Object[]{polynomial1}, 0, 1, MPI.OBJECT, i, 0);
            MPI.COMM_WORLD.Send(new Object[]{polynomial2}, 0, 1, MPI.OBJECT, i, 0);
            MPI.COMM_WORLD.Send(new int[]{startIndex}, 0, 1, MPI.INT, i, 0);
            MPI.COMM_WORLD.Send(new int[]{endIndex}, 0, 1, MPI.INT, i, 0);
        }
        Object[] results = new Object[totalProcesses - 1];
        for (int i = 1; i < totalProcesses; i++) {
            MPI.COMM_WORLD.Recv(results, i - 1, 1, MPI.OBJECT, i, 0);
        }
        Polynomial finalResult = PolynomialUtils.getResult(results);
        System.out.println("Final result: " + finalResult);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("Implementation: " + IMPLEMENTATION + "\n");
        System.out.println("Execution time: " + duration);
    }

    public static void regularWorker(int rank) {
        Object[] polynomial1 = new Object[2];
        Object[] polynomial2 = new Object[2];
        int[] startIndex = new int[1];
        int[] endIndex = new int[1];

        MPI.COMM_WORLD.Recv(polynomial1, 0, 1, MPI.OBJECT, 0, 0);
        MPI.COMM_WORLD.Recv(polynomial2, 0, 1, MPI.OBJECT, 0, 0);
        MPI.COMM_WORLD.Recv(startIndex, 0, 1, MPI.INT, 0, 0);
        MPI.COMM_WORLD.Recv(endIndex, 0, 1, MPI.INT, 0, 0);

        Polynomial poly1 = (Polynomial) polynomial1[0];
        Polynomial poly2 = (Polynomial) polynomial2[0];

        Polynomial result = PolynomialUtils.multiplySequence(poly1, poly2, startIndex[0], endIndex[0]);
        MPI.COMM_WORLD.Send(new Object[]{result}, 0, 1, MPI.OBJECT, 0, 0);
    }

    public static void karatsubaWorker(int rank) {
        Object[] polynomial1 = new Object[2];
        Object[] polynomial2 = new Object[2];
        int[] startIndex = new int[1];
        int[] endIndex = new int[1];

        MPI.COMM_WORLD.Recv(polynomial1, 0, 1, MPI.OBJECT, 0, 0);
        MPI.COMM_WORLD.Recv(polynomial2, 0, 1, MPI.OBJECT, 0, 0);
        MPI.COMM_WORLD.Recv(startIndex, 0, 1, MPI.INT, 0, 0);
        MPI.COMM_WORLD.Recv(endIndex, 0, 1, MPI.INT, 0, 0);

        Polynomial poly1 = (Polynomial) polynomial1[0];
        Polynomial poly2 = (Polynomial) polynomial2[0];

        for (int i = 0; i < startIndex[0]; i++) {
            poly1.getCoefficients().set(i, 0);
        }
        for (int j = endIndex[0]; j < poly1.getCoefficients().size(); j++) {
            poly1.getCoefficients().set(j, 0);
        }

        Polynomial result = PolynomialUtils.KaratsubaSequential(poly1, poly2);
        MPI.COMM_WORLD.Send(new Object[]{result}, 0, 1, MPI.OBJECT, 0, 0);
    }
}
