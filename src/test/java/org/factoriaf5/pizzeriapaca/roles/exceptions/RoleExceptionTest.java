package org.factoriaf5.pizzeriapaca.roles.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class RoleExceptionTest {

    @Test
    void testRoleExceptionMessage() {
        String message = "Role not found";
        RoleException exception = new RoleException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testRoleExceptionMessageAndCause() {
        String message = "Role not found";
        Throwable cause = new Throwable("Cause of the exception");
        RoleException exception = new RoleException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testRoleExceptionCause() {
        Throwable cause = new Throwable("Cause of the exception");
        RoleException exception = new RoleException(cause.getMessage(), cause);
        assertEquals(cause.getMessage(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testRoleExceptionNoArgs() {
        RoleException exception = new RoleException(null);
        assertNull(exception.getMessage());
    }
}
