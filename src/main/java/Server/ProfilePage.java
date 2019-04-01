package Server;

import DTO.UserDTO;
import ORM.Manager.KweetManager;
import ORM.Manager.UserManager;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/profilepage/{visitedId}")
public class ProfilePage {

    @Inject
    private UserManager userManager;

    @Inject
    private KweetManager kweetManager;
    private Gson gson = new Gson();

    @Path("{visitorId}")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String followUser(@PathParam("visitorId") int followedId, @PathParam("visitedId") int followerId){
        userManager.CreateFollower(followedId, followerId);
        return "success";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response GetUser(@PathParam("visitedId") int visitedId){
        String userjson = gson.toJson(userManager.getUser(visitedId));
        return Response.ok(userjson).build();

    }

    @Path("edit/{visitorId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response EditProfile(@PathParam("visitorId") int visitorId, @PathParam("visitedId") int visitedId, String profileInfoJson){
        String json = "";
        if(visitedId == visitorId){
            UserDTO user = gson.fromJson(profileInfoJson, UserDTO.class);
            json = gson.toJson(userManager.editUser(visitorId,user));
        }
        return Response.ok(json).build();
    }

    @Path("kweets")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLatestKweets(@PathParam("visitedId") int visitedId){
        String json = gson.toJson(kweetManager.getRecentKweets(visitedId));
        return Response.ok(json).build();

    }




}
