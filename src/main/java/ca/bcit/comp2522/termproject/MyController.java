package ca.bcit.comp2522.termproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MyController {
    @FXML
    private Label myfxml;

    @FXML
    private Button mybtn;

    public MyController() {
    }

    @FXML
    private void initialize() {
        System.out.println("Initialize");
    }

}
