package core.service.exception;

/**
 * Created by Adrian on 12/05/2015.
 */
public class InvalidArgumentException extends Exception {

    public InvalidArgumentException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException() {
        super();
    }
}
