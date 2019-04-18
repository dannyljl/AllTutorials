package Server;

import DTO.KweetDTO;
import ORM.Manager.KweetManager;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/startpage")
public class StartPage {

    @Inject
    KweetManager kweetManager;

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetTimeLine(@PathParam("userId") int userid){
        Gson gson = new Gson();

        String json = gson.toJson(kweetManager.getTimeLine(userid));
        return Response.ok(json).build();
    }

    @PUT
    @Path("{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public KweetDTO CreateKweet(@PathParam("userId") int userid, String content){
        return kweetManager.CreateKweet(userid,content);
    }

    @Path("search")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String SearchKweet(String searchcontent){
        Gson gson = new Gson();
        return gson.toJson(kweetManager.SearchKweet(searchcontent));

    }
}
