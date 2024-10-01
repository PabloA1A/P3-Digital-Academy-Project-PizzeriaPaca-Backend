package org.factoriaf5.pizzeriapaca.users;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.factoriaf5.pizzeriapaca.profiles.Profile;
import org.factoriaf5.pizzeriapaca.profiles.ProfileService;
import org.factoriaf5.pizzeriapaca.roles.Role;
import org.factoriaf5.pizzeriapaca.roles.RoleService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ProfileService profileService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleService roleService, ProfileService profileService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.profileService = profileService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User saveUser(User user, Set<String> roleNames, String email) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = roleNames.stream()
                .map(roleService::getRoleByName)
                .collect(Collectors.toSet());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        Profile profile = new Profile(email, savedUser);
        profileService.save(profile);

        return savedUser;
    }

    public Optional<User> updateUser(Long id, UserDto userDto, Set<String> roleNames, String email) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDto.getUsername());

            if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }

            Set<Role> roles = roleNames.stream()
                .map(roleService::getRoleByName)
                .collect(Collectors.toSet());
            user.setRoles(roles);

            User updatedUser = userRepository.save(user);

            Profile profile = profileService.findByUserId(user.getId())
                    .orElse(new Profile(email, updatedUser));

            profile.setEmail(email);
            profileService.save(profile);

            return Optional.of(updatedUser);
        } else {
            return Optional.empty();
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserDto> findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.flatMap(user -> {
            Optional<Profile> profileOptional = profileService.findByUserId(user.getId());
            String email = profileOptional.map(Profile::getEmail).orElse(null);

            return Optional.of(new UserDto(
                user.getUsername(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                email
            ));
        });
    }

    public String getEmailByUserId(Long userId) {
        Optional<Profile> profileOptional = profileService.findByUserId(userId);
        return profileOptional.map(Profile::getEmail).orElse(null);
    }
}
