/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WebSockets;

import java.io.Serializable;

/**
 * Represents a simple message for websockets
 * @author jgeenen
 */
public class Message implements Serializable{

    private String sender;
    
    private String text;

    public Message() {
    }

    public Message(String text, String sender) {
        this.text = text;
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
