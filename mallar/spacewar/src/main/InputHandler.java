package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    public boolean shoot = false;
    public boolean left = false;
    public boolean right = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE){
            shoot = true;
        }

        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
            left = true;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE){
            shoot = false;
        }

        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
            left = false;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            right = false;
        }
    }
}
