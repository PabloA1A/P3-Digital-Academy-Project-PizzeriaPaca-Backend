package org.factoriaf5.pizzeriapaca.profiles;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public Optional<Profile> findByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    public String getEmailByUserId(Long userId) {
        Optional<Profile> profileOptional = profileRepository.findByUserId(userId);
        return profileOptional.map(Profile::getEmail).orElse(null);
    }
}
