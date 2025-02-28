package muchiri.app;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
@RequestScoped
public class Resource {

    record Message(String msg){}

    @GET
    @Path("s1")
    @Produces(MediaType.APPLICATION_JSON)
    public Response s1() throws InterruptedException {
        Thread.sleep(200);
        var msg =  new Message("Results from serviceOne");
        return Response.ok(msg).build();
    }

    @GET
    @Path("s2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response s2() throws InterruptedException {
        Thread.sleep(200);
        var msg = new Message("Results from serviceTwo");
        return Response.ok(msg).build();
    }
}
