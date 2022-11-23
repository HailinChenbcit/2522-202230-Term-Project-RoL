package ca.bcit.comp2522.termproject.rol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
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

        Title title = new Title("Realm of Legends");
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

    private void checkSelectionBtn(final Button btnOne, final Button btnTwo, final Button btnThree) {
        if (btnTwo.isDisable() && btnThree.isDisable()) {
            btnOne.setDisable(false);
        } else if (btnOne.isDisable() && btnThree.isDisable()) {
            btnTwo.setDisable(false);
        } else if (btnOne.isDisable() && btnTwo.isDisable()) {
            btnThree.setDisable(false);
        }
    }

    private void setBtnLocation(final ImageView view, final Button button, final int xCord, final int yCord) {
        button.setTranslateX(xCord);
        button.setTranslateY(yCord);
        button.setGraphic(view);
        button.setOnAction(e -> {
            boolean result = PopUpMessages.display("Confirmation Box", "Are you sure?");
            if (result) {
                button.setDisable(true);
            }
        });
    }

    private Scene battleScene(final Stage stage) {
        StackPane battleGround = new StackPane();
        Text text = new Text("Battle Ground!");
        ImageView monsterView = new ImageView(new Image("file:resources/images/monster_lvl1.gif"));
        monsterView.setFitHeight(300);
        monsterView.setPreserveRatio(true);
        monsterView.setLayoutX(100);
        monsterView.setLayoutY(100);

        battleGround.getChildren().addAll(text, monsterView);
        return new Scene(battleGround, 1000, 600);
    }


    private Scene mapScene(final Stage primaryStage) {

        Button backBtn = new Button("Back to Main");
        backBtn.setId("backBtn");
        Button monsterBtn = new Button();
        Button treasureBtn = new Button();
        Button bossBtn = new Button();

        backBtn.getStylesheets().add("file:resources/css/mapStyle.css");

        ImageView monsterView = new ImageView(new Image("file:resources/images/monster.png"));
        ImageView treasureView = new ImageView(new Image("file:resources/images/treasure.png"));
        ImageView bossView = new ImageView(new Image("file:resources/images/boss.png"));
        ImageView backgroundView = new ImageView("file:resources/images/mapBackground.jpg");

//        Line lineOne = new Line();
////        Line lineTwo = new Line(30, 0, 100,0);
//        lineOne.setStartX(0);
//        lineOne.setStartY(0);
//        lineOne.setEndX(-200);
//        lineOne.setEndY(0);
//        lineOne.setStyle("-fx-stroke: DARKGREY;");
//        lineOne.setStyle("-fx-font-size: 20");

        StackPane layout2 = new StackPane();
        StackPane.setAlignment(backBtn, Pos.TOP_RIGHT);

        setBtnLocation(monsterView, monsterBtn, -320, -50);
        setBtnLocation(treasureView, treasureBtn, 0, -50);
        setBtnLocation(bossView, bossBtn, 320, -50);

        backBtn.setOnMousePressed(mouseEvent -> primaryStage.setScene(new Scene(createContent(primaryStage))));

        monsterBtn.setOnAction(e -> {
            boolean result = PopUpMessages.display("Confirmation Box", "Are you sure?");
            if (result) {
                monsterBtn.setDisable(true);
                primaryStage.setScene(battleScene(primaryStage));
            }
        });

        treasureBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.NONE);
            ButtonType btnType = new ButtonType("OK");
            alert.getButtonTypes().setAll(btnType);
            alert.setHeaderText("What's in the treasure box?");
            alert.setContentText("You found a new card! !");
            alert.show();
            treasureBtn.setDisable(true);
        });

        bossBtn.setOnAction(e -> {
            boolean result = PopUpMessages.display("Confirmation Box", "Are you sure?");
            if (result) {
                bossBtn.setDisable(true);
                primaryStage.setScene(battleScene(primaryStage));
            }
        });

        layout2.getChildren().addAll(backgroundView, backBtn, monsterBtn, treasureBtn, bossBtn, lineOne);

        return new Scene(layout2, 1000, 600);
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
            Rectangle bg = new Rectangle(400, 60);
            bg.setStroke(Color.WHITESMOKE);
            bg.setStrokeWidth(2);
            bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.ALICEBLUE);
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
