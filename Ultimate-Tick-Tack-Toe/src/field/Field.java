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
            System.out.println(Board[b.getX()][b.getY()].equalsIgnoreCase(EMPTY_FIELD));
            if (Board[b.getX()][b.getY()].equalsIgnoreCase(EMPTY_FIELD)) {
                isFull = false;
                break;
            }
        }

        return isFull;
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
