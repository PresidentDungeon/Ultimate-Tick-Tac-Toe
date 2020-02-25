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

    String AVAILABLE_FIELD = "-1";
    String EMPTY_FIELD = ".";

    int macroBoardSizeX = 9;
    int macroBoardSizeY = 9;

    String[][] macroBoard = new String[macroBoardSizeX][macroBoardSizeY];

    @Override
    public void clearBoard() {

        macroBoard = new String[macroBoardSizeX][macroBoardSizeY];

        for (int x = 0; x < macroBoardSizeX; x++) {

            for (int y = 0; y < macroBoardSizeY; y++) {
                macroBoard[x][y] = ".";
            }
        }

    }

    @Override
    public List<IMove> getAvailableMoves() {

        List<IMove> allAvailableMoves = new ArrayList<>();

        for (int x = 0; x < macroBoardSizeX; x++) {

            for (int y = 0; y < macroBoardSizeY; y++) {
                if (macroBoard[x][y].equalsIgnoreCase(EMPTY_FIELD)) {
                    Move validPlacement = new Move(x, y);
                    allAvailableMoves.add(validPlacement);
                }
            }
        }
        return allAvailableMoves;
    }

    @Override
    public String getPlayerId(int column, int row) {
        
        return macroBoard[column][row];
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] getBoard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] getMacroboard() {

        return macroBoard;
    }

    @Override
    public void setBoard(String[][] board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMacroboard(String[][] macroboard) {

        this.macroBoard = macroboard;

    }

    public void setEmpty(String[][] board) {

    }

}
