import java.util.concurrent.StructuredTaskScope;

public class Sample5 {
    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope<>()) {
            var subTask1 = scope.fork(() -> serviceOne());
            var subTask2 = scope.fork(() -> serviceTwo());

            try {
                scope.join();
                save(subTask1.get(), subTask2.get());
            } catch (InterruptedException e) {
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
