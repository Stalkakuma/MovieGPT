package lt.techin.group.project.servicesTest;

import lt.techin.group.project.exception.EmailAlreadyExistsException;
import lt.techin.group.project.exception.UsernameAlreadyExistsException;
import lt.techin.group.project.model.Roles;
import lt.techin.group.project.model.User;
import lt.techin.group.project.repository.UserRepository;
import lt.techin.group.project.rest.UserSignupRequest;
import lt.techin.group.project.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() throws UsernameAlreadyExistsException, EmailAlreadyExistsException {

        UserSignupRequest signupRequest = new UserSignupRequest("newUser", "password", "new@example.com");
        User user = new User();
        user.setUsername("newUser");
        user.setEmail("new@example.com");
        user.setRoles(Set.of(Roles.USER));

        when(userRepository.findByUsername("newUser")).thenReturn(null);
        when(userRepository.findByEmail("new@example.com")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        Map<String, String> response = userService.register(signupRequest);

        assertNotNull(response);
        assertEquals("User successfully created.", response.get("message"));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterThrowsUsernameAlreadyExistsException() {

        UserSignupRequest signupRequest = new UserSignupRequest("existingUser", "password", "new@example.com");
        User existingUser = new User();
        existingUser.setUsername("existingUser");

        when(userRepository.findByUsername("existingUser")).thenReturn(existingUser);

        UsernameAlreadyExistsException exception = assertThrows(UsernameAlreadyExistsException.class, () -> {
            userService.register(signupRequest);
        });

        assertEquals("User with name: existingUser, already exist.", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testRegisterThrowsEmailAlreadyExistsException() {

        UserSignupRequest signupRequest = new UserSignupRequest("newUser", "password", "existing@example.com");
        User existingUser = new User();
        existingUser.setEmail("existing@example.com");

        when(userRepository.findByUsername("newUser")).thenReturn(null);
        when(userRepository.findByEmail("existing@example.com")).thenReturn(existingUser);

        EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.register(signupRequest);
        });

        assertEquals("User with email: existing@example.com, already exist.", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}
