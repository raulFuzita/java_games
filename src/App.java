import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws Exception {
        
        ExecutorService es = Executors.newCachedThreadPool();

        es.execute(new Apple("One"));
        es.execute(new Apple("Two"));
        es.execute(new Apple("Three"));

        es.shutdown();
    }
}
