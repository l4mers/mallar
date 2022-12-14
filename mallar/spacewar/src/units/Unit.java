package units;

import main.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Unit {

    protected BufferedImage image;

    public Rectangle hitBox = new Rectangle(0, 0, 0, 0);
    //public int hitBoxDefaultX, hitBoxDefaultY;

    //protected boolean collision = false;

    public int leftEdge, rightEdge;
    protected int screenX, screenY;

    protected boolean invincible = false;

    protected int spriteCount = 0;

    protected String name;
    protected int speed;
    protected int maxHealth;
    protected int currentHealth;
    protected int attack;
    protected boolean alive = false;


    protected void setAction(){
    }

    public void update(){
        setAction();

        //switch ()

    }
    public void draw(Graphics2D g2, int playerX, int playerY, int screenX, int screenY, int interval){


    }
    protected BufferedImage loadSprite(String name) {
        String path = "src/resource/" + name + ".png";
        Utility utility = new Utility();
        BufferedImage img = null;
        try{
            img = utility.scaleImage(ImageIO.read(new File(path)), 96, 96);
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
}
