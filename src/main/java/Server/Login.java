package Server;

import ORM.Entity.UserEntity;
import ORM.Manager.LoginManager;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class Login {

    //@Inject
    //private EJBInterface ejbinterface;

    private LoginManager loginManager = new LoginManager();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String Login(String userJson){
        Gson g = new Gson();
        UserEntity user = g.fromJson(userJson,UserEntity.class);
        if (loginManager.attemptLogin(user.getUsername(),user.getPassword()) != null){
            return "I'm in";
        }
        return "failed";
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