import java.awt.Image;

public class Sprite {
    
    private Animation a;
    private float x;
    private float y;
    private float vx;
    private float vy;


    public Sprite(Animation a) {
        this.a = a;
    }

    // chnage position
    public void update(long timePassed) {
        x += vx * timePassed;
        y += vy * timePassed;
        a.update(timePassed);
    }

    // get x position
    public float getX() {
        return x;
    }

    // get x position
    public float getY() {
        return y;
    }

    // set sprite x position
    public void setX(float x) {
        this.x = x;
    }

    // set sprite x position
    public void setY(float y) {
        this.y = y;
    }

    // get sprite width
    public int getWidth() {
        return a.getImage().getWidth(null);
    }

    // get sprite width
    public int getHeight() {
        return a.getImage().getHeight(null);
    }

    // get horizontal velocity
    public float getVelocityX() {
        return vx;
    }

    // get vertical velocity
    public float getVelocityY() {
        return vy;
    }

    // set horizonal velocity
    public void setVelocityX(float vx) {
        this.vx = vx;
    }

    // set horizonal velocity
    public void setVelocityY(float vy) {
        this.vy = vy;
    }

    // get sprite / image
    public Image getImage() {
        return a.getImage();
    }

}
