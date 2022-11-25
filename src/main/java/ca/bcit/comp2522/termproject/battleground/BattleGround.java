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
    private static int hpStatus = 10;

    private static int bossHp = 10;

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

    public static Scene update(final String randomBackground, final String randomMonsterImage, Stage stage) {
        Image monsterHealth = new Image("file:resources/images/healthBar/HealthBar" + hpStatus + ".png");
        Image background = new Image(randomBackground);
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

        if (hpStatus != 0) {
            atkBtn.setOnMousePressed(mouseEvent -> {
                hpStatus -= 1;
                stage.setScene(update( randomBackground, randomMonsterImage, stage));
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
        String randomMonsterImage = String.format("file:resources/images/monster/%s", Monsters.randomMonsterImage());

        return update(randomBackground, randomMonsterImage, stage);
    }

    public static Scene battleBossScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/battle_background/%s", randomBackground());
        String randomBossImage = String.format("file:resources/images/boss/%s", Monsters.randomBossImage());

        return updateBossFight(randomBackground, randomBossImage, stage);
    }
}
