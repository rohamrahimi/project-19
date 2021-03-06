package model.Target;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum SoldierTargetType {
    ONE_ENEMY, ONE_FRIENDLY_SOLDIER, ENEMY_HERO, FRIENDLY_HERO, ALL_ENEMIES,
    ONE_SOLDIER, ALL_FRIENDLY_SOLDIERS, ONE_ENEMY_MINION, ONE_FRIENDLY_MINION,
    ALL_ENEMIES_IN_A_COLUMN, ONE_RANDOM_MINION_AROUND_FRIENDLY_HERO, ALL_SOLDIERS_IN_THE_ROW_OF_FRIENDLY_HERO,
    ALL_MINIONS_AROUND, ALL_MINIONS_TO_2_CELLS_FURTHER, ALL_FRIENDLY_MINIONS_AROUND_AND_ITSELF,
    ALL_FRIENDLY_MINIONS, ONE_RANDOM_ENEMY_MINION, ALL_ENEMY_MINIONS_AROUND, ITSELF, FRIENDLY_MELEE,
    FRIENDLY_RANGED_AND_HYBRID, ENEMY_HERO_RANGED_AND_HYBRID, FRIENDLY_HERO_RANGED_AND_HYBRID;

    public static ObservableList<SoldierTargetType> getHeroTypes() {
        ObservableList<SoldierTargetType> types = FXCollections.observableArrayList();
        types.addAll(ITSELF, ALL_ENEMIES, ONE_ENEMY, ONE_SOLDIER, ALL_SOLDIERS_IN_THE_ROW_OF_FRIENDLY_HERO);
        return types;
    }

    public static ObservableList<SoldierTargetType> getSpellTypes() {
        ObservableList<SoldierTargetType> types = FXCollections.observableArrayList();
        types.addAll(ONE_FRIENDLY_MINION, ONE_ENEMY_MINION, ONE_SOLDIER, ONE_FRIENDLY_SOLDIER, ONE_ENEMY,
                ENEMY_HERO, ALL_ENEMIES, FRIENDLY_HERO, ALL_FRIENDLY_SOLDIERS, ALL_FRIENDLY_MINIONS,
                ONE_RANDOM_MINION_AROUND_FRIENDLY_HERO, ALL_ENEMIES_IN_A_COLUMN);
        return types;
    }
}
