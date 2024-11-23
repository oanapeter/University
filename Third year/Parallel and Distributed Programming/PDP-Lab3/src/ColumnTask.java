class ColumnTask implements Runnable {
    private final Matrix result;
    private final Matrix a;
    private final Matrix b;
    private final int start;
    private final int end;

    public ColumnTask(Matrix result, Matrix a, Matrix b, int start, int end) {
        this.result = result;
        this.a = a;
        this.b = b;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int index = start; index < end; index++) {
            int col = index / result.rows;
            int row = index % result.rows;
            result.data[row][col] = a.computeElement(b, row, col);
        }
    }
}
