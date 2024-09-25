package org.factoriaf5.pizzeriapaca.products.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ProductNotFoundExceptionTest {

    @Test
    void testAddSuppressed() {
        ProductNotFoundException exception = new ProductNotFoundException("Main Exception");
        Throwable suppressedException = new IllegalArgumentException("Suppressed Exception");
        exception.addSuppressed(suppressedException);

        Throwable[] suppressed = exception.getSuppressed();
        assertEquals(1, suppressed.length);
        assertEquals(suppressedException, suppressed[0]);
    }

    @Test
    void testFillInStackTrace() {
        ProductNotFoundException exception = new ProductNotFoundException("Exception with stack trace");
        Throwable filledException = exception.fillInStackTrace();
        assertNotNull(filledException.getStackTrace());
        assertEquals(exception, filledException);
    }

    @Test
    void testGetCause() {
        Throwable cause = new IllegalArgumentException("Cause of exception");
        ProductNotFoundException exception = new ProductNotFoundException("Exception with cause", cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testGetLocalizedMessage() {
        ProductNotFoundException exception = new ProductNotFoundException("Localized exception message");
        assertEquals("Localized exception message", exception.getLocalizedMessage());
    }

    @Test
    void testGetMessage() {
        ProductNotFoundException exception = new ProductNotFoundException("Exception message");
        assertEquals("Exception message", exception.getMessage());
    }

    @Test
    void testGetStackTrace() {
        ProductNotFoundException exception = new ProductNotFoundException("Exception with stack trace");
        StackTraceElement[] stackTrace = exception.getStackTrace();
        assertNotNull(stackTrace);
        assertTrue(stackTrace.length > 0);
    }

    @Test
    void testGetSuppressed() {
        ProductNotFoundException exception = new ProductNotFoundException("Main Exception");
        assertEquals(0, exception.getSuppressed().length); 

        Throwable suppressedException = new IllegalArgumentException("Suppressed Exception");
        exception.addSuppressed(suppressedException);
        assertEquals(1, exception.getSuppressed().length);
        assertEquals(suppressedException, exception.getSuppressed()[0]);
    }

    @Test
    void testInitCause() {
        ProductNotFoundException exception = new ProductNotFoundException("Main Exception");
        Throwable cause = new IllegalArgumentException("Cause of exception");
        exception.initCause(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testPrintStackTrace() {
        ProductNotFoundException exception = new ProductNotFoundException("Exception with stack trace");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outContent);
        exception.printStackTrace(printStream);

        String printedStackTrace = outContent.toString();
        assertTrue(printedStackTrace.contains("ProductNotFoundException: Exception with stack trace"));
    }

    @Test
    void testPrintStackTrace2() {
        ProductNotFoundException exception = new ProductNotFoundException("Exception with stack trace");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent)); 

        exception.printStackTrace();
        String printedStackTrace = outContent.toString();
        assertTrue(printedStackTrace.contains("ProductNotFoundException: Exception with stack trace"));
    }

    @Test
    void testPrintStackTrace3() {
        ProductNotFoundException exception = new ProductNotFoundException("Exception with stack trace");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outContent);
        exception.printStackTrace(printStream);

        String printedStackTrace = outContent.toString();
        assertTrue(printedStackTrace.contains("ProductNotFoundException: Exception with stack trace"));
    }

    @Test
    void testSetStackTrace() {
        ProductNotFoundException exception = new ProductNotFoundException("Exception with custom stack trace");
        StackTraceElement[] customStackTrace = new StackTraceElement[]{
            new StackTraceElement("ClassName", "methodName", "FileName", 123)
        };

        exception.setStackTrace(customStackTrace);
        StackTraceElement[] returnedStackTrace = exception.getStackTrace();
        assertArrayEquals(customStackTrace, returnedStackTrace);
    }

    @Test
    void testToString() {
        ProductNotFoundException exception = new ProductNotFoundException("Exception with toString");
        String exceptionString = exception.toString();
        assertTrue(exceptionString.contains("ProductNotFoundException: Exception with toString"));
    }
}
