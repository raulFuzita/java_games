import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;

public class MouseInput extends Core 
implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener {

    private String mess = "";
    public static void main(String[] args) {
        new MouseInput().run();
    }

    public void init() {
        super.init();
        Window w = s.getFullScreenWindow();
        w.setFocusTraversalKeysEnabled(false);
        w.addMouseListener(this);
        w.addMouseMotionListener(this);
        w.addMouseWheelListener(this);
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
    public void mouseWheelMoved(MouseWheelEvent e) {
        mess = "moving mouse wheel";
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        mess = "you pressed down the mouse";
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mess = "you released the mouse";
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        mess = "you are dragging the mouse";
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mess = "you are moving the mouse";
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
