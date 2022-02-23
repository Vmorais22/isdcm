package controller.Exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException () {
        super("The user you want to add already exists!");
    }
}
