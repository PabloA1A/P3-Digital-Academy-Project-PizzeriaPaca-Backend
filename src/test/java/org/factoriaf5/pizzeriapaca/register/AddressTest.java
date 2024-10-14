package org.factoriaf5.pizzeriapaca.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.factoriaf5.pizzeriapaca.users.User;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    private Address address;
    private User user;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("testuser");

        customer = new Customer();
        customer.setUsername("testcustomer");

        address = new Address(user, "123 Main St", "12345", "CityName");
        address.setCustomer(customer);
    }

    @Test
    public void testAddressConstructor() {
        Address newAddress = new Address(user, "456 Elm St", "67890", "AnotherCity");
        assertEquals(user, newAddress.getUser());
        assertEquals("456 Elm St", newAddress.getAddress());
        assertEquals("67890", newAddress.getPostalCode());
        assertEquals("AnotherCity", newAddress.getCity());
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(user, address.getUser());
        assertEquals("123 Main St", address.getAddress());
        assertEquals("12345", address.getPostalCode());
        assertEquals("CityName", address.getCity());
        assertEquals(customer, address.getCustomer());

        User newUser = new User();
        newUser.setUsername("newuser");
        address.setUser(newUser);
        assertEquals(newUser, address.getUser());

        address.setAddress("789 Pine St");
        assertEquals("789 Pine St", address.getAddress());

        address.setPostalCode("54321");
        assertEquals("54321", address.getPostalCode());

        address.setCity("NewCity");
        assertEquals("NewCity", address.getCity());

        Customer newCustomer = new Customer();
        newCustomer.setUsername("newcustomer");
        address.setCustomer(newCustomer);
        assertEquals(newCustomer, address.getCustomer());
    }

    @Test
    public void testSetId() {
        address.setId(1L);
        assertEquals(1L, address.getId());
    }
}

