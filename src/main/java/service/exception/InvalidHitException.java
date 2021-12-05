package service.exception;

/**
 * Exception to represent that a column was invalid.
 */
public class InvalidHitException extends MapValidationException {

    public InvalidHitException(String message) {
        super(message);
    }

}
