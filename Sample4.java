import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class Sample4 {

    final static String BASE_URL = "http://localhost:8080/api/";
    public static void main(String[] args) {
        var es = Executors.newVirtualThreadPerTaskExecutor();
        var f1 = es.submit(() -> serviceOne());
        var f2 = es.submit(() -> serviceTwo());

        try {
            save(f1.get(),
             f2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    static String serviceOne() {
        return "Service One Result!";
    }

    static String serviceTwo() {
        return "Service Two Result!";
    }

    static void save(String result1, String result2) {
        System.out.println("Saving %s and %s".formatted(result1, result2));
    }
}
