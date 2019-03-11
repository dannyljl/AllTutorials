package Server;

import ORM.Entity.KweetEntity;
import ORM.Manager.KweetManager;
import ORM.Manager.UserManager;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/startpage/{userId}")
public class StartPage {

    @Inject
    KweetManager kweetManager = new KweetManager();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String GetTimeLine(@PathParam("userId") int userid){
        Gson gson = new Gson();
        return gson.toJson(kweetManager.getTimeLine(userid));
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String CreateKweet(@PathParam("userId") int userid,String content){
        kweetManager.CreateKweet(userid,content);
        return "success";
    }

    @Path("search")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String SearchKweet(String searchcontent){
        Gson gson = new Gson();
        return gson.toJson(kweetManager.SearchKweet(searchcontent));

    }
}
