import java.util.Random;


class Matrix {
    int rows;
    int cols;
    Integer[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new Integer[rows][cols];
        generateRandomData();
    }

    private void generateRandomData() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = random.nextInt(10);
            }
        }
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    public int computeElement(Matrix b, int row, int col) {
        int cellValue = 0;
        for (int k = 0; k < this.cols; k++) {
            cellValue += this.data[row][k] * b.data[k][col];
        }
        return cellValue;
    }
}