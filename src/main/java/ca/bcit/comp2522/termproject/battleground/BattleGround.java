package ca.bcit.comp2522.termproject.battleground;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

/**
 * BattleGround Class.
 *
 * @author hailinchen
 * @version 0.1
 */
public class BattleGround {
    private static int mobHp = 10;
    private static int playerHp = 100;

    private static int bossHp = 10;

    private static String randomBackground() {

    private static String randomMonsterBackground() {
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

    private static String randomBossBackground() {
        Random rand = new Random();
        int upperbound = 2;
        int intRandom = rand.nextInt(upperbound);
        return switch (intRandom) {
            case 0 -> "boss_background1.png";
            case 1 -> "boss_background3.png";
            default -> "boss_background2.png";
        };
    }

    public static Scene updateBattleFight(final String randomMobBackground, final String randomMobImage, Stage stage) {
        Image mobHealth = new Image("file:resources/images/healthBar/HealthBar" + mobHp + ".png");
        Image playerHealth = new Image("file:resources/images/healthBar/playerHealthIcon.png");
        Image background = new Image(randomMobBackground);
        Image mobImage = new Image(randomMobImage);

        ImageView mobHealthView = new ImageView(mobHealth);
        ImageView mobView = new ImageView(mobImage);
        ImageView backgroundView = new ImageView(background);
        ImageView playerHealthView = new ImageView(playerHealth);

        Label healthLabel = new Label(": " + playerHp);
        healthLabel.setTextFill(Color.web("#FFFFFF"));
        healthLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

        backgroundView.setTranslateY(-100);
        mobView.setTranslateY(-30);
        mobHealthView.setTranslateY(-300);
        playerHealthView.setTranslateY(210);
        playerHealthView.setTranslateX(-650);
        healthLabel.setTranslateY(210);
        healthLabel.setTranslateX(-598);

        Button atkBtn = new Button("Some card to attack");
        atkBtn.setTranslateY(300);

        StackPane battleGround = new StackPane();
        battleGround.getChildren().addAll(backgroundView, mobView, mobHealthView, atkBtn, playerHealthView, healthLabel);

        if (mobHp != 0) {
            atkBtn.setOnMousePressed(mouseEvent -> {
                mobHp--;
                playerHp--;
                stage.setScene(updateBattleFight( randomMobBackground, randomMobImage, stage));
            });
        }
        return new Scene(battleGround, 1520, 820);
    }


    public static Scene updateBossFight(final String randomBackground, final String randomBossImage, Stage stage) {
        Image bossHealth = new Image("file:resources/images/healthBar/HealthBar" + bossHp + ".png");
        Image background = new Image(randomBackground);
        Image bossImage = new Image(randomBossImage);

        ImageView monsterView = new ImageView(bossImage);
        ImageView backgroundView = new ImageView(background);
        ImageView bossHealthView = new ImageView(bossHealth);

        backgroundView.setTranslateY(-100);
        monsterView.setTranslateY(-30);
        bossHealthView.setTranslateY(-300);

        Button atkBtn = new Button("Some card to attack");
        atkBtn.setTranslateY(300);

        StackPane battleGround = new StackPane();
        battleGround.getChildren().addAll(backgroundView, monsterView, bossHealthView, atkBtn);

        if (bossHp != 0) {
            atkBtn.setOnMousePressed(mouseEvent -> {
                bossHp -= 1;
                stage.setScene(updateBossFight( randomBackground, randomBossImage, stage));
            });
        }
        return new Scene(battleGround, 1520, 820);

    }

    public static Scene battleScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/battle_background/%s", randomBackground());

    public static Scene battleMonsterScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/battle_background/%s", randomMonsterBackground());

        String randomMonsterImage = String.format("file:resources/images/monster/%s", Monsters.randomMonsterImage());

        return updateBattleFight(randomBackground, randomMonsterImage, stage);
    }

    public static Scene battleBossScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/battle_background/%s", randomBossBackground());
        String randomBossImage = String.format("file:resources/images/boss/%s", Monsters.randomBossImage());

        return updateBattleFight(randomBackground, randomBossImage, stage);
    }

    public static Scene battleBossScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/battle_background/%s", randomBackground());
        String randomBossImage = String.format("file:resources/images/boss/%s", Monsters.randomBossImage());

        return updateBossFight(randomBackground, randomBossImage, stage);
    }
}
