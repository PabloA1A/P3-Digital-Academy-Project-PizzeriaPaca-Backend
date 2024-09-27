package org.factoriaf5.pizzeriapaca.users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserDtoTest {

    @Test
    void testUserDtoConstructor() {
        UserDto userDto = new UserDto("testuser", "password");
        assertEquals("testuser", userDto.getUsername());
        assertEquals("password", userDto.getPassword());
    }

    @Test
    void testGetUsername() {
        UserDto userDto = new UserDto("testuser", "password");
        assertEquals("testuser", userDto.getUsername());
    }

    @Test
    void testGetPassword() {
        UserDto userDto = new UserDto("testuser", "password");
        assertEquals("password", userDto.getPassword());
    }
}
