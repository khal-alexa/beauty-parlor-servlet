package dao.exception;

public class DaoConnectionException extends RuntimeException {
    public DaoConnectionException(String message) {
        super(message);
    }

    public DaoConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
