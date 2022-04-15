package Hospital.Exception;

public class InvalidNationalCode extends RuntimeException{
    public InvalidNationalCode() {
    }

    public InvalidNationalCode(String message) {
        super(message);
    }

    public InvalidNationalCode(String message, Throwable cause) {
        super(message, cause);
    }
}
