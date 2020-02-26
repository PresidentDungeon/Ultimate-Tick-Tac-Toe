/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class GameBoardController implements Initializable {

    @FXML
    private JFXButton one;
    @FXML
    private JFXButton two;
    @FXML
    private JFXButton three;
    @FXML
    private JFXButton four;
    @FXML
    private JFXButton five;
    @FXML
    private JFXButton six;
    @FXML
    private JFXButton seven;
    @FXML
    private JFXButton eight;
    @FXML
    private JFXButton nine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void play(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        clickedButton.setText("Eyy!");
        System.out.println(clickedButton.getId());
        
    }
    
}
