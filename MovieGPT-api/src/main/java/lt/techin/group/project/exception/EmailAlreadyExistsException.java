package lt.techin.group.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
