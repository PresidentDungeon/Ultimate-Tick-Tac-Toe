/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package field;

import java.util.ArrayList;
import java.util.List;
import move.IMove;
import move.Move;

/**
 *
 * @author ander
 */
public class Field implements IField {

    int macroBoardSizeX = 3;
    int macroBoardSizeY = 3;

    int BoardSizeX = 9;
    int BoardSizeY = 9;

    String[][] macroBoard;
    String[][] Board;

    public Field() {

        macroBoard = new String[macroBoardSizeX][macroBoardSizeY];
        Board = new String[BoardSizeX][BoardSizeY];
        clearBoard();

    }

    @Override
    public void clearBoard() {

        for (int x = 0; x < macroBoardSizeX; x++) {

            for (int y = 0; y < macroBoardSizeY; y++) {
                macroBoard[x][y] = AVAILABLE_FIELD;
            }
        }

        for (int x = 0; x < BoardSizeX; x++) {

            for (int y = 0; y < BoardSizeY; y++) {
                Board[x][y] = EMPTY_FIELD;
            }
        }

    }

    @Override
    public List<IMove> getAvailableMoves() {

        List<IMove> allAvailableMoves = new ArrayList<>();

        for (int x = 0; x < macroBoardSizeX; x++) {

            for (int y = 0; y < macroBoardSizeY; y++) {
                if (macroBoard[x][y].equalsIgnoreCase(AVAILABLE_FIELD)) {
                    Move validPlacement = new Move(x, y);
                    allAvailableMoves.add(validPlacement);
                }
            }
        }
        return allAvailableMoves;
    }

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
    public Boolean isInActiveMicroboard(int x, int y) {
        
        int miniX = x/3;
        int miniY = y/3;
                
        return macroBoard[miniX][miniY].equalsIgnoreCase(AVAILABLE_FIELD);
    }

    @Override
    public String[][] getBoard() {
        return Board;
    }

    @Override
    public String[][] getMacroboard() {

        return macroBoard;
    }

    @Override
    public void setBoard(String[][] board) {
        this.Board = board;
    }

    @Override
    public void setMacroboard(String[][] macroboard) {

        this.macroBoard = macroboard;

    }

    public void setEmpty(String[][] board) {

    }

}
