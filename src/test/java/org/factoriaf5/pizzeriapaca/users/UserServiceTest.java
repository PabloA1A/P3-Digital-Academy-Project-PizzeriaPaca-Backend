package org.factoriaf5.pizzeriapaca.users;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.factoriaf5.pizzeriapaca.profiles.Profile;
import org.factoriaf5.pizzeriapaca.profiles.ProfileService;
import org.factoriaf5.pizzeriapaca.roles.Role;
import org.factoriaf5.pizzeriapaca.roles.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private UserService userService;

    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    void testSaveUser() {
        
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        Role role = new Role();
        role.setName("USER");

        Set<String> roleNames = new HashSet<>();
        roleNames.add("USER");

        when(roleService.getRoleByName("USER")).thenReturn(role);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        when(profileService.save(any(Profile.class))).thenReturn(new Profile("test@example.com", user));

       
        User savedUser = userService.saveUser(user, roleNames, "test@example.com");

    
        assertNotNull(savedUser);
        assertEquals("testUser", savedUser.getUsername());
        assertTrue(passwordEncoder.matches("password123", savedUser.getPassword()));
        verify(userRepository, times(1)).save(any(User.class));
        verify(profileService, times(1)).save(any(Profile.class));
    }

    @Test
    void testUpdateUser() {
        
        Long userId = 1L;
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setUsername("existingUser");
        existingUser.setPassword(passwordEncoder.encode("oldPassword"));

        UserDto userDto = new UserDto();
        userDto.setUsername("updatedUser");
        userDto.setPassword("newPassword");

        Role role = new Role();
        role.setName("ADMIN");

        Set<String> roleNames = new HashSet<>();
        roleNames.add("ADMIN");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(roleService.getRoleByName("ADMIN")).thenReturn(role);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        when(profileService.findByUserId(userId)).thenReturn(Optional.of(new Profile("old@example.com", existingUser)));
        when(profileService.save(any(Profile.class))).thenReturn(new Profile("updated@example.com", existingUser));

        
        Optional<User> updatedUserOpt = userService.updateUser(userId, userDto, roleNames, "updated@example.com");

        // Verificaciones
        assertTrue(updatedUserOpt.isPresent());
        User updatedUser = updatedUserOpt.get();
        assertEquals("updatedUser", updatedUser.getUsername());
        assertTrue(passwordEncoder.matches("newPassword", updatedUser.getPassword()));
        verify(userRepository, times(1)).save(any(User.class));
        verify(profileService, times(1)).save(any(Profile.class));
    }

    @Test
    void testUpdateUser_NotFound() {
        
        Long userId = 1L;
        UserDto userDto = new UserDto();
        userDto.setUsername("nonExistentUser");
        Set<String> roleNames = new HashSet<>();
        roleNames.add("ADMIN");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        
        Optional<User> result = userService.updateUser(userId, userDto, roleNames, "nonexistent@example.com");

        
        assertFalse(result.isPresent());
        verify(userRepository, never()).save(any(User.class));
        verify(profileService, never()).save(any(Profile.class));
    }
}

