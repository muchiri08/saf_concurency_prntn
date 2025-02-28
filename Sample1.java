import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Sample1 {

    final static String BASE_URL = "http://localhost:8080/api/";

    public static void main(String[] args) throws InterruptedException, IOException {
        
        System.out.println(System.currentTimeMillis());
        var r1 = serviceOne();//block 200
        var r2 = serviceTwo();//block  200

        save(r1, r2);
    }

    static String serviceOne() throws InterruptedException, IOException {
        //dsjhfsdhf
        var message = external("s1");
        //fkdfhsdfu
        return message.msg();
    }

    static String serviceTwo() throws InterruptedException, IOException {
        var message = external("s2");
        return message.msg();
    }

    static void save(String result1, String result2) {
        System.out.println(System.currentTimeMillis());
        System.out.println("Saving:: %s and %s".formatted(result1, result2));
    }

    static Message external(String path) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        var body = response.body();

        return objecMapper(body);
    }

    static Message objecMapper(String input) {
        String msg = input.split("\"msg\":\"")[1].split("\"")[0];
        return new Message(msg);
    }

    static record Message(String msg) {
    }
}