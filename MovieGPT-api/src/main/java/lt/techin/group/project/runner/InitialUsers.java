package lt.techin.group.project.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.techin.group.project.model.User;
import lt.techin.group.project.respository.RoleRepository;
import lt.techin.group.project.respository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class InitialUsers implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

//        User user = new User();
//        user.setUsername("TTT");
//        userRepository.save(user);
//        log.info("Not working");

    }
}
