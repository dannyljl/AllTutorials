package Server;

import DTO.UserDTO;
import ORM.Entity.UserEntity;
import ORM.Manager.LoginManager;
import ORM.Manager.UserManager;
import Utility.LoginContainer;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class Login {

    //@Inject
    //private EJBInterface ejbinterface;

    @Inject
    private LoginContainer loginContainer;

    @Inject
    private UserManager userManager;

    @Inject
    private LoginManager loginManager = new LoginManager();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(String userJson){
        Gson g = new Gson();
        UserEntity attemptUser = g.fromJson(userJson,UserEntity.class);
        String json = "";
        try {
            UserDTO user = loginContainer.getUser(attemptUser.getUsername(), attemptUser.getPassword());
            json = g.toJson(user);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return Response.ok(json).build();
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CreateUser(String userJson){
        Gson g = new Gson();
        String json = "";
        UserEntity createdUser = null;
        UserEntity user = g.fromJson(userJson,UserEntity.class);
        if (loginManager.CheckAvailable(user.getUsername()) == null){
            createdUser = loginManager.CreateUser(user.getUsername(),user.getPassword());
            json = g.toJson(createdUser);
        }
        return Response.ok(json).build();

    }


}
