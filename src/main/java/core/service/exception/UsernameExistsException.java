package core.service.exception;

/**
 * Created by Adrian on 12/05/2015.
 */
public class UsernameExistsException extends Exception {

    public UsernameExistsException(String message) {
        super(message);
    }

    public UsernameExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UsernameExistsException() {
        super();
    }
}
