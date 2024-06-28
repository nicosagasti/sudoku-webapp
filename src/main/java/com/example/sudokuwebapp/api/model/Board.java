package com.example.sudokuwebapp.api.model;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board {
    /* Attributes */
    private final Number[][] board;
    private Number[][] solvedBoard;
    private final Number[][] spareBoard;
    private final int visibleDigits;

    /* Constructor */
    public Board(int visibleDigits) {
        this.visibleDigits = visibleDigits;

        board = new Number[9][9];
        spareBoard = new Number[9][9];
        solvedBoard = GenerateGrid.getSolvedGrid();

        initializeBoard();
    }

    /* Methods */
    public Number getNumber(int row, int col) {
        checkValidPosition(row, col);
        return board[row][col];
    }

    public boolean setNumber(int row, int col, int number) {
        boolean isWon = false;
        checkValidPosition(row, col);
        if (!board[row][col].isFixed()) {
            board[row][col].setValue(number);
            isWon = checkWon();
        }
        return isWon;
    }

    public Number[][] reloadBoard() {
        deepCopy();
        return board;
    }

    public Number[][] newBoard(){
        solvedBoard = GenerateGrid.getSolvedGrid();
        initializeBoard();
        return board;
    }

    /* Private Methods */
    private boolean checkWon() {
        boolean toReturn = true;
        for (int row = 0; row < 9 && toReturn; row++)
            for (int col = 0; col < 9 && toReturn; col++)
                toReturn = board[row][col].getValue() != solvedBoard[row][col].getValue();
        return toReturn;
    }

    private void checkValidPosition(int row, int col) throws IndexOutOfBoundsException {
        if (!(row >= 0 && row < board.length && col >= 0 && col < board[0].length))
            throw new IndexOutOfBoundsException("Invalid position on the board");
    }

    private void initializeBoard() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                board[i][j] = new Number(0, false);

        List<int[]> randomPositions = getInts();

        // Modify grid with solvedNumbers
        for (int[] pos : randomPositions) {
            int row = pos[0];
            int col = pos[1];
            int value = solvedBoard[row][col].getValue();
            board[row][col] = new Number(value, true);
            spareBoard[row][col] = new Number(value, true); // TODO ver si es eficiente
        }
    }

    private List<int[]> getInts() {
        List<int[]> randomPositions = new LinkedList<>();
        Random random = new Random();

        // Get random positions
        while (randomPositions.size() < visibleDigits) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            int[] position = new int[] { row, col };

            boolean alreadyExists = false;
            for (int[] pos : randomPositions)
                if (pos[0] == row && pos[1] == col) {
                    alreadyExists = true;
                    break;
                }

            if (!alreadyExists)
                randomPositions.add(position);
        }
        return randomPositions;
    }

    private void deepCopy() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                board[i][j] = new Number(spareBoard[i][j].getValue(), spareBoard[i][j].isFixed());
    }
}
