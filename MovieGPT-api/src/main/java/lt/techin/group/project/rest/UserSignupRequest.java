package lt.techin.group.project.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UserSignupRequest {

    @NotNull
    @Size(min = 2, max = 30)
    private String username;
    @NotNull
    @Size(min = 2, max = 30)
    private String password;
    @NotNull
    @Email
    private String email;
}