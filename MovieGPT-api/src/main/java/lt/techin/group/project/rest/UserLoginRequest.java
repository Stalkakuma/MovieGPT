package lt.techin.group.project.rest;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
