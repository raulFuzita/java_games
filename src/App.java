import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class App extends JFrame {

    private Animation a;
    private ScreenManager s;
    private Image bg;

    private static final DisplayMode modes1[] = {
        new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(800, 600, 16, 0),
        new DisplayMode(640, 480, 32, 0),
        new DisplayMode(640, 480, 24, 0),
        new DisplayMode(640, 480, 16, 0)
    };
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }
    
    // load images and add scenes
    public void loadImages() {
        bg = new ImageIcon("resource//images//background.jpg").getImage();
        Image face1 = new ImageIcon("resource//images//normal.png").getImage();
        Image face2 = new ImageIcon("resource//images//smiling.png").getImage();

        a = new Animation();
        a.addScene(face1, 250);
        a.addScene(face2, 250);
    }

    // main method called from main
    public void run() {
        s = new ScreenManager();
        try {
            DisplayMode dm = s.findFirstCompatibleMode(modes1);
            s.setFullScreen(dm);
            loadImages();
            movieLoop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.restoreScreen();
        }
    }

    // play movie
    public void movieLoop() {
        long startingTime = System.currentTimeMillis();
        long cumTime = startingTime;
        while (cumTime - startingTime < 5000) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            a.update(timePassed);

            // draw and update screen
            Graphics2D g = s.getGraphics();
            draw(g);
            g.dispose();
            s.update();

            try {
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // draws graphics
    public void draw(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        g.drawImage(a.getImage(), 0, 0, null);
    }
    

}
