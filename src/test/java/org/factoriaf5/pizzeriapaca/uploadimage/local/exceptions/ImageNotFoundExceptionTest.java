package org.factoriaf5.pizzeriapaca.uploadimage.local.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImageNotFoundExceptionTest {

    @Test
    void testImageNotFoundExceptionWithMessage() {
        String message = "This image was not found";

        ImageNotFoundException exception = assertThrows(ImageNotFoundException.class, () -> {
            throw new ImageNotFoundException(message);
        });

        assertEquals(message, exception.getMessage());
    }

    @Test
    void testImageNotFoundExceptionWithMessageAndCause() {
        String message = "This image was not found due to some issue";
        Throwable cause = new RuntimeException("Underlying cause");

        ImageNotFoundException exception = assertThrows(ImageNotFoundException.class, () -> {
            throw new ImageNotFoundException(message, cause);
        });

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
