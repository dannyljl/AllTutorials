/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WebSockets;

import DTO.KweetDTO;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Encodes {@link KweetDTO}s to JSON
 * @author jgeenen
 */
public class JsonEncoder implements Encoder.Text<KweetDTO> {
    
    private final Gson gson = new Gson();

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(KweetDTO message) throws EncodeException {
        return null;
    }
}
