import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//write, read, debug
public class Sample3 {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> serviceOne())
                .whenComplete((result, error) -> {
                    if (error != null) {
                        error.printStackTrace();
                    }
                });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> serviceTwo())
                .whenComplete((result, error) -> {
                    if (error != null) {
                        error.printStackTrace();
                    }
                });

        cf1.runAfterBoth(cf2, () -> {
            try {
                save(cf1.get(), cf2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        System.out.println("At the end of the main method!");
    }

    static String serviceOne() {
        System.out.println("S1: " + Thread.currentThread().getName());
        return "Service One Result!";
        // throw new RuntimeException("Boom!");
    }

    static String serviceTwo() {
        System.out.println("S2: " + Thread.currentThread().getName());
        return "Service Two Result!";
    }

    static void save(String result1, String result2) {
        System.out.println("Saving %s and %s".formatted(result1, result2));
    }
}
