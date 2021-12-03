package service.exception;

/**
 * Exception to represent that a row was invalid.
 */
public class InvalidShipBorderException extends MapValidationException {

    public InvalidShipBorderException(String message) {
        super(message);
    }

}
