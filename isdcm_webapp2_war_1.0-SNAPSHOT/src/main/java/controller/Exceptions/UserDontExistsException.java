package controller.Exceptions;

public class UserDontExistsException extends Exception {

    public UserDontExistsException() {
        super("The user don't exists!");
    }
}
