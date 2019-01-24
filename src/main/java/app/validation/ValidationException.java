package app.validation;

public class ValidationException extends Exception {
    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Exception e) {
        super(e);
    }
}
