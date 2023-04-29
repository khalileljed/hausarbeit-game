package student;

/**
 * General Exception-type of the text adventure framework.
 */
public class TextAdventureException extends Exception {

    /**
     * Exception Prefix.
     */
    public static final String ERROR = "Sorry ";

    /**
     * Creates a new TextAdventureException with the given message.
     * @param message The error-message
     */
    public TextAdventureException(String message) {
    	super(ERROR + message);
    }
}
