import mpi.*;

public class Process implements Runnable {
    private int rank;
    private DSM dsm;

    public Process(int rank, DSM dsm) {
        this.rank = rank;
        this.dsm = dsm;
    }

    @Override
    public void run() {
        try {
            MPI.Init(new String[0]);
            rank = MPI.COMM_WORLD.Rank();

            // Perform some operations
            if (rank == 0) {
                dsm.write("var1", 10, rank);
            }
            if (rank == 1) {
                dsm.write("var2", 20, rank);
            }

            // Perform compare and exchange (CAS) operation
            if (rank == 0) {
                dsm.compareAndExchange("var1", 10, 30, rank);
            }

            // Wait and receive updates
            receiveUpdates();

            // Finalize MPI
            MPI.Finalize();
        } catch (MPIException e) {
            e.printStackTrace();
        }
    }

    private void receiveUpdates() {
        try {
            // Each process waits for messages from other processes
            int[] recvData = new int[1];
            MPI.COMM_WORLD.Recv(recvData, 0, 1, MPI.INT, MPI.ANY_SOURCE, 0);
            System.out.println("Process " + rank + " received updated value: " + recvData[0]);
        } catch (MPIException e) {
            e.printStackTrace();
        }
    }
}
