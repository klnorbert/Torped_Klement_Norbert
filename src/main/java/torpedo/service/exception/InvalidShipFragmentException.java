package torpedo.service.exception;

/**
 * Exception to represent that a box was invalid.
 */
public class InvalidShipFragmentException extends MapValidationException {

    public InvalidShipFragmentException(String message) {
        super(message);
    }

}
