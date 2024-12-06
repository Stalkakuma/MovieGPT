package lt.techin.group.project.exceptions.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class ValidationErrorResponse {
    private int status;
    private String error;
    private List<FieldError> errors;

    @Getter @Setter
    @AllArgsConstructor
    public static class FieldError {
        private String field;
        private String message;
    }
}
