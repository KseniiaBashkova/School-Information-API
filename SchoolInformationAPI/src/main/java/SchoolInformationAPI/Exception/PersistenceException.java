package SchoolInformationAPI.Exception;

public class PersistenceException extends SchoolSystemException {

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }
}
