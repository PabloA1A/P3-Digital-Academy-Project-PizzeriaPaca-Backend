package org.factoriaf5.pizzeriapaca.roles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.factoriaf5.pizzeriapaca.roles.exceptions.RoleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RoleServiceTest {

    @Mock
    private RoleRepository repository;

    @InjectMocks
    private RoleService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Role role = new Role();
        role.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(role));

        Role result = service.getById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> {
            service.getById(1L);
        });
    }
}
