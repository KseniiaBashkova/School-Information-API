package SchoolInformationAPI.Exception;

/**
 * Base for all application-specific exceptions.
 */
public class SchoolSystemException extends RuntimeException {

    public SchoolSystemException() {
    }

    public SchoolSystemException(String message) {
        super(message);
    }

    public SchoolSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchoolSystemException(Throwable cause) {
        super(cause);
    }

}
