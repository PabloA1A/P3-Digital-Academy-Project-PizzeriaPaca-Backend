package org.factoriaf5.pizzeriapaca.register;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("testUser", "password123", "test@example.com", "John", "Doe");
    }


    @Test
    void testSetAndGetId() {
        customer.setId(1L);
        assertEquals(1L, customer.getId());
    }

    @Test
    void testSetAndGetUsername() {
        customer.setUsername("newUser");
        assertEquals("newUser", customer.getUsername());
    }

    @Test
    void testSetAndGetPassword() {
        customer.setPassword("newPassword");
        assertEquals("newPassword", customer.getPassword());
    }

    @Test
    void testSetAndGetEmail() {
        customer.setEmail("newemail@example.com");
        assertEquals("newemail@example.com", customer.getEmail());
    }

    @Test
    void testSetAndGetFirstName() {
        customer.setFirstName("Jane");
        assertEquals("Jane", customer.getFirstName());
    }

    @Test
    void testSetAndGetLastName() {
        customer.setLastName("Smith");
        assertEquals("Smith", customer.getLastName());
    }

}
