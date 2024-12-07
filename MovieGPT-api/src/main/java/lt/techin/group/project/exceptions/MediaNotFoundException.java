package lt.techin.group.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MediaNotFoundException extends RuntimeException {
  public MediaNotFoundException(String message) {
    super(message);
  }
}
