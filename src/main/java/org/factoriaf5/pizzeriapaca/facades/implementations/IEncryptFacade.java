package org.factoriaf5.pizzeriapaca.facades.implementations;

public interface IEncryptFacade {

    String encode(String type, String data);

    String decode(String type, String data);

}