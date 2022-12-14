package main;

import units.Laser;
import units.Obstacle;
import units.Player;
import units.UnitLoader;
import world.WorldConstructor;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JPanel implements Runnable {

    private final int basicInterval = 32;
    private final int scale = 3;
    public int interval = basicInterval * scale;

    public int width = interval * 10;
    public int height = interval * 10;

    final private double FPS = 60.0;
    private Thread gameThread;

    //UNITS AND OBJECTS
    InputHandler inputHandler = new InputHandler();
    WorldConstructor wc = new WorldConstructor(width / interval, height / interval);
    Player player = new Player(inputHandler, basicInterval, width - (basicInterval + interval), width / 2, height - interval);

    UnitLoader unitLoader = new UnitLoader(-interval, height, width - interval);

    //GAME STATES
    public int gameState;
    final public int HOME_SCREEN = 1;
    final public int PLAY_SCREEN = 2;
    final public int PAUSE_SCREEN = 3;

    public GameFrame(){

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.addKeyListener(inputHandler);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        setupGame();
        startThread();
    }

    private void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void setupGame() {
        gameState = HOME_SCREEN;

    }

    @Override
    public void run() {
        double updateInterval = 1000000000 / FPS; //0.016666 sekund
        double delta = 0;
        long lastUpdate = System.nanoTime(); //nanosekunder för precision
        long currentTime;

        while (gameThread.isAlive()){

            currentTime = System.nanoTime();

            delta += (currentTime - lastUpdate) / updateInterval;
            lastUpdate = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
        //player.laser.update();
        for (Laser laser:
                player.lasers){
            laser.update();
        }
        unitLoader.update();
        for (Obstacle o :
                unitLoader.obstacles) {
            o.update();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponents(g);

        Graphics2D g2 = (Graphics2D) g;

        wc.draw(g2, interval);
        player.draw(g2);
        //player.laser.draw(g2);
        for (Laser laser:
                player.lasers){
            laser.draw(g2);
        }
        for (Obstacle o :
                unitLoader.obstacles) {
            o.draw(g2);
        }
    }

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("SPACE WAR");

        GameFrame gamePanel = new GameFrame();

        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
