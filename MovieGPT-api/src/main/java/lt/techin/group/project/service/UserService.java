package lt.techin.group.project.service;


import lombok.AllArgsConstructor;
import lt.techin.group.project.respository.RoleRepository;
import lt.techin.group.project.respository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;




}
