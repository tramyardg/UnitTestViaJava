package tdd;

public class EmptyListException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public EmptyListException() {
	super("your list is empty");
    }
    
}
