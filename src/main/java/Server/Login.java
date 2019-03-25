package Server;

import ORM.Entity.UserEntity;
import ORM.Manager.LoginManager;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class Login {

    //@Inject
    //private EJBInterface ejbinterface;

    @Inject
    private LoginManager loginManager = new LoginManager();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response Login(String userJson){
        Gson g = new Gson();
        UserEntity attemptUser = g.fromJson(userJson,UserEntity.class);
        UserEntity user;
        String json = "";
        user = loginManager.attemptLogin(attemptUser.getUsername(),attemptUser.getPassword());
        if (user != null){
             json = g.toJson(user);
        }
        return Response.ok(json).build();
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String CreateUser(String userJson){
        Gson g = new Gson();
        UserEntity user = g.fromJson(userJson,UserEntity.class);
        if (loginManager.CheckAvailable(user.getUsername()) == null){
            loginManager.CreateUser(user.getUsername(),user.getPassword());
            return "account created with username:" + user.getUsername().toString();
        }
        return "failed";
    }


}
