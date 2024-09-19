package org.factoriaf5.pizzeriapaca.registers;

import java.util.Set;
import java.util.HashSet;

//import org.factoriaf5.pizzeriapaca.registers.RegisterDto;
import org.factoriaf5.pizzeriapaca.security.facades.EncoderFacade;
import org.factoriaf5.pizzeriapaca.security.implementations.IEncryptFacade;
import org.factoriaf5.pizzeriapaca.roles.Role;
import org.factoriaf5.pizzeriapaca.roles.RoleService;
import org.factoriaf5.pizzeriapaca.users.User;
import org.factoriaf5.pizzeriapaca.profiles.Profile;
import org.factoriaf5.pizzeriapaca.profiles.ProfileService;
import org.factoriaf5.pizzeriapaca.users.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ProfileService profileService;
    private final IEncryptFacade encoderFacade;

    public RegisterService(UserRepository userRepository, RoleService roleService, ProfileService profileService, EncoderFacade encoderFacade) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.profileService = profileService;
        this.encoderFacade = encoderFacade;
    }

    public User save(RegisterDto newRegisterDto) {
        String passwordDecoded = encoderFacade.decode("base64", newRegisterDto.getPassword());
        String passwordEncoded = encoderFacade.encode("bcrypt", passwordDecoded);

        User user = new User(newRegisterDto.getUsername(), passwordEncoded);
        user.setRoles(assignDefaultRole());

        User savedUser = userRepository.save(user);

        Profile profile = new Profile(newRegisterDto.getEmail(), savedUser);
        profileService.save(profile);

        return savedUser;
    }

    public Set<Role> assignDefaultRole() {
        Role defaultRole = roleService.getById(1L);

        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);

        return roles;
    }
}
