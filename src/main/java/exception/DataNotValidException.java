package exception;

public class DataNotValidException extends RuntimeException {
    public DataNotValidException(String message) {
        super(message);
    }

    public DataNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

}
