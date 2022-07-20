package ex04;

public class IllegalTransactionException extends RuntimeException {

    public IllegalTransactionException() {
        super("User balance cannot be negative");
    }

}
