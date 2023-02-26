
//Given 2D matrix of 1 and 0s. Using stack, find maximum area of square made by 0s.
//        INPUT: 1 0 1 0 0
//        0 1 1 1 1
//        0 0 0 0 1
//        0 0 0 1 1
//        0 1 0 1 1
//        OUTPUT: 4

public class Question8A {
    // Method to find the maximum area of a square made by 0s in the given matrix
    public static int getMaxSquareArea(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Create a dp matrix to store the maximum size of the square with bottom right corner at (i, j)
        int[][] dp = new int[rows][cols];
        int maxArea = 0;

        // Initialize the first row and first column of the dp matrix with the corresponding values from the input matrix
        for (int i = 0; i < rows; i++) {
            dp[i][0] = matrix[i][0];
            maxArea = Math.max(maxArea, dp[i][0]); // Update the maximum area if necessary
        }
        for (int j = 0; j < cols; j++) {
            dp[0][j] = matrix[0][j];
            maxArea = Math.max(maxArea, dp[0][j]); // Update the maximum area if necessary
        }

        // Fill the remaining cells of the dp matrix
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 0; // If the current cell in the input matrix is 1, then the maximum size of the square with bottom right corner at (i, j) is 0
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1; // Otherwise, calculate the maximum size of the square with bottom right corner at (i, j) using the values in the dp matrix
                    maxArea = Math.max(maxArea, dp[i][j]); // Update the maximum area if necessary
                }
            }
        }

        return maxArea * maxArea; // Return the maximum area of the square made by 0s
    }

    public static void main(String[] args) {
        // Example input matrix
        int[][] matrix = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};

        int maxSquareArea = getMaxSquareArea(matrix); // Calculate the maximum area of the square made by 0s in the input matrix
        System.out.println("Maximum area of square made by 0s: " + maxSquareArea); // Print the result
    }

}