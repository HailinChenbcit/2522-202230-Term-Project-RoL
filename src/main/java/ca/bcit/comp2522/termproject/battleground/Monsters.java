package ca.bcit.comp2522.termproject.battleground;

import java.util.Random;

/**
 * Holds the methods for both monster and boss images.
 *
 * @author hailinchen, jasonlui
 * @version 0.1 (2022)
 */
public class Monsters extends BattleGround {

    private static final int CASE_ZERO = 0;
    private static final int CASE_ONE = 1;
    private static final int CASE_TWO = 2;
    private static final int CASE_THREE = 3;
    private static final int CASE_FOUR = 4;
    private static final int CASE_FIVE = 5;
    private static final int CASE_SIX = 6;
    private static final int BOSS_IMAGE_UPPERBOUND = 3;
    private static final int MONSTER_IMAGE_UPPERBOUND = 7;

    /**
     * Generates a randomly selected string that represents monster image.
     * @return a string that represents the monster image
     */
    protected static String randomBossImage() {
        Random rand = new Random();
        int intRandom = rand.nextInt(BOSS_IMAGE_UPPERBOUND);
        return switch (intRandom) {
            case CASE_ZERO -> "boss1.gif";
            case CASE_ONE -> "boss2.gif";
            case CASE_TWO -> "boss3.gif";
            default -> "boss4.gif";
        };
    }

    /**
     * Generates a randomly selected string that represents the boss image.
     * @return a string that represents the monster image
     */
    protected static String randomMonsterImage() {
        Random rand = new Random();
        int intRandom = rand.nextInt(MONSTER_IMAGE_UPPERBOUND);
        return switch (intRandom) {
            case CASE_ZERO -> "armorGuyBattleAxe.gif";
            case CASE_ONE -> "armorGuyShield.gif";
            case CASE_TWO -> "armorRat.gif";
            case CASE_THREE -> "armorSpearRat.gif";
            case CASE_FOUR -> "mechRat.gif";
            case CASE_FIVE -> "skullcrusher.gif";
            case CASE_SIX -> "dinosaur.gif";
            default -> "monkRat.gif";
        };
    }
}
