package ca.bcit.comp2522.termproject.battleground;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
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
 * BattleGround Class to implement the battle scenes and interactions.
 *
 * @author hailinchen
 * @version 0.1 (2022)
 */
public class BattleGround {
    private static final int DEFAULT_PLAYER_HEALTH = 100;
    private static final int DEFAULT_MONSTER_HEALTH = 25;
    private static final int DEFAULT_PLAYER_UPPERBOUND = 11;
    private static final int DEFAULT_PLAYER_LOWER_BOUND = 5;
    private static final int DEFAULT_MONSTER_UPPERBOUND = 11;
    private static final int DEFAULT_MONSTER_LOWER_BOUND = 1;
    private static final int CASE_ZERO = 0;
    private static final int CASE_ONE = 1;
    private static final int CASE_TWO = 2;
    private static final int CASE_THREE = 3;
    private static final int CASE_FOUR = 4;
    private static final int CASE_FIVE = 5;
    private static final int RANDOM_MONSTER_BACKGROUND_UPPERBOUND = 6;
    private static final int RANDOM_BOSS_BACKGROUND_UPPERBOUND = 2;
    private static int mobHp = DEFAULT_MONSTER_HEALTH;
    private static int playerHp = DEFAULT_PLAYER_HEALTH;
    private static int playerAttackUpper = DEFAULT_PLAYER_UPPERBOUND;
    private static int playerAttackLower = DEFAULT_PLAYER_LOWER_BOUND;
    private static int playerHitReturn = 0;
    private static int mobUpperBound = DEFAULT_MONSTER_UPPERBOUND;
    private static int mobLowerBound = DEFAULT_MONSTER_LOWER_BOUND;
    private static boolean isBoss = true;

