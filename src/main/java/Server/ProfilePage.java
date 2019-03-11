package Server;

import DTO.UserDTO;
import ORM.Manager.UserManager;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/profilePage/{visitorId}")
public class ProfilePage {

    @Inject
    private UserManager userManager = new UserManager();
    private Gson gson = new Gson();

    @Path("{visitedId}")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String followUser(@PathParam("visitorId") int followedId,@PathParam("visitedId") int followerId){
        userManager.CreateFollower(followedId, followerId);
        return "success";
    }

    @Path("{visitedId}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String followUser(@PathParam("visitedId") int visitedId){
        return gson.toJson(userManager.getUser(visitedId));
    }

    @Path("edit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String EditProfile(@PathParam("visitorId") int visitorId, String profileInfo){
        UserDTO user = gson.fromJson(profileInfo, UserDTO.class);
        return gson.toJson(userManager.editUser(visitorId,user));
    }




}
