package lt.techin.group.project.security;

import lombok.AllArgsConstructor;
import lt.techin.group.project.respository.UserRepository;
import lt.techin.group.project.rest.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TestController {

    private final UserRepository userRepository;

    @GetMapping
    ResponseEntity<List<UserDto>> get() {

        return ResponseEntity.ok(userRepository.findAll().stream().map(UserDto::new).toList());
    }
}
