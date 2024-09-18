package org.factoriaf5.pizzeriapaca.users;

//import org.factoriaf5.pizzeriapaca.users.SecurityUser;
//import org.factoriaf5.pizzeriapaca.users.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    UserRepository repository;

    public JpaUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repository
            .findByUsername(username)
            .map(SecurityUser::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

}