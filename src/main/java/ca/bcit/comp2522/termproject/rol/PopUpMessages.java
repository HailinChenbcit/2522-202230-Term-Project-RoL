package ca.bcit.comp2522.termproject.rol;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Popup messages Class.
 */
public class PopUpMessages {

    private static boolean answer;

    /**
     * Displays messages for player when they clicked buttons in the game map.
     * @param title a string represents title of window
     * @param message a string represents message of window
     * @return a boolean
     */
     public static boolean display(final String title, final String message) {
         Stage window = new Stage();
         window.initModality(Modality.APPLICATION_MODAL);
         window.setTitle(title);
         window.setMinWidth(250);
         Label label = new Label();
         label.setText(message);

         Button yesBtn = new Button("Yes");
         Button noBtn = new Button("No");

         yesBtn.setOnAction(e -> {
             answer = true;
             window.close();
         });

         noBtn.setOnAction(e -> {
             answer = false;
             window.close();
         });

         VBox layout = new VBox(10);
         layout.getChildren().addAll(label, yesBtn, noBtn);
         layout.setAlignment(Pos.CENTER);
         Scene scene = new Scene(layout);
         window.setScene(scene);
         window.showAndWait();

         return answer;
     }
}
