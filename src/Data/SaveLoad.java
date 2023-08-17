package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import AI.Map;
import Main.GamePanel;
import Object.CampingTent;
import Object.Candle;
import Object.OBJ_Armor;
import Object.OBJ_Axe;
import Object.OBJ_Chest;
import Object.OBJ_Key;
import Object.OBJ_Sword;

public class SaveLoad {
    private GamePanel gp;
    private Map<String, Character> itemCreators = new HashMap<String, Character>();

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
        initItemCreators();
    }

    private void initItemCreators() {
        itemCreators.put("Camping Tent", new CampingTent(gp));
        itemCreators.put("candle", new Candle(gp));
        itemCreators.put("key", new OBJ_Key(gp));
        itemCreators.put("axe", new OBJ_Axe(gp));
        itemCreators.put("sword", new OBJ_Sword(gp));
        itemCreators.put("armor", new OBJ_Armor(gp));
        itemCreators.put("chest", new OBJ_Chest(gp));
    }

    public Character getObject(String itemName) {
        return itemCreators.get(itemName);
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.strength = gp.player.strength;
            ds.agility = gp.player.agility;
            ds.xp = gp.player.xp;
            ds.nextLevelXP = gp.player.nextLevelXP;
            ds.gold = gp.player.gold;

            // player inventory
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemQuantities.add(gp.player.inventory.get(i).amount);
            }

            // player equipment
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentArmorSlot = gp.player.getCurrentArmorSlot();

            // objects on map
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    // check if the object is null
                    if (gp.obj[mapNum][i] != null) {
                        ds.mapObjectNames[mapNum][i] = "NA";
                    } else {
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        if (gp.obj[mapNum][i].loot != null) {
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].opened;

                    }
                }
            }
            // write the DataStorage object to the file
            oos.writeObject(ds);

        } catch (Exception e) {
            System.out.println("Error saving game");
        }
    }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // read the DataStorage object from the file
            DataStorage ds = (DataStorage) ois.readObject();

            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.strength = ds.strength;
            gp.player.agility = ds.agility;
            gp.player.xp = ds.xp;
            gp.player.nextLevelXP = ds.nextLevelXP;
            gp.player.gold = ds.gold;

            // player inventory
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemQuantities.get(i);
            }

            // player equipment
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentArmor = gp.player.inventory.get(ds.currentArmorSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();

            // objects on map
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    // check if the object is null
                    if (ds.mapObjectLootNames[mapNum][i].equals("NA")) {
                        gp.obj[mapNum][i] = null;
                    } else {
                        gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if (ds.mapObjectLootNames[mapNum][i] != null) {
                            gp.obj[mapNum][i].loot = getObject(ds.mapObjectLootNames[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if (gp.obj[mapNum][i].opened == true) {
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].down2;
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading game");
        }
    }
}
