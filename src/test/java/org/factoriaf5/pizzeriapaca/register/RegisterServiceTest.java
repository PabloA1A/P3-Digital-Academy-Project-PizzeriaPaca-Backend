package org.factoriaf5.pizzeriapaca.register;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.factoriaf5.pizzeriapaca.roles.Role;
import org.factoriaf5.pizzeriapaca.roles.RoleRepository;
import org.factoriaf5.pizzeriapaca.roles.exceptions.RoleNotFoundException;
import org.factoriaf5.pizzeriapaca.users.User;
import org.factoriaf5.pizzeriapaca.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RegisterService registerService;

    private RegisterDto registerDto;

    @BeforeEach
    public void setUp() {

        registerDto = new RegisterDto();
        registerDto.setUsername("testuser");
        registerDto.setPassword("password");
        registerDto.setEmail("test@example.com");
        registerDto.setFirstName("John");
        registerDto.setLastName("Doe");
        registerDto.setAddress("123 Main St");
        registerDto.setPostalCode("12345");
        registerDto.setCity("CityName");
    }

    @Test
    public void testSaveUserSuccessfully() {
      
        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(userRole));
        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        when(customerRepository.save(any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);

        User savedUser = registerService.save(registerDto);

        verify(userRepository).save(any(User.class));
        verify(customerRepository).save(any(Customer.class));

     
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertTrue(savedUser.getRoles().contains(userRole));
    }

    @Test
    public void testSaveUserRoleNotFound() {
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.empty());

        RoleNotFoundException exception = assertThrows(
            RoleNotFoundException.class,
            () -> registerService.save(registerDto)
        );

        assertEquals("Role not found: ROLE_USER", exception.getMessage());
    }
}

