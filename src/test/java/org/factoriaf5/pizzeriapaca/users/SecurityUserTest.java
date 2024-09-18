package org.factoriaf5.pizzeriapaca.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.factoriaf5.pizzeriapaca.roles.Role;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SecurityUserTest {

    @Test
    void testGetUsername() {
        User user = new User();
        user.setUsername("testuser");
        SecurityUser securityUser = new SecurityUser(user);
        assertEquals("testuser", securityUser.getUsername());
    }

    @Test
    void testGetPassword() {
        User user = new User();
        user.setPassword("password");
        SecurityUser securityUser = new SecurityUser(user);
        assertEquals("password", securityUser.getPassword());
    }

    @Test
    void testGetAuthorities() {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);

        SecurityUser securityUser = new SecurityUser(user);
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        assertEquals(authorities, securityUser.getAuthorities());
    }

    @Test
    void testIsAccountNonExpired() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        assertTrue(securityUser.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        assertTrue(securityUser.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        assertTrue(securityUser.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        assertTrue(securityUser.isEnabled());
    }
}
