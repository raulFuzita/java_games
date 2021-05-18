import java.awt.Image;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Animation {
    
    private List<OneScene> scenes = new CopyOnWriteArrayList<>();;
    private AtomicInteger sceneIndex = new AtomicInteger(0);
    private AtomicLong movieTime = new AtomicLong(0);
    private AtomicLong totalTime = new AtomicLong(0);

    public void start() {
        movieTime.set(0);
        sceneIndex.set(0);
    }

    // Set time for each scene
    public void addScene(Image i, long t) {
        totalTime.getAndUpdate(v -> v + t);
        scenes.add(new OneScene(i, totalTime.get()));
    }

    // change scener
    public void update(long timePassed) {
        if (scenes.size() > 1) {
            movieTime.getAndUpdate(v -> v + timePassed);
            if (movieTime.get() >= totalTime.get()) {
                movieTime.set(0);
                sceneIndex.set(0);
            }

            while(movieTime.get() > getScene(sceneIndex.get()).endTime) {
                sceneIndex.getAndIncrement();
            }
        }
    }

    // get animations current scene (aka image)
    public Image getImage() {
        if (scenes.isEmpty()) {
            return null;
        } else {
            return getScene(sceneIndex.get()).emoji;
        }
    }

    // get scene
    private OneScene getScene(int x) {
        return (OneScene) scenes.get(x);
    }

    // Inner class
    private class OneScene {

        Image emoji;
        long endTime;

        public OneScene(Image emoji, long endTime) {
            this.emoji = emoji;
            this.endTime = endTime;
            start();
        }
    }

}
