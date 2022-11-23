package ca.bcit.comp2522.termproject.battleground;

import java.util.Random;

public class Monsters extends BattleGround {
    private final int monsterHealth = 20;
    private final int monsterAttack = 2;

    public static String randomMonsterImage() {
        Random rand = new Random();
        int upperbound = 7;
        int intRandom = rand.nextInt(upperbound);
        return switch (intRandom) {
            case 0 -> "armorGuyBattleAxe.gif";
            case 1 -> "armorGuyShield.gif";
            case 2 -> "armorRat.gif";
            case 3 -> "armorSpearRat.gif";
            case 4 -> "mechRat.gif";
            case 5 -> "skullcrusher.gif";
            case 6 -> "dinosaur.gif";
            default -> "monkRat.gif";
        };
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }

    public int getMonsterAttack() {
        return monsterAttack;
    }
}
