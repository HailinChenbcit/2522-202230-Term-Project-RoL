package ca.bcit.comp2522.termproject.battleground;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

/**
 * BattleGround Class.
 * @author hailinchen
 * @version 0.1
 */
public class BattleGround {

    private static String randomBackground() {
        Random rand = new Random();
        int upperbound = 6;
        int intRandom = rand.nextInt(upperbound);
        return switch (intRandom) {
            case 0 -> "dungeon_background1.png";
            case 1 -> "dungeon_background2.png";
            case 2 -> "dungeon_background3.png";
            case 3 -> "dungeon_background4.png";
            case 4 -> "dungeon_background5.png";
            case 5 -> "dungeon_background6.png";
            default -> "dungeon_background7.png";
        };
    }
    public static Scene battleScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/%s", randomBackground());
        Image background = new Image(randomBackground);
        ImageView backgroundView = new ImageView(background);
        backgroundView.setTranslateY(-100);
        StackPane battleGround = new StackPane();
        Text text = new Text("Battle Ground!");
        battleGround.getChildren().addAll(backgroundView, text);
        return new Scene(battleGround, 1520, 820);
    }
}
