package controller.Exceptions;

public class InternalServerErrorException extends Exception {
    public InternalServerErrorException (String errorMessage) {
        super("An unexpected server error ocurred: " + errorMessage);
    }
}
