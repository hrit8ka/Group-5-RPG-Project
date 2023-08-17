package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    // Player stats
    int level;
    int maxLife;
    int life;
    int maxMana;
    int mana;
    int strength;
    int agility;
    int xp;
    int nextLevelXP;
    int gold;

    // Player inventory
    ArrayList<String> itemNames = new ArrayList<String>();
    ArrayList<Integer> itemQuantities = new ArrayList<Integer>();
    int currentWeaponSlot;
    int currentArmorSlot;

    //object on map
    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootNames[][];
    boolean mapObjectOpened[][];

}
