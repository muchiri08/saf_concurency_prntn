import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//block, loose, debug
public class Sample2 {
    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);
        var f1 = es.submit(() -> serviceOne());
        var f2 = es.submit(() -> serviceTwo());
        
        try {
            save(f1.get(), f2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        es.shutdown();
    }

    static String serviceOne() {
        System.out.println("S1: " + Thread.currentThread().getName());
        return "Service One Result!";
    }

    static String serviceTwo() {
        System.out.println("S2: " + Thread.currentThread().getName());
        return "Service Two Result!";
    }

    static void save(String result1, String result2) {
        System.out.println("SAVE: " + Thread.currentThread().getName());
        System.out.println("Saving %s and %s".formatted(result1, result2));
    }
}
