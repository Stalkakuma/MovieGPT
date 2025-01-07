package lt.techin.group.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class CannotEditAnotherUserFavoriteListException extends RuntimeException {
    public CannotEditAnotherUserFavoriteListException(String message) {
        super(message);
    }
}
