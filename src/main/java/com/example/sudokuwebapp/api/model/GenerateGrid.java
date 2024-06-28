package com.example.sudokuwebapp.api.model;
public class GenerateGrid {
    //https://www.geeksforgeeks.org/program-sudoku-generator/ (modified)

    static Number[][] mat;
    static int N; // number of columns/rows.
    static int SRN; // square root of N

    public static Number[][] getSolvedGrid() {
        N = 9;
        Double SRNd = Math.sqrt(N);
        SRN = SRNd.intValue();

        mat = new Number[N][N];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mat[i][j] = new Number(0, false);
            }
        }

        fillValues();
        return mat;
    }

    // Sudoku Generator
    public static void fillValues() {
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, SRN);
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    private static void fillDiagonal() {
        for (int i = 0; i < N; i = i + SRN)
            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    private static boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < SRN; i++)
            for (int j = 0; j < SRN; j++)
                if (mat[rowStart + i][colStart + j].getValue() == num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    private static void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < SRN; i++) {
            for (int j = 0; j < SRN; j++) {
                do {
                    num = randomGenerator(N);
                } while (!unUsedInBox(row, col, num));

                mat[row + i][col + j].setValue(num);
            }
        }
    }

    // Random generator
    private static int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }

    // Check if safe to put in cell
    private static boolean CheckIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - i % SRN, j - j % SRN, num));
    }

    // check in the row for existence
    private static boolean unUsedInRow(int i, int num) {
        for (int j = 0; j < N; j++)
            if (mat[i][j].getValue() == num)
                return false;
        return true;
    }

    // check in the row for existence
    private static boolean unUsedInCol(int j, int num) {
        for (int i = 0; i < N; i++)
            if (mat[i][j].getValue() == num)
                return false;
        return true;
    }

    private static boolean fillRemaining(int i, int j) {
        if (j >= N && i < N - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= N && j >= N)
            return true;

        if (i < SRN) {
            if (j < SRN)
                j = SRN;
        } else if (i < N - SRN) {
            if (j == (int) (i / SRN) * SRN)
                j = j + SRN;
        } else {
            if (j == N - SRN) {
                i = i + 1;
                j = 0;
                if (i >= N)
                    return true;
            }
        }

        for (int num = 1; num <= N; num++) {
            if (CheckIfSafe(i, j, num)) {
                mat[i][j].setValue(num);
                if (fillRemaining(i, j + 1))
                    return true;
                mat[i][j].setValue(0);
            }
        }
        return false;
    }
}

