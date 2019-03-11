package Server;

import ORM.Manager.KweetManager;
import ORM.Manager.UserManager;
import ORM.Manager.testManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class Test {

    //@Inject
    //private EJBInterface ejbinterface;

    @Inject
    private testManager manager;
    @Inject
    private KweetManager kweetManager;
    @Inject
    private UserManager userManager;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String message(){
        manager.CreateUser();
        manager.CreateKweet();
        manager.CreateUser();
        manager.CreateFollower();
        //kweetManager.getTimeLine(1);
        userManager.getUser(2);
        return "success";
        //return kweetManager.getTimeLine(1).get(0).getContent() + Integer.toString(manager.GetKweet().getUser().getUserId() + userManager.getUser(1).getFollowersId().size()) + kweetManager.getTimeLine(2).get(0).getContent();
    }
}