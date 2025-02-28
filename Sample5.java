import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class Sample5 {

    final static String BASE_URL = "http://localhost:8080/api/";

    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var subTask1 = scope.fork(() -> serviceOne());
            var subTask2 = scope.fork(() -> serviceTwo());

            try {
                scope.join().throwIfFailed();
                save(subTask1.get(), subTask2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
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
