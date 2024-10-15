package org.factoriaf5.pizzeriapaca.uploadimage.local.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImageExceptionTest {

    @Test
    void testImageExceptionWithMessage() {
        String message = "This is an image exception";
        
   
        ImageException exception = assertThrows(ImageException.class, () -> {
            throw new ImageException(message);
        });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void testImageExceptionWithMessageAndCause() {
        String message = "This is an image exception with cause";
        Throwable cause = new RuntimeException("Cause of the exception");

       
        ImageException exception = assertThrows(ImageException.class, () -> {
            throw new ImageException(message, cause);
        });

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
