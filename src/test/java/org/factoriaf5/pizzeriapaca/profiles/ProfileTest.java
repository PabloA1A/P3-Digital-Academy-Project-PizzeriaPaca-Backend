package org.factoriaf5.pizzeriapaca.profiles;

import static org.junit.jupiter.api.Assertions.*;

import org.factoriaf5.pizzeriapaca.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfileTest {

    private Profile profile;
    private User user;

    @BeforeEach
    void setUp() {
        
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        
    
        profile = new Profile("test@example.com", user);
    }

    @Test
    void testGetId() {
        profile.setId(1L);
        assertEquals(1L, profile.getId());
    }

    @Test
    void testSetId() {
        profile.setId(2L);
        assertEquals(2L, profile.getId());
    }

    @Test
    void testGetEmail() {
        assertEquals("test@example.com", profile.getEmail());
    }

    @Test
    void testSetEmail() {
        profile.setEmail("newemail@example.com");
        assertEquals("newemail@example.com", profile.getEmail());
    }

    @Test
    void testGetUser() {
        assertEquals(user, profile.getUser());
        assertEquals(1L, profile.getUser().getId());
        assertEquals("testUser", profile.getUser().getUsername());
    }

    @Test
    void testSetUser() {
        // Crear un nuevo objeto User para asociarlo
        User newUser = new User();
        newUser.setId(2L);
        newUser.setUsername("newUser");

        profile.setUser(newUser);
        assertEquals(newUser, profile.getUser());
        assertEquals(2L, profile.getUser().getId());
        assertEquals("newUser", profile.getUser().getUsername());
    }

    @Test
    void testProfileConstructor() {
        Profile newProfile = new Profile("another@example.com", user);
        assertEquals("another@example.com", newProfile.getEmail());
        assertEquals(user, newProfile.getUser());
    }

}

