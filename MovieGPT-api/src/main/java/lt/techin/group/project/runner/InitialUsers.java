package lt.techin.group.project.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.techin.group.project.model.Roles;
import lt.techin.group.project.model.User;
import lt.techin.group.project.respository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@AllArgsConstructor
@Component
public class InitialUsers implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@gmail.com");
        admin.setRoles(Set.of(Roles.ADMIN, Roles.USER));
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setEmail("user@gmail.com");
        user.setRoles(Set.of(Roles.USER));
        userRepository.save(user);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword(passwordEncoder.encode("user2"));
        user2.setEmail("user2@gmail.com");
        user2.setRoles(Set.of(Roles.USER));
        userRepository.save(user2);

    }
}
