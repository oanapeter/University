class KthTask implements Runnable {
    private final Matrix result;
    private final Matrix a;
    private final Matrix b;
    private final int taskIndex;
    private final int step;

    public KthTask(Matrix result, Matrix a, Matrix b, int taskIndex, int step) {
        this.result = result;
        this.a = a;
        this.b = b;
        this.taskIndex = taskIndex;
        this.step = step;
    }

    @Override
    public void run() {
        int totalElements = result.rows * result.cols;
        for (int index = taskIndex; index < totalElements; index += step) {
            int row = index / result.cols;
            int col = index % result.cols;
            result.data[row][col] = a.computeElement(b, row, col);
        }
    }
}