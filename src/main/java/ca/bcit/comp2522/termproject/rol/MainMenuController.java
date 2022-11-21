package ca.bcit.comp2522.termproject.rol;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * MainMenu class.
 *
 * @author hailinchen
 * @version 0.1
 */
public class MainMenuController extends Application {
    private Parent createContent(final Stage primaryStage) {
        Pane root = new Pane();

        root.setPrefSize(860, 600);

        try (InputStream is = Files.newInputStream(Paths.get("src/main/resources/images/mainBackground.jpg"))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(860);
            img.setFitHeight(600);
            root.getChildren().add(img);
        } catch (IOException e) {
            System.out.println("Cannot load image");
        }

        Title title = new Title("TEEMO TEST");
        title.setTranslateX(250);
        title.setTranslateY(200);

        MenuItem itemExit = new MenuItem("EXIT");
        itemExit.setOnMousePressed(mouseEvent -> System.exit(0));
        MenuItem itemNewGame = new MenuItem("NEW GAME");
        itemNewGame.setOnMousePressed(mouseEvent -> primaryStage.setScene(mapScene(primaryStage)));
        MenuBox vbox = new MenuBox(
                itemNewGame,
                new MenuItem("CONTACT"),
                itemExit);

        vbox.setTranslateX(300);
        vbox.setTranslateY(300);

        root.getChildren().addAll(title, vbox);
        return root;
    }

    private Scene mapScene(final Stage primaryStage) {
        Pane mapLayout = new Pane();
        Button backToMainBtn = new Button("Back to Main");
        Button treasureBtn1 = new Button("treasure");
        Button treasureBtn2 = new Button("treasure");
        Button monsterBtn1 = new Button("monster");
        Button monsterBtn2 = new Button("monster");
        Button bossBtn = new Button("monster");

        backToMainBtn.setLayoutX(0);
        backToMainBtn.setLayoutY(0);

        monsterBtn1.setLayoutX(100);
        monsterBtn1.setLayoutY(100);
        Line line1 = new Line(150, 120, 250, 160);

        treasureBtn1.setLayoutX(250);
        treasureBtn1.setLayoutY(160);
        Line line2 = new Line(300, 180, 350, 260);

        monsterBtn2.setLayoutX(350);
        monsterBtn2.setLayoutY(260);
        Line line3 = new Line(400, 280, 450, 300);

        treasureBtn2.setLayoutX(450);
        treasureBtn2.setLayoutY(300);
        Line line4 = new Line(500, 320, 600, 400);

        bossBtn.setLayoutX(600);
        bossBtn.setLayoutY(400);

        backToMainBtn.setOnMousePressed(mouseEvent -> primaryStage.setScene(new Scene(createContent(primaryStage))));
        mapLayout.getChildren().addAll(backToMainBtn, treasureBtn1, treasureBtn2, monsterBtn1, monsterBtn2, bossBtn,
                line1, line2, line3, line4);
        return new Scene(mapLayout, 860, 600);
    }

    /**
     * Starts the game.
     *
     * @param primaryStage initial stage.
     * @throws Exception Exceptions.
     */
    public void start(final Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent(primaryStage));
        primaryStage.setTitle("Realm of Legends");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static class Title extends StackPane {
        /**
         * Title class to s how game title.
         *
         * @param name a string represents title.
         */
        Title(final String name) {
            Rectangle bg = new Rectangle(300, 60);
            bg.setStroke(Color.WHITESMOKE);
            bg.setStrokeWidth(2);
            bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.DARKSLATEGRAY);
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.BOLD, 50));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
        }
    }

    private static class MenuBox extends VBox {
        MenuBox(final MainMenuController.MenuItem... items) {
            getChildren().add(createSeparator());

            for (MainMenuController.MenuItem item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }

        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(200);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }

    private static class MenuItem extends StackPane {
        MenuItem(final String name) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.DARKBLUE),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.9, Color.BLACK),
                    new Stop(1, Color.DARKBLUE));

            Rectangle bg = new Rectangle(200, 30);
            bg.setOpacity(0.4);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(mouseEvent -> {
                bg.setFill(gradient);
                text.setFill(Color.WHITE);
            });

            setOnMouseExited(mouseEvent -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });

            setOnMousePressed(mouseEvent -> bg.setFill(Color.DARKCYAN));

            setOnMouseReleased(mouseEvent -> bg.setFill(gradient));
        }
    }

    /**
     * The game driver.
     *
     * @param args commandline arguments.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
