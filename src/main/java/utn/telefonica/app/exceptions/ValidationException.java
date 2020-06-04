package utn.telefonica.app.exceptions;

public class ValidationException extends Throwable {
    private final String message;

    public ValidationException(String message){
        super(message);
        this.message = message;
    }
}
