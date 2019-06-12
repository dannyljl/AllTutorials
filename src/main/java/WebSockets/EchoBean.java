/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WebSockets;

import DTO.KweetDTO;

import javax.ejb.Asynchronous;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;


@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EchoBean {

    @EJB
    private EchoBean delegate;

    @Asynchronous
    public void send(Session session, KweetDTO message, int repeats, long delay, double delayMultiplier ){
        try {
            synchronized(session){
                session.getBasicRemote().sendObject(message);
            }
            Thread.sleep(delay);
        } catch (InterruptedException | IOException | EncodeException ex) {
            throw new IllegalStateException(ex);
        }
        }

}
