/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import button.CustomButton;
import field.IField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class GeneratedWindowController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateButtons();
    }

    public void generateButtons() {
        int initialXPosition = 90;
        int initialYPosition = 139;
        int buttonWidth = 30;
        int buttonHeight = 30;

        for (int x = 0; x < IField.BoardSizeX; x++) {
            for (int y = 0; y < IField.BoardSizeY; y++) {

                CustomButton btn = new CustomButton(x, y);

                btn.setOnMouseClicked((mouseEvent)
                        -> {
                    getPosition(btn);
                    
                });

                btn.setPrefSize(buttonWidth, buttonHeight);

                btn.setLayoutX(initialXPosition + (buttonWidth * x));
                btn.setLayoutY(initialYPosition + (buttonHeight * y));
                btn.setText("x");

                mainPane.getChildren().add(btn);

            }

        }

    }
    
    public void getPosition(CustomButton button)
    {
        System.out.println(button.getX() + ", " + button.getY());
    }

}
