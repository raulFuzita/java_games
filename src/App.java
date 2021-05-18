import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class App extends JFrame {

    private Screen screen;
    private Image bg;
    private Animation a;
    public static void main(String[] args) throws Exception {
        
        DisplayMode dm = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
        App app = new App();
        app.run(dm);
    }

    public void run(DisplayMode dm) {
        screen = new Screen();
        try {
            screen.setFullScreen(dm, new JFrame());
            loadPics();
            movieLoop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            screen.restoreScreen();
        }
    }

    // loads pictures computer to java and adds scene
    public void loadPics() {
        bg = new ImageIcon("resource//images//background.jpg").getImage();
        Image emoji1 = new ImageIcon("resource//images//normal.png").getImage();
        Image emoji2 = new ImageIcon("resource//images//smiling.png").getImage();
        Image emoji3 = new ImageIcon("resource//images//sunglasses.png").getImage();
        a = new Animation();
        a.addScene(emoji1, 250);
        a.addScene(emoji2, 250);
        a.addScene(emoji3, 250);
    }

    // main moview loop
    public void movieLoop() {
        long startingTime = System.currentTimeMillis();
        long cumTime = startingTime;

        while(cumTime - startingTime < 5000) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            a.update(timePassed);

            Graphics g = screen.getFullScreenWindow().getGraphics();
            draw(g);
            g.dispose();

            try {
                Thread.sleep(120);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void draw(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        g.drawImage(a.getImage(), 0, 0, null);
    }
}
