package controller.Exceptions;

public class VideoDontExistsException extends Exception {

    public VideoDontExistsException() {
        super("The video don't exists!");
    }
}
