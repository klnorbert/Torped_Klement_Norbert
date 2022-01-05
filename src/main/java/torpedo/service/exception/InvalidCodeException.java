package torpedo.service.exception;

/**
 * Exception to represent that a column was invalid.
 */
public class InvalidCodeException extends MapValidationException {

    public InvalidCodeException(String message) {
        super(message);
    }

}
