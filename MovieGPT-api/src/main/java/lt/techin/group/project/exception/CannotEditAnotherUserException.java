package lt.techin.group.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class CannotEditAnotherUserException extends RuntimeException {
    public CannotEditAnotherUserException(String message) {
        super(message);
    }
}
