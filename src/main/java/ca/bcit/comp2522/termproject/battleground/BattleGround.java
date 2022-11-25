package ca.bcit.comp2522.termproject.battleground;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;

/**
 * BattleGround Class.
 *
 * @author hailinchen
 * @version 0.1
 */
public class BattleGround {
    private static int monsterHp = 10;

    private static int bossHp = 10;

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

    public static Scene updateMonsterFight(final String randomMonsterBackground, final String randomMonsterImage, Stage stage) {
        Image monsterHealth = new Image("file:resources/images/healthBar/HealthBar" + monsterHp + ".png");
        Image background = new Image(randomMonsterBackground);
        Image monsterImage = new Image(randomMonsterImage);

        ImageView monsterHealthView = new ImageView(monsterHealth);
        ImageView monsterView = new ImageView(monsterImage);
        ImageView backgroundView = new ImageView(background);

        backgroundView.setTranslateY(-100);
        monsterView.setTranslateY(-30);
        monsterHealthView.setTranslateY(-300);

        Button atkBtn = new Button("Some card to attack");
        atkBtn.setTranslateY(300);

        StackPane battleGround = new StackPane();
        battleGround.getChildren().addAll(backgroundView, monsterView, monsterHealthView, atkBtn);

        if (monsterHp != 0) {
            atkBtn.setOnMousePressed(mouseEvent -> {
                monsterHp -= 1;
                stage.setScene(updateMonsterFight( randomMonsterBackground, randomMonsterImage, stage));
            });
        }
        return new Scene(battleGround, 1520, 820);
    }

    public static Scene updateBossFight(final String randomBossBackground, final String randomBossImage, Stage stage) {
        Image bossHealth = new Image("file:resources/images/healthBar/HealthBar" + bossHp + ".png");
        Image bossBackground = new Image(randomBossBackground);
        Image bossImage = new Image(randomBossImage);

        ImageView bossHealthView = new ImageView(bossHealth);
        ImageView bossView = new ImageView(bossImage);
        ImageView backgroundView = new ImageView(bossBackground);

        backgroundView.setTranslateY(-100);
        bossView.setTranslateY(-30);
        bossHealthView.setTranslateY(-300);

        Button atkBtn = new Button("Some card to attack");
        atkBtn.setTranslateY(300);

        StackPane battleGround = new StackPane();
        battleGround.getChildren().addAll(backgroundView, bossView, bossHealthView, atkBtn);

        if (monsterHp != 0) {
            atkBtn.setOnMousePressed(mouseEvent -> {
                monsterHp -= 1;
                stage.setScene(updateMonsterFight( randomBossBackground, randomBossImage, stage));
            });
        }
        return new Scene(battleGround, 1520, 820);
    }

    public static Scene battleMonsterScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/battle_background/%s", randomMonsterBackground());
        String randomMonsterImage = String.format("file:resources/images/monster/%s", Monsters.randomMonsterImage());

        return updateMonsterFight(randomBackground, randomMonsterImage, stage);
    }

    public static Scene battleBossScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/battle_background/%s", randomBossBackground());
        String randomBossImage = String.format("file:resources/images/boss/%s", Monsters.randomBossImage());

        return updateBossFight(randomBackground, randomBossImage, stage);
    }
}
