package Server;

import AccountTypes.AccountType;
import DTO.KweetDTO;
import ORM.Manager.KweetManager;
import Server.bindings.Secured;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    @Secured({AccountType.USER})
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
