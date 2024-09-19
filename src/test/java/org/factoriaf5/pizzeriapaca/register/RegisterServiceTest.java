package org.factoriaf5.pizzeriapaca.register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.factoriaf5.pizzeriapaca.facades.EncoderFacade;
import org.factoriaf5.pizzeriapaca.profiles.Profile;
import org.factoriaf5.pizzeriapaca.profiles.ProfileService;
import org.factoriaf5.pizzeriapaca.roles.Role;
import org.factoriaf5.pizzeriapaca.roles.RoleService;
import org.factoriaf5.pizzeriapaca.users.User;
import org.factoriaf5.pizzeriapaca.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RegisterServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private ProfileService profileService;

    @Mock
    private EncoderFacade encoderFacade;

    @InjectMocks
    private RegisterService registerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
     
        RegisterDto registerDto = new RegisterDto("testuser", "encodedpassword", "testemail@example.com");
        String passwordDecoded = "decodedpassword";
        String passwordEncoded = "bcryptencodedpassword";

        Role defaultRole = new Role();
        defaultRole.setId(1L);

        User user = new User("testuser", passwordEncoded);
      

        Profile profile = new Profile("testemail@example.com", user);

        when(encoderFacade.decode("base64", "encodedpassword")).thenReturn(passwordDecoded);
        when(encoderFacade.encode("bcrypt", passwordDecoded)).thenReturn(passwordEncoded);
        when(roleService.getById(1L)).thenReturn(defaultRole);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setRoles(new HashSet<>(Set.of(defaultRole)));
            return savedUser;
        });
        when(profileService.save(any(Profile.class))).thenReturn(profile);

        
        User savedUser = registerService.save(registerDto);

        assertNotNull(savedUser, "Saved user should not be null");
        assertEquals("testuser", savedUser.getUsername(), "Username should match");
        assertEquals(passwordEncoded, savedUser.getPassword(), "Encoded password should match");
        assertNotNull(savedUser.getRoles(), "Roles set should not be null");
        assertTrue(savedUser.getRoles().contains(defaultRole), "User should have the default role");

        verify(encoderFacade).decode("base64", "encodedpassword");
        verify(encoderFacade).encode("bcrypt", passwordDecoded);
        verify(roleService).getById(1L);
        verify(userRepository).save(any(User.class));
        verify(profileService).save(any(Profile.class));
    }

    @Test
    public void testAssignDefaultRole() {
  
        Role defaultRole = new Role();
        defaultRole.setId(1L);

        when(roleService.getById(1L)).thenReturn(defaultRole);

       
        Set<Role> roles = registerService.assignDefaultRole();

    
        assertNotNull(roles, "Roles set should not be null");
        assertEquals(1, roles.size(), "Roles set should contain exactly one role");
        assertTrue(roles.contains(defaultRole), "Roles set should contain the default role");

        verify(roleService).getById(1L);
    }
}
