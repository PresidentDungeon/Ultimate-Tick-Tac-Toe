/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package field;

import button.CustomButton;
import java.util.ArrayList;
import java.util.List;
import move.IMove;
import move.Move;

/**
 *
 * @author ander
 */
public class Field implements IField {

    int microBoardSizeX = 3;
    int microBoardSizeY = 3;

    int BoardSizeX = 9;
    int BoardSizeY = 9;

    private String[][] microBoard;
    private String[][] Board;

    public Field() {

        microBoard = new String[microBoardSizeX][microBoardSizeY];
        Board = new String[BoardSizeX][BoardSizeY];
        clearBoard();

    }

    /**
     * Resets the board for a new game.
     */
    @Override
    public void clearBoard() {

        for (int x = 0; x < microBoardSizeX; x++) {

            for (int y = 0; y < microBoardSizeY; y++) {
                microBoard[x][y] = AVAILABLE_FIELD;
            }
        }

        for (int x = 0; x < BoardSizeX; x++) {

            for (int y = 0; y < BoardSizeY; y++) {
                Board[x][y] = EMPTY_FIELD;
            }
        }
        
    }

    /**
     * When a player clicks a field, this method will check if it is legal.
     *
     * @return
     */
    @Override
    public List<IMove> getAvailableMoves() {

        List<IMove> allAvailableMoves = new ArrayList<>();

        for (int x = 0; x < BoardSizeX; x++) {
            for (int y = 0; y < BoardSizeY; y++) {
                if (Board[x][y].equalsIgnoreCase(EMPTY_FIELD) && isInActiveMicroboard(x, y)) {
                    Move validPlacement = new Move(x, y);
                    allAvailableMoves.add(validPlacement);
                }
            }
        }
        return allAvailableMoves;
    }

    /**
     * Who's turn is it?
     *
     * @param column
     * @param row
     * @return
     */
    @Override
    public String getPlayerId(int column, int row) {
        return Board[column][row];
    }

