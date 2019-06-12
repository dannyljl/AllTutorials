/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WebSockets;

import DTO.KweetDTO;
import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Decodes {@link Message}s from JSON
 * @author jgeenen
 */
public class JsonDecoder implements Decoder.Text<KweetDTO>{

    private final Gson gson = new Gson();
    
    @Override
    public void init(EndpointConfig config) {

    }
    
    @Override
    public KweetDTO decode(String arg0) throws DecodeException {
        return gson.fromJson(arg0, KweetDTO.class);
    }

    @Override
    public boolean willDecode(String arg0) {
        return true;
    }


    @Override
    public void destroy() {

    }
    
}
