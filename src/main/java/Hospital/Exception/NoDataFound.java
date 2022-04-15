package Hospital.Exception;

public class NoDataFound extends RuntimeException{
    public NoDataFound() {
    }

    public NoDataFound(String message) {
        super(message);
    }

    public NoDataFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDataFound(Throwable cause) {
        super(cause);
    }
}
