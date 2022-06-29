/**
 * This exception is used to show specifically that the delimiter does not exist
 */
public class DelimiterNotFoundException extends RuntimeException {
    public DelimiterNotFoundException(String message) {
        super(message);
    }

    public DelimiterNotFoundException(String message, Throwable err) {
        super(message, err);
    }
}
