package ca.bcit.comp2522.termproject.rol;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class MainMenu extends Application {
    private Parent createContent(final Stage primaryStage) {
        Pane root = new Pane();

        root.setPrefSize(860, 600);

        try (InputStream is = Files.newInputStream(Paths.get("resources/images/bg.jpg"))) {
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

    private Scene mainMenuScene(final Stage primaryStage) {
        Button btn2 = new Button("Back to Main");
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(btn2);
        return new Scene(layout2, 600, 300);
    }

    private Scene mapScene(final Stage primaryStage) {
        Button backBtn = new Button("Back to Main");
        Button monsterBtn = new Button();
        Button treasureBtn = new Button();
        Button bossBtn = new Button();

        Image monsterIcon = new Image("file:resources/images/monster.png");
        Image treasureIcon = new Image("file:resources/images/treasure.png");
        Image bossIcon = new Image("file:resources/images/boss.png");
        ImageView monsterView = new ImageView(monsterIcon);
        ImageView treasureView = new ImageView(treasureIcon);
        ImageView bossView = new ImageView(bossIcon);

        backBtn.setOnMousePressed(mouseEvent -> primaryStage.setScene(mainMenuScene(primaryStage)));

        StackPane layout2 = new StackPane();

        layout2.getChildren().add(backBtn);
        StackPane.setAlignment(backBtn, Pos.TOP_RIGHT);

        layout2.getChildren().add(monsterBtn);
        monsterBtn.setTranslateX(-420);
        monsterBtn.setTranslateY(-50);
        monsterBtn.setGraphic(monsterView);

        layout2.getChildren().add(treasureBtn);
        treasureBtn.setTranslateX(0);
        treasureBtn.setTranslateY(-50);
        treasureBtn.setGraphic(treasureView);

        layout2.getChildren().add(bossBtn);
        bossBtn.setTranslateX(420);
        bossBtn.setTranslateY(-50);
        bossBtn.setGraphic(bossView);

        return new Scene(layout2, 1600, 800);
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
        public Title(final String name) {
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
        public MenuBox(final MainMenu.MenuItem... items) {
            getChildren().add(createSeparator());

            for (MainMenu.MenuItem item : items) {
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
        public MenuItem(final String name) {
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
