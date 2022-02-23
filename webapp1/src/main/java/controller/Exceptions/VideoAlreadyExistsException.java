package controller.Exceptions;

public class VideoAlreadyExistsException extends Exception  {
    
    public VideoAlreadyExistsException () {
        super("The video you want to add already exists!");
    }
}
