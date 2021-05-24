import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;

public class Look extends Core implements KeyListener, MouseMotionListener {
    
    private Image bg;
    private Robot robot;
    private Point mouse;
    private Point center;
    private Point image;
    private boolean centering;
    private String mess = "";

    public static void main(String[] args) {
        new Look().run();
    }

    public void init() {
        super.init();
        mouse = new Point();
        center = new Point();
        image = new Point();
        centering = false;

        try {
            robot = new Robot();
            recenterMouse();
            mouse.x = center.x;
            mouse.y = center.y;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Window w = s.getFullScreenWindow();
        w.setFocusTraversalKeysEnabled(false);
        w.addMouseMotionListener(this);
        w.addKeyListener(this);
        bg = new ImageIcon("resource//images//background.jpg").getImage();
    }

    @Override
    public synchronized void draw(Graphics2D g) {
        int w = s.getWidth();
        int h = s.getHeight();
        
        image.x %= w;
        image.y %= h;
        if (image.x < 0) {
            image.x += w;
        }
        if (image.y < 0) {
            image.y += h;
        }
        
        int x = image.x;
        int y = image.y;

        g.drawImage(bg, x, y, null);
        g.drawImage(bg, x-w, y, null);
        g.drawImage(bg, x, y-h, null);
        g.drawImage(bg, x-w, y-h, null);
    }

    // recenter the mouse using robot
    private synchronized void recenterMouse() {
        Window w = s.getFullScreenWindow();
        if (robot != null && w.isShowing()) {
            center.x = w.getWidth() / 2;
            center.y = w.getHeight() / 2;
            SwingUtilities.convertPointToScreen(center, w);
            centering = true;
            robot.mouseMove(center.x, center.y);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public synchronized void mouseMoved(MouseEvent e) {
        if (centering && center.x == e.getX() && center.y == e.getY()) {
            centering = false;
        } else {
            int dx = e.getX() - mouse.x;
            int dy = e.getY() - mouse.y;
            image.x += dx;
            image.y += dy;
            recenterMouse();
        }

        mouse.x = e.getX();
        mouse.y = e.getY();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
