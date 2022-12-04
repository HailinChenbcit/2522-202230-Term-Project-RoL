package ca.bcit.comp2522.termproject.battleground;

import javafx.animation.FadeTransition;
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
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * BattleGround Class.
 *
 * @author hailinchen
 * @version 0.1
 */
public class BattleGround {
    private static int mobHp = 10;
    private static int playerHp = 100;
    private static int playerAttackUpper = 11;
    private static int playerAttackLower = 5;
    private static int playerHitReturn = 0;
    private static int mobUpperBound = 11;
    private static int mobLowerBound = 1;
    private static boolean isBoss = true;
    /*
        Randomize background for each battleground.
     */
    private static String randomMonsterBackground() {
        Random rand = new Random();
        int intRandom = rand.nextInt(6);
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
        int intRandom = rand.nextInt(2);
        return switch (intRandom) {
            case 0 -> "boss_background1.png";
            case 1 -> "boss_background3.png";
            default -> "boss_background2.png";
        };
    }
    /*
        Randomize hit stats during battle.
     */
    private static void battleInteraction() {
        int randomPlayerAttack = ThreadLocalRandom.current().nextInt(playerAttackLower, playerAttackUpper);
        int randomMobAttack = ThreadLocalRandom.current().nextInt(mobLowerBound, mobUpperBound);
        playerHitReturn = randomPlayerAttack;
        playerHp -= randomMobAttack;
        mobHp -= randomPlayerAttack;
        if (playerHp <= 0) {
            System.exit(0);
        }
    }
    /*
        Check monster current health.
     */
    private static int monsterHealthChecker() {
        if (mobHp <= 0) {
            return 0;
        } else if (mobHp <= 5) {
            return 1;
        } else if (mobHp <= 10) {
            return 2;
        } else if (mobHp <= 15) {
            return 3;
        } else if (mobHp <= 20) {
            return 4;
        } else if (mobHp <= 25) {
            return 5;
        } else if (mobHp <= 30) {
            return 6;
        } else if (mobHp <= 35) {
            return 7;
        } else if (mobHp <= 40) {
            return 8;
        } else if (mobHp <= 45) {
            return 9;
        }
        return 10;
    }
    /*
        Check boss current health.
     */
    private static int mobHealthChecker() {
        if (isBoss) {
            if (mobHp <= 0) {
                return 0;
            } else if (mobHp <= 10) {
                return 1;
            } else if (mobHp <= 20) {
                return 2;
            } else if (mobHp <= 30) {
                return 3;
            } else if (mobHp <= 40) {
                return 4;
            } else if (mobHp <= 50) {
                return 5;
            } else if (mobHp <= 60) {
                return 6;
            } else if (mobHp <= 70) {
                return 7;
            } else if (mobHp <= 80) {
                return 8;
            } else if (mobHp <= 90) {
                return 9;
            }
            return 10;
        } else {
            return monsterHealthChecker();
        }
    }


