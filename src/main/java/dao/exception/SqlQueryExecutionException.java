package dao.exception;

public class SqlQueryExecutionException extends RuntimeException {
    public SqlQueryExecutionException(String message) {
        super(message);
    }

    public SqlQueryExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
