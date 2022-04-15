package Hospital.Exception;

public class RecordDoesNotExist extends RuntimeException{
    public RecordDoesNotExist() {
    }

    public RecordDoesNotExist(String message) {
        super(message);
    }

    public RecordDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }
}