    /*
     * Random monster background generator.
     */
    private static String randomMonsterBackground() {
        Random rand = new Random();
        int intRandom = rand.nextInt(RANDOM_MONSTER_BACKGROUND_UPPERBOUND);
        return switch (intRandom) {
            case CASE_ZERO -> "dungeon_background1.png";
            case CASE_ONE -> "dungeon_background2.png";
            case CASE_TWO -> "dungeon_background3.png";
            case CASE_THREE -> "dungeon_background4.png";
            case CASE_FOUR -> "dungeon_background5.png";
            case CASE_FIVE -> "dungeon_background6.png";
            default -> "dungeon_background7.png";
        };
    }
    /*
     * Random boss background generator.
     */
    private static String randomBossBackground() {
        Random rand = new Random();
        int intRandom = rand.nextInt(RANDOM_BOSS_BACKGROUND_UPPERBOUND);
        return switch (intRandom) {
            case CASE_ZERO -> "boss_background1.png";
            case CASE_ONE -> "boss_background3.png";
            default -> "boss_background2.png";
        };
    }
    /*
     * Run the interaction between the mob and player after the card is played.
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
     * Calculates the monster's health to the proper health bar sections
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
     * Calculates the boss's health to the proper health bar sections
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
    /*
     * Activates the fade animation in battle scene.
     */
    private static void playFade(final FadeTransition fadeTransition, final Node node) {
        fadeTransition.setFromValue(10);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setNode(node);
        fadeTransition.play();
    }
    /*
     * Sets the x and y coordinates on the battle scene of the given node.
     */
    private static void setNodes(final Node node, final int first, final int second) {
        node.setTranslateX(first);
        node.setTranslateY(second);
    }
    /*
     * Creates the details for the given label.
     */
    private static void setLabels(final Label label, final int spot) {
        label.setTextFill(Color.web("#FFFFFF"));
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, spot));
    }

    /**
     *  Update the battle scene between every player interaction with the mob.
     *
     * @param randomMobBackground a String
     * @param randomMobImage a String
     * @param stage a stage of the battle scene
     * @return a scene of the battleground
     */
    public static Scene updateBattleFight(final String randomMobBackground, final String randomMobImage, final
    Stage stage) {
        Image mobHealth = new Image("file:resources/images/healthBar/HealthBar" + mobHealthChecker() + ".png");
        Image playerHealth = new Image("file:resources/images/healthBar/playerHealthIcon.png");
        Image background = new Image(randomMobBackground);
        Image mobImage = new Image(randomMobImage);
        Image hitReturnImage = new Image("file:resources/images/miscellaneous/hitReturnBackground.png");

        ImageView mobHealthView = new ImageView(mobHealth);
        ImageView mobView = new ImageView(mobImage);
        ImageView backgroundView = new ImageView(background);
        ImageView playerHealthView = new ImageView(playerHealth);
        ImageView hitReturnView = new ImageView(hitReturnImage);

        Label healthLabel = new Label(": " + playerHp);
        Label displayPlayerDamage = new Label("- " + playerHitReturn);
        setLabels(healthLabel, 20);
        setLabels(displayPlayerDamage, 18);

        FadeTransition fadePlayerDamage = new FadeTransition(Duration.millis(1750), displayPlayerDamage);
        FadeTransition fadePlayerDamageBackground = new FadeTransition(Duration.millis(1750), hitReturnView);
        playFade(fadePlayerDamage, displayPlayerDamage);
        playFade(fadePlayerDamageBackground, hitReturnView);

        setNodes(backgroundView, 0, -100);
        setNodes(mobView, 0, -30);
        setNodes(mobHealthView, 0, -300);
        setNodes(playerHealthView, -650, 210);
        setNodes(healthLabel, -598, 210);
        setNodes(hitReturnView, 150, -245);
        setNodes(displayPlayerDamage, 150, -250);

        Button atkBtn = new Button("Some card to attack");
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
     *  Create the monster battle scene.
     * @param stage a stage of a battle scene
     * @return updateBattleFight method
     */
    public static Scene battleMonsterScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/battle_background/%s", randomMonsterBackground());
        String randomMonsterImage = String.format("file:resources/images/monster/%s", Monsters.randomMonsterImage());

        return updateBattleFight(randomBackground, randomMonsterImage, stage);
    }

    /**
     *  Create the boss battle scene.
     * @param stage a stage of a battle scene
     * @return updateBattleFight method
     */
    public static Scene battleBossScene(final Stage stage) {
        String randomBackground = String.format("file:resources/images/battle_background/%s", randomBossBackground());
        String randomBossImage = String.format("file:resources/images/boss/%s", Monsters.randomBossImage());

        return updateBattleFight(randomBackground, randomBossImage, stage);
    }

    /**
     * Sets the new number to the playerHP.
     * @param playerHp an int
     */
    public static void setPlayerHp(final int playerHp) {
        BattleGround.playerHp = playerHp;
    }

    /**
     * Sets the new upperbound to the player attack.
     * @param playerAttackUpper an int
     */
    public static void setPlayerAttackUpper(final int playerAttackUpper) {
        BattleGround.playerAttackUpper = playerAttackUpper;
    }

    /**
     * Sets the new lower-bound to the player attack.
     * @param playerAttackLower an int
     */
    public static void setPlayerAttackLower(final int playerAttackLower) {
        BattleGround.playerAttackLower = playerAttackLower;
    }

    /**
     * Sets the new mob's health.
     * @param mobHp an int
     */
    public static void setMobHp(final int mobHp) {
        BattleGround.mobHp = mobHp;
    }

    /**
     * Sets the new upperbound to the mob attack.
     * @param mobUpperBound an int
     */
    public static void setMobUpperBound(final int mobUpperBound) {
        BattleGround.mobUpperBound = mobUpperBound;
    }

    /**
     * Sets the new lower-bound to the mob attack.
     * @param mobLowerBound an int
     */
    public static void setMobLowerBound(final int mobLowerBound) {
        BattleGround.mobLowerBound = mobLowerBound;
    }

    /**
     * Determines if the player is interacting with the boss.
     * @param isBoss a boolean
     */
    public static void setIsBoss(final boolean isBoss) {
        BattleGround.isBoss = isBoss;
    }


}
