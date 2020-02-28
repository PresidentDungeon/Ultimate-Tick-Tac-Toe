package game;

import bot.IBot;
import button.CustomButton;
import field.IField;
import java.util.List;
import javafx.scene.control.Alert;
//import javafx.scene.control.Alert;
import move.IMove;
import move.Move;

/**
 * This is a proposed GameManager for Ultimate Tic-Tac-Toe, the implementation of which is up to whoever uses this interface. Note that initializing a game through the constructors means that you have to create a new instance of the game manager for every new game of a different type (e.g. Human vs Human, Human vs Bot or Bot vs Bot), which may not be ideal for your solution, so you could consider refactoring that into an (re-)initialize method instead.
 *
 * @author mjl
 */
public class GameManager {

    /**
     * Three different game modes.
     */
    public enum GameMode {
        HumanVsHuman,
        HumanVsBot,
        BotVsBot
    }

    private final String FINISHED_FIELD = "-";
    private final String UNAVAILABLE_FIELD = "*";
    private final IGameState currentState;
    private int currentPlayer = 0; //player0 == 0 && player1 == 1
    private GameMode mode = GameMode.HumanVsHuman;
    private IBot bot = null;
    private IBot bot2 = null;

    /**
     * Set's the currentState so the game can begin. Game expected to be played Human vs Human
     *
     * @param currentState Current game state, usually an empty board, but could load a saved game.
     */
    public GameManager(IGameState currentState) {
        this.currentState = currentState;
        mode = GameMode.HumanVsHuman;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played Human vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could load a saved game.
     * @param bot The bot to play against in vsBot mode.
     */
    public GameManager(IGameState currentState, IBot bot) {
        this.currentState = currentState;
        mode = GameMode.HumanVsBot;
        this.bot = bot;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played Bot vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could load a saved game.
     * @param bot The first bot to play.
     * @param bot2 The second bot to play.
     */
    public GameManager(IGameState currentState, IBot bot, IBot bot2) {
        this.currentState = currentState;
        mode = GameMode.BotVsBot;
        this.bot = bot;
        this.bot2 = bot2;
    }

    /**
     * User input driven Update
     *
     * @param move The next user move
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame(IMove move) {
        //Verify the new move
        if (!verifyMoveLegality(move)) {
            return false;
        }

        //Update the currentState
        updateBoard(move);
        updateMicroboard(move);

        //Update currentPlayer
        currentPlayer = (currentPlayer + 1) % 2;

        return true;
    }

    public void play(CustomButton btn, List<CustomButton> buttonsInMicroBoard, List<CustomButton> allButtons) {
        IMove move = new Move(btn.getX(), btn.getY());
        if (verifyMoveLegality(move)) {
            btn.setText((currentPlayer == 0) ? "x" : "o");

            updateBoard(move);
            updateMicroboard(move);

            if (currentState.getField().checkMicroBoardFull(buttonsInMicroBoard)) {
                currentState.getField().getMicroboard()[btn.getX() / 3][btn.getY() / 3] = FINISHED_FIELD;
                setAllFieldsToAvailable();
                System.out.println("Is full");
            }
            if (currentState.getField().checkForWinnerInMicroBoard(buttonsInMicroBoard) != null) {
                currentState.getField().getMicroboard()[btn.getX() / 3][btn.getY() / 3] = currentState.getField().checkForWinnerInMicroBoard(buttonsInMicroBoard);

                String id = ((currentPlayer == 1) ? "blue" : "red");
                
                for (CustomButton b : buttonsInMicroBoard)
                {
                    b.setId(id);
                }
                
                
                if (currentState.getField().checkForWinnerBoard() != null) {
                    showWinnerAlert("Concratulations!", "The winner of the game is player: " + currentState.getField().checkForWinnerBoard());
                }

                setAllFieldsToAvailable();

            }

            if (currentState.getField().getAvailableMoves().size() == 0) {
                showWinnerAlert("Draw!", "The game ended in a draw");
            }

                        highlightPlayableArea(allButtons);
            
        }

    }

    //No available fields - set draw
    /**
     * Non-User driven input, e.g. an update for playing a bot move.
     *
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame() {
        //Check game mode is set to one of the bot modes.
        assert (mode != GameMode.HumanVsHuman);

        //Check if player is bot, if so, get bot input and update the state based on that.
        if (mode == GameMode.HumanVsBot && currentPlayer == 1) {
            //Check bot is not equal to null, and throw an exception if it is.
            assert (bot != null);

            IMove botMove = bot.doMove(currentState);

            //Be aware that your bots might perform illegal moves.
            return updateGame(botMove);
        }

        //Check bot is not equal to null, and throw an exception if it is.
        assert (bot != null);
        assert (bot2 != null);

        //TODO: Implement a bot vs bot Update.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Boolean verifyMoveLegality(IMove move) {
        //Test if the move is legal   
        //NOTE: should also check whether the move is placed on an occupied spot.
        if (currentState.getField().getBoard()[move.getX()][move.getY()].equalsIgnoreCase(IField.EMPTY_FIELD)) {
            return currentState.getField().isInActiveMicroboard(move.getX(), move.getY());
        }
        return false;
    }

    private void updateBoard(IMove move) {
        updateStatistics();
        currentState.getField().getBoard()[move.getX()][move.getY()]
                = (currentPlayer == 0) ? "x" : "o";
        currentPlayer = (currentPlayer + 1) % 2;
    }

    private void updateMicroboard(IMove move) {

        String[][] microBoard = currentState.getField().getMicroboard();

        int macroX = move.getX() % 3;
        int macroY = move.getY() % 3;

        //Set all unavailable fields that are not already full or won to available
        for (int x = 0; x < microBoard.length; x++) {
            for (int y = 0; y < microBoard.length; y++) {

                if (microBoard[x][y].equalsIgnoreCase(UNAVAILABLE_FIELD)) {
                    microBoard[x][y] = IField.AVAILABLE_FIELD;
                }
            }
        }
        //Sets all available (besides finished boards) to unavailable 
        //if the next move is in an available board
        if (microBoard[macroX][macroY].equalsIgnoreCase(IField.AVAILABLE_FIELD)) {

            for (int x = 0; x < microBoard.length; x++) {
                for (int y = 0; y < microBoard.length; y++) {

                    if (microBoard[x][y].equalsIgnoreCase(IField.AVAILABLE_FIELD)) {
                        microBoard[x][y] = UNAVAILABLE_FIELD;
                    }
                }
            }
            microBoard[macroX][macroY] = IField.AVAILABLE_FIELD;
        }
        currentState.getField().setMicroboard(microBoard); //Måske?
    }

    public void setAllFieldsToAvailable() {
        //Set all unavailable fields that are not already full or won to available
        for (int x = 0; x < IField.microBoardSizeX; x++) {
            for (int y = 0; y < IField.microBoardSizeY; y++) {

                if (currentState.getField().getMicroboard()[x][y].equalsIgnoreCase(UNAVAILABLE_FIELD)) {
                    currentState.getField().getMicroboard()[x][y] = IField.AVAILABLE_FIELD;
                }
            }
        }
    }

    private void updateStatistics() {
        currentState.setMoveNumber(currentState.getMoveNumber() + 1);
    }

    public void highlightPlayableArea(List<CustomButton> allButtons)
    {
        for (CustomButton button : allButtons)
        {
            button.getStylesheets().clear();
            button.getStylesheets().add("css/MainTheme.css");
        }

        List<IMove> availableMoves = currentState.getField().getAvailableMoves();
        
        for (CustomButton b : allButtons)
        {
            for(IMove move : availableMoves)
            {
                if (b.getX() == move.getX() && b.getY() == move.getY())
                {
                    b.getStylesheets().add("css/ClickableButton.css");
                    break;
                }
            }
        }
    }
    
    /**
     * Opens a error box to be displayed.
     *
     * @param contentText the message that the error box should display.
     */
    public void showWinnerAlert(String header, String Winner) {
        Alert errAlert = new Alert(Alert.AlertType.INFORMATION);
        errAlert.setTitle(header);
        errAlert.setHeaderText(null);
        errAlert.setContentText(Winner);
        errAlert.showAndWait();
    }
}
