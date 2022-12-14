package units;

import main.InputHandler;

import java.awt.*;

public class Player extends Unit{

    private final InputHandler inputHandler;
    private int laserCount = 0;
    public Laser[] lasers = new Laser[10];
    boolean shoot = false;
    private int shootCoolDown = 0;

    public Player(InputHandler inputHandler, int leftEdge, int rightEdge, int spawnX, int spawnY){
        this.inputHandler = inputHandler;
        for (int i = 0; i < 10; i++) {
            lasers[i] = new Laser(spawnY - 40);
        }

        screenX = spawnX;
        screenY = spawnY;

        hitBox.x = 10;
        hitBox.y = 30;
//        hitBoxDefaultX = hitBox.x;
//        hitBoxDefaultY = hitBox.y;
        hitBox.width = 72;
        hitBox.height = 50;

        super.leftEdge = leftEdge;
        super.rightEdge = rightEdge;
        speed = 5;
        maxHealth = 3;
        currentHealth = maxHealth;
        attack = 1;

        image = loadSprite("player");
    }

    public void update() {
        if (shoot){
            shootCoolDown++;
            if (shootCoolDown == 20){
                shoot = false;
                shootCoolDown = 0;
            }
        } else if (inputHandler.shoot){
            shoot = true;
            laserCount++;
            if (laserCount == 10){
                laserCount = 0;
            }
            lasers[laserCount].setUpLaser(screenX - 1, 1, speed + 5);
            //inputHandler.shoot = false;
        }

        if(inputHandler.left){
            if (screenX > leftEdge){
                screenX -= speed;
            }
        } else if (inputHandler.right) {
            if (screenX < rightEdge) {
                screenX += speed;
            }
        }

        if (invincible){
            spriteCount++;
            if (spriteCount == 30){
                spriteCount = 0;
                invincible = false;
            }
        }
    }
    public void draw(Graphics2D g2){

        if (spriteCount < 5){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        } else if (spriteCount < 10){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } else if (spriteCount < 15){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        } else if (spriteCount < 20){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } else if (spriteCount < 25){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        } else if (spriteCount < 30){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }

        g2.drawImage(image, screenX, screenY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);
    }
}
