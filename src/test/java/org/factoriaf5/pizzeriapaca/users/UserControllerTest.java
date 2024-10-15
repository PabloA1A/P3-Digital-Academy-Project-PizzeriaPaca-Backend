package org.factoriaf5.pizzeriapaca.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.factoriaf5.pizzeriapaca.roles.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById_UserExists() {
        // Preparar datos de prueba
        Long userId = 1L;
        UserDto userDto = new UserDto("testUser", Set.of("USER"), "test@example.com");
        when(userService.findUserById(userId)).thenReturn(Optional.of(userDto));

        // Llamar al método
        ResponseEntity<UserDto> response = userController.getUserById(userId);

        // Verificaciones
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userService, times(1)).findUserById(userId);
    }

    @Test
    void testGetUserById_UserNotFound() {
        // Preparar datos de prueba
        Long userId = 1L;
        when(userService.findUserById(userId)).thenReturn(Optional.empty());

        // Llamar al método
        ResponseEntity<UserDto> response = userController.getUserById(userId);

        // Verificaciones
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).findUserById(userId);
    }

    @Test
    void testGetAllUsers() {
        // Preparar datos de prueba
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");
        Role role = new Role();
        role.setName("USER");
        user1.setRoles(Set.of(role));
        user2.setRoles(Set.of(role));

        when(userService.findAllUsers()).thenReturn(List.of(user1, user2));
        when(userService.getEmailByUserId(user1.getId())).thenReturn("user1@example.com");
        when(userService.getEmailByUserId(user2.getId())).thenReturn("user2@example.com");

        // Llamar al método
        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        // Verificaciones
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(userService, times(1)).findAllUsers();
    }

    @Test
    void testCreateUser() {
        // Preparar datos de prueba
        UserDto userDto = new UserDto("testUser", "password123", Set.of("USER"), "test@example.com");
        User user = new User(userDto.getUsername(), userDto.getPassword());
        when(userService.saveUser(any(User.class), eq(userDto.getRoles()), eq(userDto.getEmail()))).thenReturn(user);

        // Llamar al método
        ResponseEntity<User> response = userController.createUser(userDto);

        // Verificaciones
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).saveUser(any(User.class), eq(userDto.getRoles()), eq(userDto.getEmail()));
    }

    @Test
    void testUpdateUser_UserExists() {
        // Preparar datos de prueba
        Long userId = 1L;
        UserDto userDto = new UserDto("updatedUser", "newPassword", Set.of("ADMIN"), "updated@example.com");
        User user = new User("updatedUser", "newPassword");
        when(userService.updateUser(eq(userId), any(UserDto.class), eq(userDto.getRoles()), eq(userDto.getEmail()))).thenReturn(Optional.of(user));

        // Llamar al método
        ResponseEntity<User> response = userController.updateUser(userId, userDto);

        // Verificaciones
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).updateUser(eq(userId), any(UserDto.class), eq(userDto.getRoles()), eq(userDto.getEmail()));
    }

    @Test
    void testUpdateUser_UserNotFound() {
        // Preparar datos de prueba
        Long userId = 1L;
        UserDto userDto = new UserDto("updatedUser", "newPassword", Set.of("ADMIN"), "updated@example.com");
        when(userService.updateUser(eq(userId), any(UserDto.class), eq(userDto.getRoles()), eq(userDto.getEmail()))).thenReturn(Optional.empty());

        // Llamar al método
        ResponseEntity<User> response = userController.updateUser(userId, userDto);

        // Verificaciones
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).updateUser(eq(userId), any(UserDto.class), eq(userDto.getRoles()), eq(userDto.getEmail()));
    }

    @Test
    void testDeleteUser() {
        // Preparar datos de prueba
        Long userId = 1L;

        // Llamar al método
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Verificaciones
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }
}

