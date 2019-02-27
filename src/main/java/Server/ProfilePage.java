package Server;

import ORM.Manager.UserManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/profilePage/{userId}")
public class ProfilePage {

    UserManager userManager = new UserManager();

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String followUser(@PathParam("userId") int userid,String id){
        userManager.CreateFollower(userid, Integer.parseInt(id));
        return "success";
    }

}
