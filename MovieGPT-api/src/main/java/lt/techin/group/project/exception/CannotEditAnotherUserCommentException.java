package lt.techin.group.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class CannotEditAnotherUserCommentException extends RuntimeException {
    public CannotEditAnotherUserCommentException(String message) {
        super(message);
    }
}
