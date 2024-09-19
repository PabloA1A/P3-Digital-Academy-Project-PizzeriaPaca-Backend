package org.factoriaf5.pizzeriapaca.security.facades.encryptations;

import java.util.Base64;

import org.factoriaf5.pizzeriapaca.security.implementations.IEncoder;

public class Base64Encoder implements IEncoder {

    @Override
    public String encode(String data) {
        String dataEncoded = Base64.getEncoder().encodeToString(data.getBytes());
        return dataEncoded;
    }

    public String decode(String data) {
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        String dataDecoded = new String(decodedBytes);
        return dataDecoded;
    }

}
