/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WebSockets;

import DTO.FollowerDTO;
import DTO.KweetDTO;
import ORM.Entity.UserEntity;
import ORM.Manager.KweetManager;
import ORM.Manager.UserManager;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jgeenen
 */
@ServerEndpoint(
        value = "/echo-socket/{userId}",
        encoders = JsonEncoder.class,
        decoders = JsonDecoder.class
)
public class ServerEchoEndpoint {

    private static final Logger LOG = Logger.getLogger(ServerEchoEndpoint.class.getName());

    private static HashMap<String, Session> onlineUsers = new HashMap<>();

    private Session session;

    @Inject
    UserManager userManager;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        System.out.println("pathparam: " + userId);
        System.out.println("onopen Session:" + session.getId());
        onlineUsers.put(userId, session);
        this.session = session;
        LOG.log(Level.INFO, "onOpen: endpointConfig: {0}, session: {1}", new Object[]{session});
    }

    @OnMessage
    public void onMessage(Session session, KweetDTO message) {
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
    public void onClose(Session session, CloseReason closeReason,@PathParam("userId") String userId ) {
        LOG.log(Level.INFO, "session {0} closed with reason {1}", new Object[]{session, closeReason});
        try {
            onlineUsers.remove(userId);
        }
        catch(IllegalStateException ise) {
            //swallow: httpSession allready expired
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOG.log(
                Level.WARNING,
                new StringBuilder("an error occured for session ").append(session).toString(),
                throwable
        );
    }

    public Session getSession() {
        return session;
    }

    private void broadcast(Session session, KweetDTO message)
            throws IOException, EncodeException {
        Gson gson = new Gson();
        session.getBasicRemote().
                sendText(gson.toJson(message));
        System.out.println("hier begint het: ");
        System.out.println(gson.toJson(message));
        System.out.println("en dit is de gebruiker: " + gson.toJson(userManager.getUserEntity(message.getUserId()).getFollowingMe()));
        for(UserEntity follower: userManager.getUserEntity(message.getUserId()).getFollowingMe()
            ) {
            if(onlineUsers.containsKey(follower.getUserId())){
                onlineUsers.get(follower.getUserId()).getBasicRemote().sendText(gson.toJson(message));
            }
        }
        }
    }
