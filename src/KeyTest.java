import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;

public class KeyTest extends Core implements KeyListener {

    private String mess = "";

    public static void main(String[] args) {
        new KeyTest().run();
    }

    // init also call init from superclass
    public void init() {
        super.init();
        Window w = s.getFullScreenWindow();
        w.setFocusTraversalKeysEnabled(false);
        w.addKeyListener(this);
        mess = "press escape to exit";
    }

    @Override
    public synchronized void draw(Graphics2D g) {
        Window w = s.getFullScreenWindow();
        g.setColor(w.getBackground());
        g.fillRect(0, 0, s.getWidth(), s.getHeight());
        g.setColor(w.getForeground());
        g.drawString(mess, 30, 30);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ESCAPE) {
            stop();
        } else {
            mess = "Pressed : " + KeyEvent.getKeyText(keyCode);
            e.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        mess =  "Released : " + KeyEvent.getKeyText(keyCode);
        e.consume();
    }
    
}
