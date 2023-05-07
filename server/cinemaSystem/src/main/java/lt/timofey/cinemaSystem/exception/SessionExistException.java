package lt.timofey.cinemaSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SessionExistException extends RuntimeException{
    public SessionExistException(String message) {
        super(message);
    }
}
