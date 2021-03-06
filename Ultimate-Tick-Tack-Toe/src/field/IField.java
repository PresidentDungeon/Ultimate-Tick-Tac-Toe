package field;

import button.CustomButton;
import java.util.List;
import move.IMove;

/**
 *
 * @author mjl
 */
public interface IField {

    String AVAILABLE_FIELD = "-1";
    String EMPTY_FIELD = ".";
    String UNAVAILABLY_FIELD = "*";
    
    int microBoardSizeX = 3;
    int microBoardSizeY = 3;

    int BoardSizeX = 9;
    int BoardSizeY = 9;
    
    

    /**
     * Clears the board
     */
    void clearBoard();

    /**
     * Generates a list of available moves, moves are limited to the 3x3 area 
     * indicated by the opponents last move, and limited by occupied spaces.
     * @return List of currently available moves
     */
    List<IMove> getAvailableMoves();

    /**
     * Returns the player id on given column and row
     * @param column Column
     * @param row Row
     * @return String
     */
    String getPlayerId(int column, int row);

    boolean isEmpty();

    /**
     * Checks whether the field is full
     * @return Returns true when field is full, otherwise returns false.
     */
    boolean isFull();

    boolean checkMicroBoardFull(List<CustomButton> microBoardButtons);
    
    String checkForWinnerInMicroBoard(List<CustomButton> microBoardButtons);
    
    String checkForWinnerBoard();
    
    /**
     * Checks whether a specific board position is available for input.
     * It checks whether the board position is available for play in the 
     * microboard (3x3), where it is marked using the AVAILABLE_FIELD character.
     * @param x
     * @param y
     * @return Returns true if the board position at (x,y) is available for input, false otherwise.
     */
    Boolean isInActiveMicroboard(int x, int y);

    /**
     * @return the Board (the entire 9x9 board)
     */
    String[][] getBoard();

    /**
     * @return the Microboard (the overarching 3x3 board)
     */
    String[][] getMicroboard();

    /**
     * @param board the Board to set (the entire 9x9 board)
     */
    void setBoard(String[][] board);

    /**
     * @param microboard the Microboard to set (the overarching 3x3 board)
     */
    void setMicroboard(String[][] microboard);
    
}
