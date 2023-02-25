import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MatrixMultiplication {
    private final int[][] matrix1;
    private final int[][] matrix2;
    private final int[][] result;

    public MatrixMultiplication(int[][] matrix1, int[][] matrix2) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = new int[matrix1.length][matrix2[0].length];
    }

    public void multiply() {
        int numThreads = matrix1.length * matrix2[0].length;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                MatrixMultiplicationTask task = new MatrixMultiplicationTask(i, j);
                executor.execute(task);
            }
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // Thread interrupted, stop multiplication
        }
    }

      class MatrixMultiplicationTask implements Runnable {
        private final int row;
        private final int col;

        public  MatrixMultiplicationTask(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void run() {
            for (int k = 0; k < matrix2.length; k++) {
                result[row][col] += matrix1[row][k] * matrix2[k][col];
            }
        }
    }

}

