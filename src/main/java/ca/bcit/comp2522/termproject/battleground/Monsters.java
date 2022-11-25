package ca.bcit.comp2522.termproject.battleground;

import java.util.Random;

public class Monsters extends BattleGround {

    static String monsterImage;

    protected static String randomBossImage() {
        Random rand = new Random();
        int upperbound = 3;
        int intRandom = rand.nextInt(upperbound);
        return switch (intRandom) {
            case 0 -> "boss1.gif";
            case 1 -> "boss2.gif";
            case 2 -> "boss3.gif";
            default -> "boss4.gif";
        };
    }

    protected static String randomMonsterImage() {
        Random rand = new Random();
        int upperbound = 7;
        int intRandom = rand.nextInt(upperbound);
        monsterImage = switch (intRandom) {
            case 0 -> "armorGuyBattleAxe.gif";
            case 1 -> "armorGuyShield.gif";
            case 2 -> "armorRat.gif";
            case 3 -> "armorSpearRat.gif";
            case 4 -> "mechRat.gif";
            case 5 -> "skullcrusher.gif";
            case 6 -> "dinosaur.gif";
            default -> "monkRat.gif";
        };
        return monsterImage;
    }

    public static String getMonsterImage() {
        return monsterImage;
    }

    public static void setMonsterImage(String monsterImage) {
        Monsters.monsterImage = monsterImage;
    }
}
