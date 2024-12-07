package lt.techin.group.project.service;


import lombok.AllArgsConstructor;
import lt.techin.group.project.respository.RoleRepository;
import lt.techin.group.project.respository.UserRepository;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;


}
