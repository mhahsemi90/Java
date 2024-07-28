package calculation.services.exception;

public class HandledError extends RuntimeException {
    public HandledError(String message) {
        super(message);
    }
}
