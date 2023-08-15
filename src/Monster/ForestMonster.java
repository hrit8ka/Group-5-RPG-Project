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
        
    }
}
