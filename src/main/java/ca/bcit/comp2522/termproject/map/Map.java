package ca.bcit.comp2522.termproject.map;

import ca.bcit.comp2522.termproject.battleground.BattleGround;
import ca.bcit.comp2522.termproject.rol.MainMenu;
import ca.bcit.comp2522.termproject.treasure.Treasure;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Map class.
 *
 * @author hailinchen
 * @version 0.1
 */
public class Map {
    private int level;
    private Treasure treasure;
    private BattleGround battle;

    /**
     * Constructor of a Map.
     *
     * @param level    an int
     * @param treasure a Treasure object
     * @param battle   a BattleGround object
     */
    public Map(final int level, final Treasure treasure, final BattleGround battle) {
        this.level = level;
        this.treasure = treasure;
        this.battle = battle;
    }

}
