package lt.techin.group.project.service;


import lombok.AllArgsConstructor;
import lt.techin.group.project.exception.EmailAlreadyExistsException;
import lt.techin.group.project.exception.UsernameAlreadyExistsException;
import lt.techin.group.project.model.Roles;
import lt.techin.group.project.model.User;
import lt.techin.group.project.repository.UserRepository;
import lt.techin.group.project.rest.UserLoginRequest;
import lt.techin.group.project.rest.UserSignupRequest;
import lt.techin.group.project.rest.dto.UserDto;
import lt.techin.group.project.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;


@AllArgsConstructor
@Service
public class UserService {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public Map<String, String> login(UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDto userDto = new UserDto(userRepository.findByUsername(userDetails.getUsername()));
        return jwtService.generateToken(userDto);
    }

    public Map<String, String> register(UserSignupRequest userSignupRequest) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        if (userRepository.findByUsername(userSignupRequest.getUsername()) != null) {
            throw new UsernameAlreadyExistsException("User with name: " + userSignupRequest.getUsername() + ", already exist.");
        }
        if (userRepository.findByEmail(userSignupRequest.getEmail()) != null) {
            throw new EmailAlreadyExistsException("User with email: " + userSignupRequest.getEmail() + ", already exist.");
        }

        User newUser = new User();
        newUser.setUsername(userSignupRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(userSignupRequest.getPassword()));
        newUser.setEmail(userSignupRequest.getEmail());
        newUser.setRoles(Set.of(Roles.USER));
        userRepository.save(newUser);

        return Map.of("message" , "User successfully created.");
    }
}
