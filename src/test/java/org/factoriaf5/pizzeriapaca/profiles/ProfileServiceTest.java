package org.factoriaf5.pizzeriapaca.profiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProfile() {
        Profile profile = new Profile();
        when(profileRepository.save(profile)).thenReturn(profile);

        Profile savedProfile = profileService.save(profile);

        assertEquals(profile, savedProfile);
    }

    @Test
    void testGetEmailByUserId() {
        Profile profile = new Profile();
        profile.setEmail("test@example.com");
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));

        String email = profileService.getEmailByUserId(1L);

        assertEquals("test@example.com", email);
    }

    @Test
    void testGetEmailByUserIdNotFound() {
        when(profileRepository.findById(1L)).thenReturn(Optional.empty());

        String email = profileService.getEmailByUserId(1L);

        assertNull(email);
    }
}
