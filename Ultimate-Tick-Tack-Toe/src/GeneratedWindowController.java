/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import button.CustomButton;
import field.IField;
import game.GameManager;
import game.GameState;
import game.IGameState;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class GeneratedWindowController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    IGameState gameState = new GameState();
    GameManager gm = new GameManager(gameState);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateButtons();
    }

    /**
     * Will generate game buttons at startup.
     */
    public void generateButtons() {
        int initialXPosition = 90;
        int initialYPosition = 139;
        int buttonWidth = 30;
        int buttonHeight = 30;

        int spacingX = 0;
        int spacingY = 0;

        for (int y = 0; y < IField.BoardSizeY; y++) {

            spacingX = 0;
            if (y == 3 || y == 6) {
                spacingY += 1;
            }

            for (int x = 0; x < IField.BoardSizeX; x++) {

                if (x == 3 || x == 6) {
                    spacingX += 1;
                }

                CustomButton btn = new CustomButton(x, y);

                btn.setOnMouseClicked((mouseEvent)
                        -> {
                    getPosition(btn);
                    playGame(btn);

                });

                btn.setPrefSize(buttonWidth, buttonHeight);

                btn.setLayoutX(initialXPosition + (buttonWidth * x) + spacingX * 20);
                btn.setLayoutY(initialYPosition + (buttonHeight * y) + spacingY * 20);

                btn.setText("");
                btn.getStylesheets().add("/css/MainTheme.css");

                mainPane.getChildren().add(btn);

            }

        }

    }

    /**
     * Prints directions of clicked button into stacktrace.
     *
     * @param button
     */
    public void getPosition(CustomButton button) {
    }

    /**
     * Starts the game.
     *
     * @param button
     */
    public void playGame(CustomButton button) {

//         String[][] test = new String[3][3];
        
        
        List<CustomButton> microButtons = new ArrayList<>();
        List<CustomButton> allButtons = new ArrayList<>();

        for (Node customButton : mainPane.getChildren()) {
            CustomButton b = (CustomButton) customButton;
            
            allButtons.add(b);
            
            if (b.getMicroId() == button.getMicroId()) {
                microButtons.add(b);
            }

        }
        
        gm.play(button, microButtons, allButtons);
    }    
}
