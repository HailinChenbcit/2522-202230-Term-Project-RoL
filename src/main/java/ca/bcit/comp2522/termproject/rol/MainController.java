package ca.bcit.comp2522.termproject.rol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button mapBtn;

    /**
     * controller.
     */
    public MainController() {

    }

    @FXML
    private void action() {
        System.out.println("My button function");
    }
    @FXML
    private void toMapView() throws IOException {

    }

    public Button getMapBtn() {
        return mapBtn;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
