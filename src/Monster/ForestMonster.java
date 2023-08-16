package Monster;

import Main.GamePanel;
import Object.Fireball;
import Character.Character;
public class ForestMonster extends Character{
    GamePanel gp;
    public ForestMonster(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = monsterType;
        name = "Forest Monster";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 8;
        life = maxLife;
        attack = 4;
        defense = 2;
        xp = 5;
        projectile = new Fireball(gp);

        getImage();
    }

    public void getImage(){
        up1 = setUp("src/monsters/forestmonster_1.png",gp.tileSize, gp.tileSize);
        up2 = setUp("src/monsters/forestmonster_2.png",gp.tileSize, gp.tileSize);
        down1 = setUp("src/monsters/forestmonster_3.png",gp.tileSize, gp.tileSize);
        down2 = setUp("src/monsters/forestmonster_4.png",gp.tileSize, gp.tileSize);
        left1 = setUp("src/monsters/forestmonster_5.png",gp.tileSize, gp.tileSize);
        left2 = setUp("src/monsters/forestmonster_6.png",gp.tileSize, gp.tileSize);
        
    }

    public void update(){
        super.update();
        
    }
}
