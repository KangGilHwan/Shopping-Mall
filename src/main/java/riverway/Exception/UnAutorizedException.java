package riverway.Exception;

public class UnAutorizedException extends RuntimeException {

    public UnAutorizedException() {
        super();
    }

    public UnAutorizedException(String message) {
        super(message);
    }
}
