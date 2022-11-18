package ca.bcit.comp2522.termproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Game driver class.
 *
 * @author hailinchen
 * @version 0.1
 */
public class RolDriver extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        FXMLLoader fx = new FXMLLoader();
        URL location = fx.getClassLoader().getResource("ca/bcit/comp2522/termproject/fxml/myfxml.fxml");
        fx.setLocation(location);
        AnchorPane root = fx.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setHeight(1000);
        stage.setWidth(1000);
        stage.show();
    }

    /**
     * The game driver.
     *
     * @param args
     */
    public static void main(final String[] args) {
        launch(args);
    }

}
