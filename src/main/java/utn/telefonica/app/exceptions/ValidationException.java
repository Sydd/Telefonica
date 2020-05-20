package utn.telefonica.app.exceptions;

public class ValidationException extends Throwable {
    public ValidationException(String message){
        super(message);
    }
}
