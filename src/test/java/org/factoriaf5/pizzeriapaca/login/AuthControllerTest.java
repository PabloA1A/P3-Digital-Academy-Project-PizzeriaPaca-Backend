package org.factoriaf5.pizzeriapaca.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import org.factoriaf5.pizzeriapaca.users.User;
import org.factoriaf5.pizzeriapaca.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testLoginSuccess() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testuser");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);

        when(authentication.getAuthorities()).thenAnswer(invocation -> authorities);

        ResponseEntity<Map<String, String>> response = authController.login();

        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Logged");
        expectedResponse.put("id", "1");
        expectedResponse.put("username", "testuser");
        expectedResponse.put("roles", "ROLE_USER");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testLoginUserNotFound() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("unknownuser");
        when(userRepository.findByUsername("unknownuser")).thenReturn(Optional.empty());

        try {
            authController.login();
        } catch (UsernameNotFoundException e) {
            assertEquals("User not found: unknownuser", e.getMessage());
        }
    }
}
