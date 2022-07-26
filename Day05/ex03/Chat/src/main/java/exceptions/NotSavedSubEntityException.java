package exceptions;



public class NotSavedSubEntityException extends RuntimeException {
    public NotSavedSubEntityException(String s) {
        super(s);
    }
}
