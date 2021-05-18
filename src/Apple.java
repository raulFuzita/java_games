import java.util.concurrent.ThreadLocalRandom;

public class Apple implements Runnable {

    String name;
    int time;

    public Apple(String name) {
        this.name = name;
        time = ThreadLocalRandom.current().nextInt(999);
    }
    
    @Override
    public void run() {
        try {
            System.out.printf("%s is sleeping for %d\n", name, time);
            Thread.sleep(time);
            System.out.printf("%s is done ", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}