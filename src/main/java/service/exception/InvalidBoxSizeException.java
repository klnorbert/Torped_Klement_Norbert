package service.exception;

/**
 * Exception to represent that a box was invalid.
 */
public class InvalidBoxSizeException extends MapValidationException {

    public InvalidBoxSizeException(String message) {
        super(message);
    }

}
