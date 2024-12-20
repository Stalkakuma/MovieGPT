package lt.techin.group.project.security;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lt.techin.group.project.exception.EmailAlreadyExistsException;
import lt.techin.group.project.exception.UsernameAlreadyExistsException;
import lt.techin.group.project.rest.UserLoginRequest;
import lt.techin.group.project.rest.UserSignupRequest;
import lt.techin.group.project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(userLoginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody @Valid UserSignupRequest userSignupRequest) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userSignupRequest));
    }

}