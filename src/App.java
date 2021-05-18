import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class App extends JFrame {

    private Screen s;
    private Image bg;
    private Image emoji;
    private boolean loaded = false;
    public static void main(String[] args) throws Exception {
        
        DisplayMode dm = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
        App app = new App();
        app.run(dm);
    }

    public void run(DisplayMode dm) {
        getContentPane().setBackground(Color.PINK);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 24));

        s = new Screen();

        try {
            s.setFullScreen(dm, this);
            loadpics();
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.restoreScreen();
        }
        
    }

    // loads pictures
    public void loadpics() {
        bg = new ImageIcon("resource//images//background.jpg").getImage();
        emoji = new ImageIcon("resource//images//smiling.png").getImage();
        loaded = true;
        repaint();
    }

    // This method is called by JFrame automatically
    public void paint(Graphics g) {
        super.paint(g);
        // Make the text smoth
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        g.drawString("This is gonna be awesome", 200, 200);

        if (loaded) {
            g.drawImage(bg, 0, 0, null);
            g.drawImage(emoji, 100, 50, null);
        }
    }
}
