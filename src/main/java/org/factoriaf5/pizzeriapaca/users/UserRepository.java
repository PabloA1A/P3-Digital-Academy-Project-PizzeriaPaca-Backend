package org.factoriaf5.pizzeriapaca.users;

import java.util.Optional;

//import org.factoriaf5.pizzeriapaca.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

}
