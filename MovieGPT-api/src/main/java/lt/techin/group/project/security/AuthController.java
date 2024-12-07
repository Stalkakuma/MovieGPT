package lt.techin.group.project.security;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lt.techin.group.project.model.Role;
import lt.techin.group.project.model.User;
import lt.techin.group.project.respository.RoleRepository;
import lt.techin.group.project.respository.UserRepository;
import lt.techin.group.project.rest.UserLoginRequest;
import lt.techin.group.project.rest.UserSignupRequest;
import lt.techin.group.project.rest.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public String login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDto userDto = new UserDto(userRepository.findByUsername(userDetails.getUsername()));
        System.out.println(userDto);
        return jwtService.generateToken(userDto);
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        Role user = roleRepository.findByName("USER");

        if (userRepository.findByUsername(userSignupRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body(new UserDto(userRepository.findByUsername(userSignupRequest.getUsername())));
        }
        User newUser = new User();
        newUser.setUsername(userSignupRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(userSignupRequest.getPassword()));
        newUser.setEmail(userSignupRequest.getEmail());
        newUser.setRoles(new HashSet<>(Set.of(user)));
        userRepository.save(newUser);

        return ResponseEntity.ok(new UserDto(newUser));

    }

}
