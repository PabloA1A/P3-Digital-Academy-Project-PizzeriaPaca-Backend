package org.factoriaf5.pizzeriapaca.uploadimage.exceptions;

public class ImageNotFoundException extends ImageException {

    public ImageNotFoundException(String message) {
        super(message);
    }

    public ImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
