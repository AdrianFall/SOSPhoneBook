package core.service.exception;

/**
 * Created by Adrian on 12/05/2015.
 */
public class AccountExistsException extends Exception {

    public AccountExistsException(String message) {
        super(message);
    }

    public AccountExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public AccountExistsException() {
        super();
    }
}
