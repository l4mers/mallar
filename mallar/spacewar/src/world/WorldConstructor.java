package world;

import main.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class WorldConstructor {

    private BufferedImage image;

    Utility ut = new Utility();

    int[] randomSubX;
    int[] randomSubY;

    int worldWidth, worldHeight;

    public WorldConstructor(int worldWidth, int worldHeight){
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        loadImage();
        loadSubID();
    }

    private void loadSubID() {
        randomSubX = new int[worldWidth];
        randomSubY = new int[worldHeight];
        Random random = new Random();

        for (int i = 0; i < worldWidth; i++) {
            randomSubX[i] = random.nextInt(1, 3);
            randomSubY[i] = 2;
        }
    }

    public void draw(Graphics g2, int interval){

        int row = 0;
        int col = 0;

        while (row < worldWidth && col < worldHeight){

            int worldX = row * interval;
            int worldY = col * interval;

            g2.drawImage(ut.getSubImage(image, randomSubX[0], 2, interval, interval), worldX, worldY, null);

            row++;
            if (row  == worldWidth){
                row = 0;
                col++;
            }
        }
    }

    private void loadImage() {
        try {
            image = ut.scaleImage(ImageIO.read(new File("src/resource/background.png")), 96 * 2, 96 * 2);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
