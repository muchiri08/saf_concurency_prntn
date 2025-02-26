public class Sample1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        var r1 = serviceOne();
        var r2 = serviceTwo();
        System.out.println(System.currentTimeMillis());

        save(r1, r2);
    }

    static String serviceOne() throws InterruptedException {
        Thread.sleep(300);
        return "Service One Result!";
    }

    static String serviceTwo() throws InterruptedException {
        Thread.sleep(300);
        return "Service Two Result!";
    }

    static void save(String result1, String result2) {
        System.out.println("Saving %s and %s".formatted(result1, result2));
    }
}