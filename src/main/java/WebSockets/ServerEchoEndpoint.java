/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WebSockets;

import DTO.KweetDTO;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgeenen
 */
@ServerEndpoint(
    value = "/echo-socket",
    encoders = JsonEncoder.class,
    decoders = JsonDecoder.class,
    configurator = HttpSessionProvider.class
)
public class ServerEchoEndpoint {
    
    private static final Logger LOG = Logger.getLogger(ServerEchoEndpoint.class.getName());

    @Inject
    private EchoBean ECHO_BEAN;
    
    private HttpSession httpSession;
    
    private Session session;
    
    @OnOpen
    public void onOpen(EndpointConfig endpointConfig, Session session){
        this.httpSession = HttpSessionProvider.provide(endpointConfig);
        this.session = session;
        LOG.log(Level.INFO, "onOpen: endpointConfig: {0}, session: {1}", new Object[]{endpointConfig, session});
    }
    
    @OnMessage
    public void onMessage(Session session, KweetDTO message){
        LOG.log(Level.INFO, "received message with text: {0}", message.getContent());
        ECHO_BEAN.send(session, message, 2, 1000, 1.2);
    }
    
    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        LOG.log(Level.INFO, "session {0} closed with reason {1}", new Object[]{session, closeReason});
        try{
            httpSession.invalidate();
        } catch(IllegalStateException ise){
            //swallow: httpSession allready expired
        }
    }
    
    @OnError
    public void onError(Session session, Throwable throwable){
        LOG.log(
            Level.WARNING, 
            new StringBuilder("an error occured for session ").append(session).toString(), 
            throwable
        );
    }

    public Session getSession() {
        return session;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }
    
}