    @Override
    public boolean isEmpty() {
        for (int x = 0; x < BoardSizeX; x++) {
            for (int y = 0; y < BoardSizeY; y++) {
                if (!Board[x][y].equalsIgnoreCase(EMPTY_FIELD)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isFull() {

        for (int x = 0; x < BoardSizeX; x++) {
            for (int y = 0; y < BoardSizeY; y++) {
                if (Board[x][y].equalsIgnoreCase(EMPTY_FIELD)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkMicroBoardFull(List<CustomButton> microBoardButtons) {
        boolean isFull = true;

        for (CustomButton b : microBoardButtons) {
            if (Board[b.getX()][b.getY()].equalsIgnoreCase(EMPTY_FIELD)) {
                isFull = false;
                break;
            }
        }

        return isFull;
    }

    @Override
    public String checkForWinnerInMicroBoard(List<CustomButton> microBoardButtons) {
        int arrayLocation = 0;

        String[][] clickedMicroBoard = new String[IField.microBoardSizeX][IField.microBoardSizeY];

        for (int y = 0; y < IField.microBoardSizeY; y++) {
            for (int x = 0; x < IField.microBoardSizeX; x++) {

                CustomButton currentCustomButton = microBoardButtons.get(arrayLocation);
                arrayLocation++;
                clickedMicroBoard[x][y] = Board[currentCustomButton.getX()][currentCustomButton.getY()];
            }
        }

        //Check for columns
        for (int y = 0; y < microBoardSizeY; y++) {
            if (!clickedMicroBoard[y][0].equalsIgnoreCase(EMPTY_FIELD) && !clickedMicroBoard[y][1].equalsIgnoreCase(EMPTY_FIELD) && !clickedMicroBoard[y][2].equalsIgnoreCase(EMPTY_FIELD)) {
                if (clickedMicroBoard[y][0].equalsIgnoreCase(clickedMicroBoard[y][1])
                        && clickedMicroBoard[y][0].equalsIgnoreCase(clickedMicroBoard[y][2])) {

                    return clickedMicroBoard[y][0];
                }
            }
        }

        //Check for rows
        for (int x = 0; x < microBoardSizeX; x++) {
            if (!clickedMicroBoard[0][x].equalsIgnoreCase(EMPTY_FIELD) && !clickedMicroBoard[1][x].equalsIgnoreCase(EMPTY_FIELD) && !clickedMicroBoard[2][x].equalsIgnoreCase(EMPTY_FIELD)) {
                if (clickedMicroBoard[0][x].equalsIgnoreCase(clickedMicroBoard[1][x])
                        && clickedMicroBoard[0][x].equalsIgnoreCase(clickedMicroBoard[2][x])) {

                    return clickedMicroBoard[0][x];
                }
            }
        }

        //Check for diag
        if (!clickedMicroBoard[0][0].equalsIgnoreCase(EMPTY_FIELD) && !clickedMicroBoard[1][1].equalsIgnoreCase(EMPTY_FIELD) && !clickedMicroBoard[2][2].equalsIgnoreCase(EMPTY_FIELD)) {
            if (clickedMicroBoard[0][0].equalsIgnoreCase(clickedMicroBoard[1][1])
                    && clickedMicroBoard[0][0].equalsIgnoreCase(clickedMicroBoard[2][2])) {
                return clickedMicroBoard[0][0];
            }
        }

        //check for antiDiag
        if (!clickedMicroBoard[0][2].equalsIgnoreCase(EMPTY_FIELD) && !clickedMicroBoard[1][1].equalsIgnoreCase(EMPTY_FIELD) && !clickedMicroBoard[2][0].equalsIgnoreCase(EMPTY_FIELD)) {
            if (clickedMicroBoard[0][2].equalsIgnoreCase(clickedMicroBoard[1][1])
                    && clickedMicroBoard[0][2].equalsIgnoreCase(clickedMicroBoard[2][0])) {

                return clickedMicroBoard[0][2];
            }
        }

        return null;
    }

    @Override
    public String checkForWinnerBoard() {

        //Check for columns
        for (int y = 0; y < microBoardSizeY; y++) {
            if (microBoard[y][0].equalsIgnoreCase("x") && microBoard[y][1].equalsIgnoreCase("x") && microBoard[y][2].equalsIgnoreCase("x")
                    || microBoard[y][0].equalsIgnoreCase("o") && microBoard[y][1].equalsIgnoreCase("o") && microBoard[y][2].equalsIgnoreCase("o")) {

                if (microBoard[y][0].equalsIgnoreCase(microBoard[y][1])
                        && microBoard[y][0].equalsIgnoreCase(microBoard[y][2])) {

                    return microBoard[y][0];
                }
            }
        }

        //Check for rows
        for (int x = 0; x < microBoardSizeX; x++) {
            if (microBoard[0][x].equalsIgnoreCase("x") && microBoard[1][x].equalsIgnoreCase("x") && microBoard[2][x].equalsIgnoreCase("x")
                    || microBoard[0][x].equalsIgnoreCase("o") && microBoard[1][x].equalsIgnoreCase("o") && microBoard[2][x].equalsIgnoreCase("o")) {
                if (microBoard[0][x].equalsIgnoreCase(microBoard[1][x])
                        && microBoard[0][x].equalsIgnoreCase(microBoard[2][x])) {

                    return microBoard[0][x];
                }
            }
        }

        //Check for diag
        if (microBoard[0][0].equalsIgnoreCase("x") && microBoard[1][1].equalsIgnoreCase("x") && microBoard[2][2].equalsIgnoreCase("x")
                || microBoard[0][0].equalsIgnoreCase("o") && microBoard[1][1].equalsIgnoreCase("o") && microBoard[2][2].equalsIgnoreCase("o")) {
            if (microBoard[0][0].equalsIgnoreCase(microBoard[1][1])
                    && microBoard[0][0].equalsIgnoreCase(microBoard[2][2])) {
                return microBoard[0][0];
            }
        }

        //check for antiDiag
        if (microBoard[0][2].equalsIgnoreCase("x") && microBoard[1][1].equalsIgnoreCase("x") && microBoard[2][0].equalsIgnoreCase("x")
                || microBoard[0][2].equalsIgnoreCase("o") && microBoard[1][1].equalsIgnoreCase("o") && microBoard[2][0].equalsIgnoreCase("o")) {
            if (microBoard[0][2].equalsIgnoreCase(microBoard[1][1])
                    && microBoard[0][2].equalsIgnoreCase(microBoard[2][0])) {

                return microBoard[0][2];
            }
        }

        return null;
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y) {

        int miniX = x / 3;
        int miniY = y / 3;

        return microBoard[miniX][miniY].equalsIgnoreCase(AVAILABLE_FIELD);
    }

    @Override
    public String[][] getBoard() {
        return Board;
    }

    @Override
    public String[][] getMicroboard() {

        return microBoard;
    }

    @Override
    public void setBoard(String[][] board) {
        this.Board = board;
    }

    @Override
    public void setMicroboard(String[][] microboard) {

        this.microBoard = microboard;
    }
}
