/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WebSockets;

import DTO.FollowerDTO;
import DTO.KweetDTO;
import DTO.UserDTO;
import ORM.Manager.UserManager;
import com.google.gson.Gson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jgeenen
 */
@ServerEndpoint(
        value = "/echo-socket/{userId}"
)
public class ServerEchoEndpoint {

    private static final Logger LOG = Logger.getLogger(ServerEchoEndpoint.class.getName());

    private static Map<String, Session> onlineUsers = new HashMap<>();

    private Gson gson = new Gson();

    @Inject
    UserManager userManager;

    @OnOpen
    public void init(Session session, @PathParam("userId") String userId) {
        onlineUsers.put(userId, session);
        System.out.println("online user size: " + onlineUsers.size());
    }

    @OnMessage
    public void sendMessage(Session session, String kweet) {
        KweetDTO message = gson.fromJson(kweet,KweetDTO.class);
        LOG.log(Level.INFO, "received message with text: {0}", message.getContent());

        System.out.println("onmessage Session:" + session.getId());
        try {
            broadcast(session, message);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(EncodeException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void goClose(Session session, CloseReason closeReason,@PathParam("userId") String userId ) {
        LOG.log(Level.INFO, "session {0} closed with reason {1}", new Object[]{session, closeReason});
        try {
            onlineUsers.remove(userId);
        }
        catch(IllegalStateException ise) {
            //swallow: httpSession allready expired
        }
    }

    @OnError
    public void haveError(Session session, Throwable throwable) {
        LOG.log(
                Level.WARNING,
                new StringBuilder("an error occured for session ").append(session).toString(),
                throwable
        );
    }

    private void broadcast(Session session, KweetDTO message)
            throws IOException, EncodeException {
        Gson gson = new Gson();
        session.getBasicRemote().
                sendText(gson.toJson(message));
        System.out.println("hier begint het: ");
        UserDTO userDTO = new UserDTO(userManager.getUserEntity(message.getUserId()));
        for(FollowerDTO follower: userDTO.getFollowingMe()
            ) {
            if(onlineUsers.containsKey(String.valueOf(follower.getUserId()))){
                onlineUsers.get(follower.getUserId()).getAsyncRemote().sendText(gson.toJson(message));
            }
        }
        }
    }