    /**
     * Creates scene during battle.
     * @param randomMobBackground a string represents boss background
     * @param randomMobImage a string represents boss image
     * @param stage main stage.
     * @return a new scene
     */
    public static Scene updateBattleFight(final String randomMobBackground, final String randomMobImage,
                                          final Stage stage) {
        Image mobHealth = new Image("file:resources/images/healthBar/HealthBar" + mobHealthChecker() + ".png");
        Image playerHealth = new Image("file:resources/images/healthBar/playerHealthIcon.png");
        Image background = new Image(randomMobBackground);
        Image mobImage = new Image(randomMobImage);
        Image hitReturnImage = new Image("file:resources/images/hitReturnBackground.png");

        ImageView mobHealthView = new ImageView(mobHealth);
        ImageView mobView = new ImageView(mobImage);
        ImageView backgroundView = new ImageView(background);
        ImageView playerHealthView = new ImageView(playerHealth);
        ImageView hitReturnView = new ImageView(hitReturnImage);

        Label healthLabel = new Label(": " + playerHp);
        Label displayPlayerDamage = new Label("- " + playerHitReturn);
        healthLabel.setTextFill(Color.web("#FFFFFF"));
        healthLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        displayPlayerDamage.setTextFill(Color.web("#FFFFFF"));
        displayPlayerDamage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));

        FadeTransition fadePlayerDamage = new FadeTransition(Duration.millis(1750), displayPlayerDamage);
        FadeTransition fadePlayerDamageBackground = new FadeTransition(Duration.millis(1750), hitReturnView);

        fadePlayerDamage.setFromValue(10);
        fadePlayerDamage.setToValue(0);
        fadePlayerDamage.setCycleCount(1);
        fadePlayerDamage.setAutoReverse(true);

        fadePlayerDamageBackground.setFromValue(10);
        fadePlayerDamageBackground.setToValue(0);
        fadePlayerDamageBackground.setCycleCount(1);
        fadePlayerDamageBackground.setAutoReverse(true);

        fadePlayerDamage.setNode(displayPlayerDamage);
        fadePlayerDamage.play();
        fadePlayerDamageBackground.setNode(hitReturnView);
        fadePlayerDamageBackground.play();


        backgroundView.setTranslateY(-100);
        mobView.setTranslateY(-30);
        mobHealthView.setTranslateY(-300);
        playerHealthView.setTranslateY(210);
        playerHealthView.setTranslateX(-650);
        healthLabel.setTranslateY(210);
        healthLabel.setTranslateX(-598);
        hitReturnView.setTranslateY(-245);
        hitReturnView.setTranslateX(150);
        displayPlayerDamage.setTranslateY(-250);
        displayPlayerDamage.setTranslateX(150);

        Button atkBtn = new Button("Attack!");
        atkBtn.setTranslateY(300);

        StackPane battleGround = new StackPane();
        battleGround.getChildren().addAll(backgroundView, mobView, mobHealthView, atkBtn, playerHealthView,
                healthLabel, hitReturnView, displayPlayerDamage);

        if (mobHp > 0) {
            atkBtn.setOnMousePressed(mouseEvent -> {
                battleInteraction();
                stage.setScene(updateBattleFight(randomMobBackground, randomMobImage, stage));
            });
        }
        return new Scene(battleGround, 1520, 820);
    }

    /**
     * Chooses monster image and background image randomly.
     * @param stage main stage.
     * @return a new scene.
     */
    public static Scene battleMonsterScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/battle_background/%s", randomMonsterBackground());
        String randomMonsterImage = String.format("file:resources/images/monster/%s", Monsters.randomMonsterImage());

        return updateBattleFight(randomBackground, randomMonsterImage, stage);
    }

    /**
     * Chooses boss image and background image randomly.
     * @param stage main stage.
     * @return a new scene.
     */
    public static Scene battleBossScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/battle_background/%s", randomBossBackground());
        String randomBossImage = String.format("file:resources/images/boss/%s", Monsters.randomBossImage());

        return updateBattleFight(randomBackground, randomBossImage, stage);
    }

    /**
     * Default player HP.
     * @param playerHp an int
     */
    public static void setPlayerHp(final int playerHp) {
        BattleGround.playerHp = playerHp;
    }

    /**
     * Player attack stat upperbound.
     * @param playerAttackUpper an int
     */
    public static void setPlayerAttackUpper(final int playerAttackUpper) {
        BattleGround.playerAttackUpper = playerAttackUpper;
    }

    /**
     * Player attack stat lower-bound.
     * @param playerAttackLower an int
     */
    public static void setPlayerAttackLower(final int playerAttackLower) {
        BattleGround.playerAttackLower = playerAttackLower;
    }

    /**
     * Sets boss's HP.
     * @param mobHp an int.
     */
    public static void setMobHp(final int mobHp) {
        BattleGround.mobHp = mobHp;
    }

    /**
     * Sets upperbound HP for boss.
     * @param mobUpperBound an int
     */
    public static void setMobUpperBound(final int mobUpperBound) {
        BattleGround.mobUpperBound = mobUpperBound;
    }

    /**
     * Sets lower-bound HP for boss.
     * @param mobLowerBound an int
     */
    public static void setMobLowerBound(final int mobLowerBound) {
        BattleGround.mobLowerBound = mobLowerBound;
    }

    /**
     * Sets if current battle is against boss.
     * @param isBoss a boolean
     */
    public static void setIsBoss(final boolean isBoss) {
        BattleGround.isBoss = isBoss;
    }
}
