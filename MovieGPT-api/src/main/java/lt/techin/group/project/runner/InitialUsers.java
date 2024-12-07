package lt.techin.group.project.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.techin.group.project.model.Role;
import lt.techin.group.project.model.User;
import lt.techin.group.project.respository.RoleRepository;
import lt.techin.group.project.respository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Component
public class InitialUsers implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("USER");
        roleRepository.save(userRole);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@gmail.com");
        admin.setRoles(new HashSet<>(Set.of(adminRole)));
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setEmail("user@gmail.com");
        user.setRoles(new HashSet<>(Set.of(userRole)));
        userRepository.save(user);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword(passwordEncoder.encode("user2"));
        user2.setEmail("user2@gmail.com");
        user2.setRoles(new HashSet<>(Set.of(userRole)));
        userRepository.save(user2);

        adminRole.setUsers(new HashSet<>(Set.of(admin)));
        userRole.setUsers(new HashSet<>(Set.of(user, user2)));
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

    }
}
