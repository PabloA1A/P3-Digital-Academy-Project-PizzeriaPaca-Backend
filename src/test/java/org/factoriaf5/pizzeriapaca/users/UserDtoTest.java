package org.factoriaf5.pizzeriapaca.users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class UserDtoTest {

    @Test
    void testUserDtoConstructor() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        UserDto userDto = new UserDto("testuser", "password", roles, "test@example.com");
        assertEquals("testuser", userDto.getUsername());
        assertEquals("password", userDto.getPassword());
        assertEquals(roles, userDto.getRoles());
        assertEquals("test@example.com", userDto.getEmail());
    }

    @Test
    void testUserDtoConstructorWithoutPassword() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        UserDto userDto = new UserDto("testuser", roles, "test@example.com");
        assertEquals("testuser", userDto.getUsername());
        assertEquals(roles, userDto.getRoles());
        assertEquals("test@example.com", userDto.getEmail());
    }

    @Test
    void testGetUsername() {
        UserDto userDto = new UserDto("testuser", "password");
        assertEquals("testuser", userDto.getUsername());
    }

    @Test
    void testSetUsername() {
        UserDto userDto = new UserDto();
        userDto.setUsername("newuser");
        assertEquals("newuser", userDto.getUsername());
    }

    @Test
    void testGetPassword() {
        UserDto userDto = new UserDto("testuser", "password");
        assertEquals("password", userDto.getPassword());
    }

    @Test
    void testSetPassword() {
        UserDto userDto = new UserDto();
        userDto.setPassword("newpassword");
        assertEquals("newpassword", userDto.getPassword());
    }

    @Test
    void testGetRoles() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        UserDto userDto = new UserDto("testuser", "password", roles, "test@example.com");
        assertEquals(roles, userDto.getRoles());
    }

    @Test
    void testSetRoles() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        UserDto userDto = new UserDto();
        userDto.setRoles(roles);
        assertEquals(roles, userDto.getRoles());
    }

    @Test
    void testGetEmail() {
        UserDto userDto = new UserDto("testuser", "password");
        userDto.setEmail("test@example.com");
        assertEquals("test@example.com", userDto.getEmail());
    }

    @Test
    void testSetEmail() {
        UserDto userDto = new UserDto();
        userDto.setEmail("new@example.com");
        assertEquals("new@example.com", userDto.getEmail());
    }
}
