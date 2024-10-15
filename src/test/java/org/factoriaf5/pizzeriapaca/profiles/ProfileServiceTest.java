package org.factoriaf5.pizzeriapaca.profiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.factoriaf5.pizzeriapaca.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    private Profile profile;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

       
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        
        profile = new Profile("test@example.com", user);
        profile.setId(1L);
    }

    @Test
    void testSaveProfile() {
        
        when(profileRepository.save(profile)).thenReturn(profile);

      
        Profile savedProfile = profileService.save(profile);
        assertNotNull(savedProfile);
        assertEquals("test@example.com", savedProfile.getEmail());

        
        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    void testFindByUserId() {
       
        when(profileRepository.findByUserId(1L)).thenReturn(Optional.of(profile));

        
        Optional<Profile> foundProfile = profileService.findByUserId(1L);
        assertTrue(foundProfile.isPresent());
        assertEquals("test@example.com", foundProfile.get().getEmail());

       
        verify(profileRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testFindByUserIdNotFound() {
        
        when(profileRepository.findByUserId(2L)).thenReturn(Optional.empty());

        
        Optional<Profile> foundProfile = profileService.findByUserId(2L);
        assertFalse(foundProfile.isPresent());

        
        verify(profileRepository, times(1)).findByUserId(2L);
    }

    @Test
    void testGetEmailByUserId() {
       
        when(profileRepository.findByUserId(1L)).thenReturn(Optional.of(profile));

        
        String email = profileService.getEmailByUserId(1L);
        assertEquals("test@example.com", email);

        
        verify(profileRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetEmailByUserIdNotFound() {
      
        when(profileRepository.findByUserId(2L)).thenReturn(Optional.empty());

     
        String email = profileService.getEmailByUserId(2L);
        assertNull(email);

        verify(profileRepository, times(1)).findByUserId(2L);
    }
}
