package org.factoriaf5.pizzeriapaca.profiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.factoriaf5.pizzeriapaca.users.User;
import org.junit.jupiter.api.Test;

public class ProfileTest {

    @Test
    void testProfileConstructor() {
        User user = new User();
        Profile profile = new Profile("test@example.com", user);
        assertEquals("test@example.com", profile.getEmail());
        assertEquals(user, profile.getUser());
    }

    @Test
    void testProfileNoArgsConstructor() {
        Profile profile = new Profile();
        assertNull(profile.getEmail());
        assertNull(profile.getUser());
    }

    @Test
    void testProfileAllArgsConstructor() {
        User user = new User();
        Profile profile = new Profile(1L, "test@example.com", user);
        assertEquals(1L, profile.getId());
        assertEquals("test@example.com", profile.getEmail());
        assertEquals(user, profile.getUser());
    }

    @Test
    void testProfileBuilder() {
        User user = new User();
        Profile profile = Profile.builder()
                .id(1L)
                .email("test@example.com")
                .user(user)
                .build();
        assertEquals(1L, profile.getId());
        assertEquals("test@example.com", profile.getEmail());
        assertEquals(user, profile.getUser());
    }

    @Test
    void testSettersAndGetters() {
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setEmail("test@example.com");
        User user = new User();
        profile.setUser(user);

        assertEquals(1L, profile.getId());
        assertEquals("test@example.com", profile.getEmail());
        assertEquals(user, profile.getUser());
    }
}
