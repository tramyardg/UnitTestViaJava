package tdd;

class EmptyListException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    EmptyListException() {
        super("your list is empty");
    }

}
