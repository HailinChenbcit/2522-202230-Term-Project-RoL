package ca.bcit.comp2522.termproject.map;

import ca.bcit.comp2522.termproject.battleground.BattleGround;
import ca.bcit.comp2522.termproject.rol.MainMenu;
import ca.bcit.comp2522.termproject.rol.PopUpMessages;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Map class.
 *
 * @author hailinchen
 * @version 0.1
 */
public class GameMap {
    private static void setBtnLocation(final ImageView view, final Button button, final int xCord) {
        button.setTranslateX(xCord);
        button.setTranslateY(-50);
        button.setGraphic(view);
        button.setOnAction(e -> {
            boolean result = PopUpMessages.display("Confirmation Box", "Are you sure?");
            if (result) {
                button.setDisable(true);
            }
        });
    }

    /**
     * Create a map scene for user, a map contains monster, treasure and boss.
     * @param primaryStage stage from mainMenu.
     * @return a Scene
     */
    public static Scene mapScene(final Stage primaryStage) {

        Button backBtn = new Button("Back to Main");
        backBtn.setId("backBtn");
        Button monsterBtn = new Button();
        Button treasureBtn = new Button();
        Button bossBtn = new Button();

        backBtn.getStylesheets().add("file:resources/css/mapStyle.css");


        Image monsterIcon = new Image("file:resources/images/miscellaneous/monster.png");
        Image treasureIcon = new Image("file:resources/images/miscellaneous/treasure.png");
        Image bossIcon = new Image("file:resources/images/miscellaneous/boss.png");
        Image background = new Image("file:resources/images/miscellaneous/mapBackground.jpg");

        Line line1 = new Line(-200, 0, 0,0);
        Line line2 = new Line(-200, 0, 0,0);

        line1.setTranslateX(-150);
        line1.setTranslateY(-50);

        line2.setTranslateX(170);
        line2.setTranslateY(-50);

        line1.setStroke(Color.DARKGREY);
        line1.setStrokeWidth(10);
        line1.setStyle("-fx-line: 20");
        line1.getStrokeDashArray().addAll(2d, 21d);

        line2.setStroke(Color.DARKGREY);
        line2.setStrokeWidth(10);
        line2.setStyle("-fx-line: 20");
        line2.getStrokeDashArray().addAll(2d, 21d);

        ImageView monsterView = new ImageView(monsterIcon);
        ImageView treasureView = new ImageView(treasureIcon);
        ImageView bossView = new ImageView(bossIcon);
        ImageView backgroundView = new ImageView(background);

        backBtn.setOnMousePressed(mouseEvent -> primaryStage.setScene(new Scene(MainMenu.createContent(primaryStage))));

        StackPane layout2 = new StackPane();
        StackPane.setAlignment(backBtn, Pos.TOP_RIGHT);

        setBtnLocation(monsterView, monsterBtn, -320);

        setBtnLocation(treasureView, treasureBtn, 0);

        setBtnLocation(bossView, bossBtn, 320);

        monsterBtn.setOnAction(e -> {
            boolean result = PopUpMessages.display("Confirmation Box", "Are you sure?");
            if (result) {
                BattleGround.setIsBoss(false);
                BattleGround.setMobHp(50);
                BattleGround.setMobLowerBound(1);
                BattleGround.setMobUpperBound(11);
                monsterBtn.setDisable(true);
                primaryStage.setScene(BattleGround.battleMonsterScene(primaryStage));
            }
        });

        treasureBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.NONE);
            ButtonType btnType = new ButtonType("OK");
            alert.getButtonTypes().setAll(btnType);
            alert.setHeaderText("What's in the treasure box?");
            alert.setContentText("New Gear! You're health and attacks have increased!");
            alert.show();
            BattleGround.setPlayerHp(125);
            BattleGround.setPlayerAttackLower(10);
            BattleGround.setPlayerAttackUpper(25);
            treasureBtn.setDisable(true);
        });

        bossBtn.setOnAction(e -> {
            boolean result = PopUpMessages.display("Confirmation Box", "Are you sure?");
            if (result) {
                BattleGround.setIsBoss(true);
                BattleGround.setMobHp(100);
                BattleGround.setMobLowerBound(6);
                BattleGround.setMobUpperBound(21);
                bossBtn.setDisable(true);
                primaryStage.setScene(BattleGround.battleBossScene(primaryStage));
            }
        });

        layout2.getChildren().addAll(backgroundView, backBtn, monsterBtn, treasureBtn, bossBtn, line1, line2);

        return new Scene(layout2, 1000, 600);
    }

}
